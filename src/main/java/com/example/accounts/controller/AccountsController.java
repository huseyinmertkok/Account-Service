package com.example.accounts.controller;

import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.ResponseDTO;
import com.example.accounts.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@AllArgsConstructor
public class AccountsController {
    private final AccountService accountService;

    /**
     * Post request for creating customer and account .
     *
     * @param customerDTO customer being created
     * @return a ResponseEntity for the response to use
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO("201", "Account created successfully."));
    }

    /**
     * Get request for getting account and user details.
     *
     * @param mobileNumber mobile number of the customer
     * @return a ResponseEntity with the body of customer and account details for the response to use
     */
    @GetMapping("/get")
    public ResponseEntity<CustomerDTO> getAccountDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccount(mobileNumber));
    }

    /**
     * Put request for updating customer and account details.
     *
     * @param customerDTO customer being updated
     * @return a ResponseEntity with the body of customer and account details for the response to use
     */
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO) {
        if (accountService.updateAccount(customerDTO)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("200", "Request processed successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("400", "An error occurred during request"));
        }
    }

    /**
     * Delete request for deleting the customer and customer's account
     *
     * @param mobileNumber mobile number of the customer
     * @return a ResponseEntity for the response to use
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        if (accountService.deleteAccount(mobileNumber)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("200", "Request processed successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("400", "An error occurred during request"));
        }
    }
}
