package com.soft.deletion.global.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Optional;

public class AuditListener {

    @PrePersist
    public void setCreatedAt(final Auditable auditable) {
        final BaseTime baseTime =
                Optional.ofNullable(auditable.getBaseTime()).orElseGet(BaseTime::new);
        auditable.setBaseTime(baseTime);
    }

    @PreUpdate
    public void setUpdatedAt(final Auditable auditable) {
        auditable.getBaseTime().update();
    }
}
