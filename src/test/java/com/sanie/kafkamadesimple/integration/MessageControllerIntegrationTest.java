package com.sanie.kafkamadesimple.integration;

import com.sanie.kafkamadesimple.controller.MessageController;
import com.sanie.kafkamadesimple.model.Message;
import com.sanie.kafkamadesimple.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void givenMessages_whenGetMessages_thenReturnJsonArray() throws Exception {
        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk());
    }

    @Test
    public void sendMessageTest() throws Exception {
        String message = "Test message";
        mockMvc.perform(get("/messages/send").param("message", message))
                .andExpect(status().isOk())
                .andExpect(content().string("Message sent to the Kafka Topic input-topic Successfully"));

        verify(kafkaTemplate).send(eq("input-topic"), eq(message));
    }
}

