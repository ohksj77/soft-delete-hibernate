package com.soft.deletion.greeting;

import com.soft.deletion.global.IdResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GreetingService {

    private final GreetingRepository greetingRepository;
    private final GreetingMapper greetingMapper;

    @Transactional
    public List<IdResponse<Long>> createGreeting() {
        final List<Greeting> greetings =
                greetingRepository.saveAll(
                        List.of(Greeting.ofOnlyIAmFine(), Greeting.ofOnlyYouAreFine()));

        return greetingMapper.toIdResponses(greetings);
    }

    @Transactional
    public void updateGreetingsToBeHappy() {
        greetingRepository.findAll().forEach(Greeting::updateToBeHappy);
    }

    @Transactional(readOnly = true)
    public List<GreetingResponse> findAllGreetings() {
        return greetingMapper.toGreetingResponses(greetingRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<GreetingResponse> findAllGreetingsDeleted() {
        return greetingMapper.toGreetingResponses(greetingRepository.findAllByDeletedAtIsNotNull());
    }

    @Transactional
    public void deleteGreeting() {
        greetingRepository.deleteAll();
    }
}
