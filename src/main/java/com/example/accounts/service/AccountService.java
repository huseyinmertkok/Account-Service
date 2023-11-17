package com.example.accounts.service;

import com.example.accounts.dto.CustomerDTO;

public interface AccountService {
    /**
     * Create account.
     *
     * @param customerDTO customer object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Get account.
     *
     * @param mobileNumber customer mobile number
     * @return customer and account details
     */
    CustomerDTO getAccount(String mobileNumber);

    /**
     * Update account.
     *
     * @param customerDTO customer object
     * @return if update is successful
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     * Delete account.
     *
     * @param mobileNumber mobile number of customer
     * @return if delete is successful
     */
    boolean deleteAccount(String mobileNumber);
}
