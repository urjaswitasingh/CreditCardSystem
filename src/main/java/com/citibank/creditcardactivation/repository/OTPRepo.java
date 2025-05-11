package com.citibank.creditcardactivation.repository;


import com.citibank.creditcardactivation.model.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepo extends MongoRepository<OTP, String> {
   OTP findByUserId(String userId);
    OTP deleteByUserId(String otp);
}
