package com.example.accounts.mapper;

import com.example.accounts.dto.AccountsDTO;
import com.example.accounts.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AccountsMapper {
    AccountsDTO accountsToDTO(Accounts accounts);

    Accounts dtoToAccounts(AccountsDTO accountsDTO);
}
