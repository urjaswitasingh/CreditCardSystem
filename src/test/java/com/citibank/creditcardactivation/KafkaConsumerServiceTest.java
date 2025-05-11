package com.citibank.creditcardactivation;


import com.citibank.creditcardactivation.kafka.KafkaConsumerService;
import com.citibank.creditcardactivation.service.CreditCheckService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class KafkaConsumerServiceTest {

    private String testMessage;

    @MockBean
    private KafkaConsumerService kafkaConsumerService;

    @MockBean
    private CreditCheckService creditCheckService;

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        testMessage = "{\"phoneNumber\":1234567890, \"salary\":50000, \"totalCard\":3}";

    }

    @Test
    public void testListener() throws JsonProcessingException {

        kafkaConsumerService.listner(testMessage);

    }
}
