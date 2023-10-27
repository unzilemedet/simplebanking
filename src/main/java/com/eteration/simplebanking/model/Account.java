package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.exception.ErrorType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    @Column(unique = true)
    private String accountNumber;
    private double balance;
    @Builder.Default
    private LocalDate createDate= LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();



    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if(amount > this.balance){
            throw new InsufficientBalanceException(ErrorType.INSUFFICIENT_CURRENCY);
        }
        this.balance -= amount;
    }
    public void deposit(double amount) {
        this.balance += amount;
    }
    public void post(Transaction transaction) throws InsufficientBalanceException {
        if(transaction instanceof DepositTransaction){
            DepositTransaction dt = (DepositTransaction) transaction;
            this.deposit(dt.getAmount());
        }
        if(transaction instanceof WithdrawalTransaction){
            WithdrawalTransaction wt = (WithdrawalTransaction) transaction;
            this.withdraw(wt.getAmount());
        }

        this.transactions.add(transaction);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", createDate=" + createDate +
                ", transactions=" + transactions +
                '}';
    }
}
