package com.ecommerce.orders.auditing;

import com.ecommerce.orders.entity.Audit;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class CAuditEntityListener {
    @PrePersist
    public void setCreatedAt(Audit audit){
        audit.setCreatedAt( LocalDateTime.now());
    }
    @PreUpdate
    public void setUpdatedAt(Audit audit){
        audit.setUpdatedAt(LocalDateTime.now());
    }
}
