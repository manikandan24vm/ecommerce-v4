package com.ecommers.product_service.auditing;

import com.ecommers.product_service.entity.Audit;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
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
