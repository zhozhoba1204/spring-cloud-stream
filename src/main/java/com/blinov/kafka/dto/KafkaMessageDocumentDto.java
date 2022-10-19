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
public class KafkaMessageDocumentDto {

    private UUID id;
    private String name;
    @JsonProperty(value = "created_date")
    private LocalDateTime createdDate;
    @JsonProperty(value = "updated_date")
    private LocalDateTime updatedDate;
    @JsonProperty(value = "__op")
    private String op;
    @JsonProperty(value = "__table")
    private String table;
    @JsonProperty(value = "__deleted")
    private boolean deleted;


}
