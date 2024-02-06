package com.sanie.kafkamadesimple.unit;

import com.sanie.kafkamadesimple.model.Message;
import com.sanie.kafkamadesimple.repository.MessageRepository;
import com.sanie.kafkamadesimple.service.KafkaConsumerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class KafkaConsumerServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private KafkaConsumerService kafkaConsumerService;

    @Test
    public void whenMessageReceived_thenSaveMessage() {
        String messageContent = "Test message";
        kafkaConsumerService.listenGroupFoo(messageContent);

        verify(messageRepository).save(any(Message.class));
    }
}
