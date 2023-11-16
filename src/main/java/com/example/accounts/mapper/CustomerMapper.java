package com.example.accounts.mapper;

import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.CustomerResponse;
import com.example.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    CustomerResponse customerToCustomerResponse(Customer customer);
}
