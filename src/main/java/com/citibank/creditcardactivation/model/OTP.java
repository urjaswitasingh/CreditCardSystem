package com.citibank.creditcardactivation.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class OTP {
    private int otp;
    @Id
    private String userId;



}
