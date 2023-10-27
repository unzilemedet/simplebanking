package com.eteration.simplebanking.model;


import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
public class DepositTransaction  extends Transaction{
    private final String type = "DepositTransaction";

    public DepositTransaction() {
        super(0);
    }

    public DepositTransaction(double amount) {
        super(amount);
    }

    public String getType() {
        return type;
    }
}
