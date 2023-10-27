package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
