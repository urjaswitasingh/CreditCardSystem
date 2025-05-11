package com.citibank.creditcardactivation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnResponse<T> {

    private String status;
    private int code;
    private String message;
    private T data;


}
