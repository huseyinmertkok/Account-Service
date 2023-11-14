package com.example.accounts.mapper;

import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper {
    CustomerDTO customerToDTO(Customer customer);

    Customer dtoToCustomer(CustomerDTO customerDTO);
}
