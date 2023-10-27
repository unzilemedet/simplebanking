package com.eteration.simplebanking.model;

import lombok.Getter;

import javax.persistence.Entity;
@Getter
@Entity
public class PhoneBillPaymentTransaction extends WithdrawalTransaction{

    private String operator;
    private String phoneNumber;


    public PhoneBillPaymentTransaction() {
        super(0);
    }
    public PhoneBillPaymentTransaction(String operator, String phoneNumber, double amount) {
        super(amount);
        this.operator = operator;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PhoneBillPaymentTransaction{" +
                "operator='" + operator + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}' + super.toString();
    }
}
