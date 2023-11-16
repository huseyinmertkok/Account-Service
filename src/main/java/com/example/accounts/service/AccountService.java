package com.example.accounts.service;

import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.CustomerResponse;

public interface AccountService {
    /**
     * Create account.
     * @param customerDTO Customer object.
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Get account.
     * @param mobileNumber Customer mobile number.
     */
    CustomerResponse getAccount(String mobileNumber);
}
