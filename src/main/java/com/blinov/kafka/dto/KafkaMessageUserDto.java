package com.blinov.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaMessageUserDto {

    private UUID id;
    private String name;
    @JsonProperty(value = "birth_date")
    private LocalDateTime birthDate;
    @JsonProperty(value = "__op")
    private String op;
    @JsonProperty(value = "__table")
    private String table;
    @JsonProperty(value = "__deleted")
    private boolean deleted;

}
