package com.citibank.creditcardactivation.repository;


import com.citibank.creditcardactivation.model.CreditScore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditScoreRepo extends MongoRepository<CreditScore, String> {
    CreditScore findByPhoneNumber(long phoneNumber);
}
