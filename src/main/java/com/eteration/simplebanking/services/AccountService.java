package com.eteration.simplebanking.services;


import com.eteration.simplebanking.exception.ErrorType;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;



import com.eteration.simplebanking.repository.IAccountRepository;
import org.springframework.stereotype.Service;


// This class is a place holder you can change the complete implementation
@Service
public class AccountService {
    private final IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account findAccount(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public void save(Account account){
        accountRepository.save(account);
    }

}
