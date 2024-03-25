package com.soft.deletion.global.audit;

import jakarta.persistence.Embeddable;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Embeddable
public class BaseTime {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected BaseTime() {
        this.createdAt = LocalDateTime.now();
    }

    public void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
