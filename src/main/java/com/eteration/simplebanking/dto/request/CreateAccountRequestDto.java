package com.eteration.simplebanking.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccountRequestDto {

    @NotBlank(message = "Owner can not be null!")
    private String owner;

    @Size(min = 7, max = 7, message ="accountNumber must be 7 char!")
    @NotBlank(message = "accountNumber can not be null!")
    private String accountNumber;
}
