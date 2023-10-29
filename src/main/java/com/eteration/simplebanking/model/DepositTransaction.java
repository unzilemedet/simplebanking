package com.eteration.simplebanking.model;


import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
public class DepositTransaction extends Transaction {

    public DepositTransaction() {
        super(0, TransactionConstant.DEPOSIT_TRANSACTION);
    }

    public DepositTransaction(double amount) {
        super(amount, TransactionConstant.DEPOSIT_TRANSACTION);
    }

    @Override
    public void post(Account account) {
        account.deposit(this.getAmount());
        this.setAccount(account);
    }

}
