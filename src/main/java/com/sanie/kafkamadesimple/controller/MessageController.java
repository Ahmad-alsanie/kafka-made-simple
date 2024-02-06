package com.sanie.kafkamadesimple.controller;

import com.sanie.kafkamadesimple.model.Message;
import com.sanie.kafkamadesimple.repository.MessageRepository;
import com.sanie.kafkamadesimple.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    private final KafkaProducerService kafkaProducerService;

    public MessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducerService.sendMessage(message, "input-topic");
        return "Message sent to the Kafka Topic input-topic Successfully";
    }
}

