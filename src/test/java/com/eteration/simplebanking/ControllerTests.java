package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.dto.request.CreateAccountRequestDto;
import com.eteration.simplebanking.dto.response.FindAccountResponseDto;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ControllerTests {


    @Autowired
    private AccountController controller;

    @Test
    public void givenId_Credit_thenReturnJson() {
        CreateAccountRequestDto dto = new CreateAccountRequestDto();
        dto.setAccountNumber("17892");
        dto.setOwner("Unzile Medet");
        controller.createAccount(dto);
        controller.credit("17892", new DepositTransaction(1000.0));
        ResponseEntity<FindAccountResponseDto> findAccountResponseDtoResponseEntity = controller.getAccount("17892");
        assertEquals(1000.0, findAccountResponseDtoResponseEntity.getBody().getBalance(), 0.001);

    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson() {

        CreateAccountRequestDto dto = new CreateAccountRequestDto();
        dto.setAccountNumber("17893");
        dto.setOwner("Unzile Medet");
        controller.createAccount(dto);

        ResponseEntity<TransactionStatus> result = controller.credit("17893", new DepositTransaction(1000.0));
        ResponseEntity<TransactionStatus> result2 = controller.debit("17893", new WithdrawalTransaction(50.0));
        ResponseEntity<FindAccountResponseDto> findAccountResponseDtoResponseEntity = controller.getAccount("17893");

        assertEquals("OK", result.getBody().getStatus().toUpperCase());
        assertEquals("OK", result2.getBody().getStatus().toUpperCase());
        assertEquals(950.0, findAccountResponseDtoResponseEntity.getBody().getBalance(), 0.001);
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
            throws Exception {
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            CreateAccountRequestDto dto = new CreateAccountRequestDto();
            dto.setAccountNumber("17894");
            dto.setOwner("Unzile Medet");
            controller.createAccount(dto);
            ResponseEntity<TransactionStatus> result = controller.credit("17894", new DepositTransaction(1000.0));
            ResponseEntity<FindAccountResponseDto> findAccountResponseDtoResponseEntity = controller.getAccount("17894");
            assertEquals("OK", result.getBody().getStatus().toUpperCase());
            assertEquals(1000.0, findAccountResponseDtoResponseEntity.getBody().getBalance(), 0.001);

            ResponseEntity<TransactionStatus> result2 = controller.debit("17894", new WithdrawalTransaction(5000.0));
        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson()
            throws Exception {
        CreateAccountRequestDto dto = new CreateAccountRequestDto();
        dto.setAccountNumber("17895");
        dto.setOwner("Unzile Medet");
        controller.createAccount(dto);
        ResponseEntity<FindAccountResponseDto> result = controller.getAccount("17895");
        assertEquals(dto.getAccountNumber(), result.getBody().getAccountNumber());
    }

}