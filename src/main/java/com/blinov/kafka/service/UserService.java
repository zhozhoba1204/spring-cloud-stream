package com.blinov.kafka.service;

import com.blinov.kafka.dto.KafkaMessageUserDto;
import com.blinov.kafka.model.UserEntity;
import com.blinov.kafka.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements TransferService {

    private final UserRepository userRepository;

    @Override
    public void handleEntity(Object dto) {
        KafkaMessageUserDto kafkaMessageUserDto = (KafkaMessageUserDto) dto;
        if (kafkaMessageUserDto.isDeleted()) {
            UserEntity user = userRepository.findById(kafkaMessageUserDto.getId()).orElseThrow();
            userRepository.delete(user);
        } else {
            UserEntity userEntity = new UserEntity(kafkaMessageUserDto.getId(), kafkaMessageUserDto.getName(), kafkaMessageUserDto.getBirthDate());
            userRepository.save(userEntity);
            log.info("User has been saved");
        }
    }

    @Override
    public String entityType() {
        return "KafkaMessageUserDto";
    }

}
