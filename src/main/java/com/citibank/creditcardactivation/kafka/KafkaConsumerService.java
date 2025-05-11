package com.citibank.creditcardactivation.kafka;


import com.citibank.creditcardactivation.service.CreditCheckService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    CreditCheckService creditCheckService;

    @KafkaListener(topics= "credit-check" ,groupId = "my-group" )
    public void listner(String message) throws JsonProcessingException {
        creditCheckService.creditCheck(message);
    }

}
