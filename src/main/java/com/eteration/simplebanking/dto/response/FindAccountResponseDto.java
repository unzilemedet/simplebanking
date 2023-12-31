package com.eteration.simplebanking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAccountResponseDto {
    private String accountNumber;
    private String owner;
    private double balance;
    private LocalDate createDate;
    private List<TransactionResponseDto> transactions;

    public FindAccountResponseDto(String accountNumber, String owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
    }
}
