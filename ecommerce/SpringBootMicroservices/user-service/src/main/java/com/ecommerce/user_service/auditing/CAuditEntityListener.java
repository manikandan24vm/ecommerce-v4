package com.ecommerce.user_service.auditing;

import com.ecommerce.user_service.entity.Details;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class CAuditEntityListener {
    @PrePersist
    public void setCreationFields(Details details) {
        details.setCreatedAt(LocalDateTime.now());
        details.setUpdatedAt(null); // Avoid setting updatedAt on creation
    }

    @PreUpdate
    public void setUpdateFields(Details details) {
        details.setUpdatedAt(LocalDateTime.now()); // Set only on update

    }
}
