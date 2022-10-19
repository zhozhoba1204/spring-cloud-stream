package com.blinov.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "result_user")
public class UserEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID tenantId;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonProperty("birth_date")
    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

}
