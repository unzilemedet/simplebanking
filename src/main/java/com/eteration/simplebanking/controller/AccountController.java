package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is a place holder you can change the complete implementation
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;



    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") String accountNumber) {
        return new ResponseEntity<>(accountService.findAccount(accountNumber), HttpStatus.OK);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable("accountNumber") String accountNumber, @RequestBody DepositTransaction transaction
    ) throws InsufficientBalanceException {

        Account account = accountService.findAccount(accountNumber);
        account.post(transaction);
        accountService.save(account);
        String approvalCode = transaction.getApprovalCode().toString();
        return new ResponseEntity<>(new TransactionStatus("OK", approvalCode), HttpStatus.OK);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable("accountNumber") String accountNumber,
                                                   @RequestBody WithdrawalTransaction transaction
    ) throws InsufficientBalanceException {

        Account account = accountService.findAccount(accountNumber);
        account.post(transaction);
        accountService.save(account);
        String approvalCode = transaction.getApprovalCode().toString();
        return new ResponseEntity<>(new TransactionStatus("OK",approvalCode), HttpStatus.OK);
    }
}