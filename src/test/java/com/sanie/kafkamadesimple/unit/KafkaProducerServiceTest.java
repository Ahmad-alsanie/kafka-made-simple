package com.sanie.kafkamadesimple.unit;

import com.sanie.kafkamadesimple.service.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService kafkaProducerService;

    @Test
    public void whenSendMessage_thenVerifyKafkaTemplateCalled() {
        String message = "Test message";
        String topicName = "test-topic";

        kafkaProducerService.sendMessage(message, topicName);

        verify(kafkaTemplate).send(topicName, message);
    }
}

