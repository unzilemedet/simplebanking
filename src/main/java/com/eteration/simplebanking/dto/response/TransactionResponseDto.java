package com.eteration.simplebanking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponseDto {
    private String date;
    private Double amount;
    private String type;
    private String approvalCode;
}
