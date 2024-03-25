package com.soft.deletion.greeting;

import com.soft.deletion.global.IdResponse;
import com.soft.deletion.global.envelop.ApiResponse;
import com.soft.deletion.global.envelop.ApiStatus;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<List<IdResponse<Long>>> createGreeting() {
        return ApiResponse.of(greetingService.createGreeting(), ApiStatus.GREETING_CREATED);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGreeting() {
        greetingService.updateGreetingsToBeHappy();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGreeting() {
        greetingService.deleteGreeting();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<GreetingResponse>> findAllGreetings() {
        return ApiResponse.of(greetingService.findAllGreetings(), ApiStatus.GREETINGS_FOUND);
    }
}
