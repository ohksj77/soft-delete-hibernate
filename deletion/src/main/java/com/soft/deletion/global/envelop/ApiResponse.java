package com.soft.deletion.global.envelop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse<T> {

    private final String code;
    private final String message;
    private T data;

    public static <T> ApiResponse<T> of(final T data, final ApiStatus apiStatus) {
        return new ApiResponse<>(apiStatus.getCode(), apiStatus.getMessage(), data);
    }
}
