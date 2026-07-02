package com.timeorchestrator.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

/**
 * User JPA Entity Class
 */
@Entity
@Data
public class UserData {
    @Id
    @UUID
    @Length(max = 36)
    private String id;

    @URL
    @Length(max = 512)
    private String profileImageUrl;

    @Length(max = 128)
    private String timezone;
}
