package com.blinov.kafka.config;

import com.blinov.kafka.dto.KafkaMessageDocumentDto;
import com.blinov.kafka.dto.KafkaMessageUserDto;
import com.blinov.kafka.service.CustomOtherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConfig {
    private final ObjectMapper objectMapper;
    private final CustomOtherService customOtherService;

    @Bean
    public Function<String, String> getKafkaMessage() {
        return message -> {
            try {
                JSONObject jsonObject = (JSONObject) jsonParser().parse(message);
                String table = (String) jsonObject.get("__table");
                switch (table) {
                    case "document":
                        KafkaMessageDocumentDto kafkaMessageDocumentDto = objectMapper.readValue(message, KafkaMessageDocumentDto.class);
                        customOtherService.transferEntity(kafkaMessageDocumentDto);
                        break;
                    case "t_user":
                        KafkaMessageUserDto kafkaMessageUserDto = objectMapper.readValue(message, KafkaMessageUserDto.class);
                        customOtherService.transferEntity(kafkaMessageUserDto);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return message;
        };
    }

    @Bean
    public JSONParser jsonParser() {
        return new JSONParser();
    }
}
