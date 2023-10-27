package com.eteration.simplebanking.model;


import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
public class WithdrawalTransaction extends Transaction{

    private final String type = "WithdrawalTransaction";

    public WithdrawalTransaction() {
        super(0);
    }
    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    public String getType() {
        return type;
    }
}


