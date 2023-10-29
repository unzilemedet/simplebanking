package com.eteration.simplebanking.services;


import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.dto.request.CreateAccountRequestDto;
import com.eteration.simplebanking.dto.response.FindAccountResponseDto;
import com.eteration.simplebanking.exception.CustomGenericException;
import com.eteration.simplebanking.exception.ErrorType;
import com.eteration.simplebanking.mapper.IMapper;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.IAccountRepository;
import com.eteration.simplebanking.repository.ITransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


// This class is a place holder you can change the complete implementation
@Service
public class AccountService {
    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    public AccountService(IAccountRepository accountRepository, ITransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public FindAccountResponseDto findAccountInfo(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new CustomGenericException(ErrorType.ACCOUNT_NOT_FOUND);
        }
        return IMapper.INSTANCE.fromAccountToFindAccountResponsedto(account);

    }


    @Transactional
    public TransactionStatus createAccount(CreateAccountRequestDto dto) {
        accountRepository.save(IMapper.INSTANCE.fromCreateAccountRequestDtoToAccount(dto));
        return new TransactionStatus("ok", UUID.randomUUID().toString());
    }

    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);

    }

    @Transactional(rollbackOn = InsufficientBalanceException.class)
    public TransactionStatus post(Transaction transaction, String accountNumber) throws InsufficientBalanceException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        transaction.post(account);
        transactionRepository.save(transaction);
        return new TransactionStatus("ok", transaction.getApprovalCode().toString());
    }
}
