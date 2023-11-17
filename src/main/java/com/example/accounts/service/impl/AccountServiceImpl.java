package com.example.accounts.service.impl;

import com.example.accounts.dto.AccountsDTO;
import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.Customer;
import com.example.accounts.exception.CustomerAlreadyExistsException;
import com.example.accounts.exception.ResourceNotFoundException;
import com.example.accounts.mapper.AccountsMapper;
import com.example.accounts.mapper.CustomerMapper;
import com.example.accounts.repository.AccountsRepository;
import com.example.accounts.repository.CustomerRepository;
import com.example.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * Create account.
     *
     * @param customerDTO customer object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        if (customerRepository.findByMobileNumber(customerDTO.getMobileNumber()).isPresent()){
            throw new CustomerAlreadyExistsException("Customer with given mobile number is already exist");
        }
        // TODO: mapper not working
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("admin");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * Get account.
     *
     * @param mobileNumber customer mobile number
     * @return customer and account details
     */
    @Override
    public CustomerDTO getAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
        customerDTO.setAccountsDTO(AccountsMapper.INSTANCE.accountsToaccountsDTO(accounts));
        return customerDTO;
    }

    /**
     * Update account.
     *
     * @param customerDTO customer object
     * @return if update is successful
     */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        if(!Objects.isNull(accountsDTO)){
            Accounts accounts = accountsRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountsDTO.getAccountNumber().toString())
            );
            AccountsMapper.INSTANCE.updateAccounts(accounts, accountsDTO);
            accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerID", customerId.toString())
            );
            CustomerMapper.INSTANCE.updateCustomer(customer, customerDTO);
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    /**
     * Delete account.
     *
     * @param mobileNumber mobile number of customer
     * @return if delete is successful
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
     * Create new account for newly created user.
     *
     * @param customer customer
     * @return created account
     */
    private Accounts createNewAccount(Customer customer){
        Accounts accounts = Accounts.builder()
                .customerId(customer.getCustomerId())
                .accountNumber(1000000000L + new Random().nextInt(900000000))
                .accountType("Savings")
                .branchAddress("123 Main Street, New York")
                .build();
        accounts.setCreatedAt(customer.getCreatedAt());
        accounts.setCreatedBy(customer.getCreatedBy());
        return accounts;
    }
}
