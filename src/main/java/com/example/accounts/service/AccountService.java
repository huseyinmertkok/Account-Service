package com.example.accounts.service;

import com.example.accounts.dto.CustomerDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountService {
    /**
     * Create account.
     * @param customerDTO Customer object.
     */
    void createAccount(@RequestBody CustomerDTO customerDTO);
}
