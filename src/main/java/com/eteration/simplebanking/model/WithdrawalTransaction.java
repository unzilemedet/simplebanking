package com.eteration.simplebanking.model;


import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
public class WithdrawalTransaction extends Transaction {


    public WithdrawalTransaction() {
        super(0, TransactionConstant.WITDRAW_TRANSACTION);
    }

    public WithdrawalTransaction(double amount) {
        super(amount, TransactionConstant.WITDRAW_TRANSACTION);
    }

    @Override
    public void post(Account account) {
        account.withdraw(this.getAmount());
        this.setAccount(account);
    }

}


