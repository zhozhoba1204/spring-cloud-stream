package com.blinov.kafka.service;

import com.blinov.kafka.model.DocumentEntity;
import com.blinov.kafka.dto.KafkaMessageDocumentDto;
import com.blinov.kafka.repo.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService implements TransferService {
    private final DocumentRepository documentRepository;

    @Override
    public void handleEntity(Object dto) {
        KafkaMessageDocumentDto kafkaMessageDocumentDto = (KafkaMessageDocumentDto) dto;
        if (kafkaMessageDocumentDto.isDeleted()) {
            DocumentEntity document = documentRepository.findById(kafkaMessageDocumentDto.getId()).orElseThrow();
            documentRepository.delete(document);
        } else {
            DocumentEntity document = new DocumentEntity(kafkaMessageDocumentDto.getId(),
                    kafkaMessageDocumentDto.getName(), LocalDateTime.now(), LocalDateTime.now());
            documentRepository.save(document);
            log.info("Document has been saved");
        }
    }

    @Override
    public String entityType() {
        return "KafkaMessageDocumentDto";
    }


}
