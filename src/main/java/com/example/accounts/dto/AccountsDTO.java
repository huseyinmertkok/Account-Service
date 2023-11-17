package com.example.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDTO {
    @NotEmpty(message = "Account number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
