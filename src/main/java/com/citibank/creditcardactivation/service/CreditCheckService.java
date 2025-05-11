package com.citibank.creditcardactivation.service;


import com.citibank.creditcardactivation.kafka.KafkaProducerService;
import com.citibank.creditcardactivation.kafkamessagetemplate.CreditCheckMessage;
import com.citibank.creditcardactivation.kafkamessagetemplate.CreditScoreReceiveMessage;
import com.citibank.creditcardactivation.model.CreditScore;
import com.citibank.creditcardactivation.repository.CreditScoreRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCheckService {
    @Autowired
    CreditScoreRepo creditScoreRepo;
    @Autowired
    KafkaProducerService kafkaProducerService;

    public void creditCheck(String message) throws JsonProcessingException {
        // convert kafka message to CreditCheckMessage
        ObjectMapper objectMapper = new ObjectMapper();
        CreditCheckMessage creditCheckMessage = objectMapper.readValue(message, CreditCheckMessage.class);

        // check if credit score already exists
        CreditScore creditScore = creditScoreRepo.findByPhoneNumber(creditCheckMessage.getPhoneNumber());

        // if credit score does not exist, generate credit score and save it
        if (creditScore == null) {
            int generatedScore = generateCreditScore(creditCheckMessage.getSalary(), creditCheckMessage.getTotalCard());
            CreditScore newCreditScore = new CreditScore();
            newCreditScore.setCreditScore(generatedScore);
            newCreditScore.setPhoneNumber(creditCheckMessage.getPhoneNumber());
            creditScoreRepo.save(newCreditScore);

            // send credit score to kafka
            CreditScoreReceiveMessage creditScoreReceiveMessage = new CreditScoreReceiveMessage();
            creditScoreReceiveMessage.setCreditScore(generatedScore);
            creditScoreReceiveMessage.setPhoneNumber(creditCheckMessage.getPhoneNumber());
            kafkaProducerService.sendMessage(creditScoreReceiveMessage);
        }
        else{
            // send credit score to kafka
            CreditScoreReceiveMessage creditScoreReceiveMessage = new CreditScoreReceiveMessage();
            creditScoreReceiveMessage.setCreditScore(creditScore.getCreditScore());
            creditScoreReceiveMessage.setPhoneNumber(creditCheckMessage.getPhoneNumber());
            kafkaProducerService.sendMessage(creditScoreReceiveMessage);
        }
    }
    private int generateCreditScore(double annualSalary, int numCreditCards) {
        int creditScore = 0;


        if (numCreditCards >= 2) {
            creditScore += 300;
        }

        if (annualSalary > 200000) {
            creditScore += 500;
        } else if (annualSalary > 50000) {
            creditScore += 150;
        } else {
            creditScore += 50;
        }

        return creditScore;
    }

}
