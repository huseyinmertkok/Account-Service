package com.example.accounts.dto;

import lombok.Data;

@Data
public class CustomerResponse {
    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDTO accountsDTO;
}
