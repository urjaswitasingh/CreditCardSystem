package com.citibank.creditcardactivation;

import com.citibank.creditcardactivation.kafka.KafkaProducerService;
import com.citibank.creditcardactivation.kafkamessagetemplate.CreditCheckMessage;
import com.citibank.creditcardactivation.kafkamessagetemplate.CreditScoreReceiveMessage;
import com.citibank.creditcardactivation.model.CreditScore;
import com.citibank.creditcardactivation.repository.CreditScoreRepo;
import com.citibank.creditcardactivation.service.CreditCheckService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CreditCheckServiceTest {

    @Mock
    private CreditScoreRepo creditScoreRepo;

    @Mock
    private KafkaProducerService kafkaProducerService;
    @InjectMocks
    private CreditCheckService creditCheckService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreditCheck_NewCreditScore() throws JsonProcessingException {
        CreditCheckMessage creditCheckMessage = new CreditCheckMessage(1234567890L, 75000, 3);
        String message = objectMapper.writeValueAsString(creditCheckMessage);
        when(creditScoreRepo.findByPhoneNumber(creditCheckMessage.getPhoneNumber())).thenReturn(null);

        creditCheckService.creditCheck(message);

        // ArgumentCaptor is used to capture the arguments passed to a method or not.
        ArgumentCaptor<CreditScore> creditScoreCaptor = ArgumentCaptor.forClass(CreditScore.class);
        verify(creditScoreRepo, times(1)).save(creditScoreCaptor.capture());
        CreditScore savedCreditScore = creditScoreCaptor.getValue();
        assertEquals(1234567890L, savedCreditScore.getPhoneNumber());
        assertEquals(450, savedCreditScore.getCreditScore());


    }
}

