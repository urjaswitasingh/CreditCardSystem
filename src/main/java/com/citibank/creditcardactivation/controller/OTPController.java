package com.citibank.creditcardactivation.controller;


import com.citibank.creditcardactivation.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OTPController {

    @Autowired
    OTPService otpService;
    @PostMapping("/generateOTP")
    public int generateOTP(@RequestParam String userId) {
        return otpService.generateOTP(userId);
    }

    @PostMapping("/validateOTP")
    public boolean validateOTP(@RequestParam String userId, @RequestParam int otp) {
        return otpService.validateOTP(userId, otp);
    }
}
