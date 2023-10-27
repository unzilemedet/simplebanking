package com.eteration.simplebanking.controller;


import lombok.Getter;

// This class is a place holder you can change the complete implementation
@Getter
public class TransactionStatus {
    private String status;
    private String approvalCode;


    public TransactionStatus(String status, String approvalCode) {
        this.status = status;
        this.approvalCode = approvalCode;
    }



}
