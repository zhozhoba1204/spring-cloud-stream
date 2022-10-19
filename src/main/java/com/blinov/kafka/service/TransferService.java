package com.blinov.kafka.service;

public interface TransferService {
    void handleEntity(Object entity);

    String entityType();
}
