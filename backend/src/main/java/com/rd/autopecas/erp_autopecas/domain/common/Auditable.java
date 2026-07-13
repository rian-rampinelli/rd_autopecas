package com.rd.autopecas.erp_autopecas.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class Auditable {

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        updateAt = LocalDateTime.now();
    }

}