package com.soft.deletion.global.envelop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiStatus {
    GREETING_CREATED("G101", "Greeting created"),
    GREETINGS_FOUND("G201", "Greetings found");

    private final String code;
    private final String message;
}
