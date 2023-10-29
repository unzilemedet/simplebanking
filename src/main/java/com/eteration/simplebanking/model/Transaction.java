package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

// This class is a place holder you can change the complete implementation
@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String type;
    private LocalDateTime date;
    private UUID approvalCode = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account_number", nullable = false, referencedColumnName = "account_number")
    private Account account;

    private double amount;

    public Transaction(double amount, String type) {
        this();
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", approvalCode=" + approvalCode +
                ", amount=" + amount +
                '}';
    }

    public abstract void post(Account account);
}
