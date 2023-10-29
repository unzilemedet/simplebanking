package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.request.CreateAccountRequestDto;
import com.eteration.simplebanking.dto.response.FindAccountResponseDto;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// This class is a place holder you can change the complete implementation
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Create Account")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Created Account", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TransactionStatus.class))}), @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content)})
    @PostMapping("/create-account")
    public ResponseEntity<TransactionStatus> createAccount(@RequestBody @Valid CreateAccountRequestDto dto) {

        return ResponseEntity.ok(accountService.createAccount(dto));
    }

    @Operation(summary = "Get All Account")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Credit Success",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FindAccountResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Account Not Fount", content = @Content)})
    @GetMapping("/account-info/{accountNumber}")
    public ResponseEntity<FindAccountResponseDto> getAccount(@PathVariable("accountNumber") String accountNumber) {
        return new ResponseEntity<>(accountService.findAccountInfo(accountNumber), HttpStatus.OK);
    }

    @Operation(summary = "Debit")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Credit Success",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TransactionStatus.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable("accountNumber") String accountNumber, @RequestBody DepositTransaction transaction) {
        return new ResponseEntity<>(accountService.post(transaction, accountNumber), HttpStatus.OK);
    }

    @Operation(summary = "Debit")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Debit Success",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TransactionStatus.class))}),
            @ApiResponse(responseCode = "400", description = "Insufficient Balance", content = @Content)})
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable("accountNumber") String accountNumber, @RequestBody WithdrawalTransaction transaction) {
        return new ResponseEntity<>(accountService.post(transaction, accountNumber), HttpStatus.OK);
    }
}