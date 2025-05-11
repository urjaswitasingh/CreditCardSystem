package com.citibank.creditcardactivation.kafka;


import com.citibank.creditcardactivation.kafkamessagetemplate.CreditCheckMessage;
import com.citibank.creditcardactivation.kafkamessagetemplate.CreditScoreReceiveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final String TOPIC = "send-credit";
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    public void sendMessage(CreditScoreReceiveMessage message){
        this.kafkaTemplate.send(TOPIC, message);
    }
    }

