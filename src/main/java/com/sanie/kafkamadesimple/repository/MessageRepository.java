package com.sanie.kafkamadesimple.repository;

import com.sanie.kafkamadesimple.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}

