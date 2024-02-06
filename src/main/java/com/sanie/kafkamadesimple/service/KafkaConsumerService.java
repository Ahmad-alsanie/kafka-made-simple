package com.sanie.kafkamadesimple.service;

import com.sanie.kafkamadesimple.model.Message;
import com.sanie.kafkamadesimple.repository.MessageRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private MessageRepository messageRepository;

    @KafkaListener(topics = "input-topic", groupId = "my-group")
    public void listenGroupFoo(String messageContent) {
        Message message = new Message();
        message.setContent(messageContent);
        messageRepository.save(message);
        System.out.println("Saved Message to MongoDB: " + messageContent);
    }
}

