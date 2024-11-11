package me.maistrenko.dubbodao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer count;
    private LocalDateTime lastUpdatedAt;
}
