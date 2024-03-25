package com.soft.deletion.greeting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GreetingRepository extends JpaRepository<Greeting, UUID> {}
