package com.example.accounts.service.impl;

import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.Customer;
import com.example.accounts.exception.CustomerAlreadyExistsException;
import com.example.accounts.mapper.AccountsMapper;
import com.example.accounts.mapper.CustomerMapper;
import com.example.accounts.repository.AccountsRepository;
import com.example.accounts.repository.CustomerRepository;
import com.example.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * Create account.
     *
     * @param customerDTO Customer object.
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
