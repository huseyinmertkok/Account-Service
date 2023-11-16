package com.example.accounts.controller;

import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.CustomerResponse;
import com.example.accounts.dto.ResponseDTO;
import com.example.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountsController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO("201", "Account created successfully."));
    }

    @GetMapping("/get")
    public ResponseEntity<CustomerResponse> getAccountDetails(@RequestParam String mobileNumber){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccount(mobileNumber));
    }
}
