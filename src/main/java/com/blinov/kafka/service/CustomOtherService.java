package com.blinov.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomOtherService {

    private final Map<String, TransferService> transferServiceMap;

    public CustomOtherService(List<TransferService> transferServices) {
        this.transferServiceMap = transferServices.stream().collect(Collectors.toMap(TransferService::entityType, Function.identity()));
    }

    public void transferEntity(Object dto) {
        TransferService transferService = transferServiceMap.get(dto.getClass().getSimpleName());
        transferService.handleEntity(dto);
    }
}
