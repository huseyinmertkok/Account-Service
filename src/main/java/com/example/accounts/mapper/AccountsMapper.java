package com.example.accounts.mapper;

import com.example.accounts.dto.AccountsDTO;
import com.example.accounts.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountsMapper {

    AccountsMapper INSTANCE = Mappers.getMapper(AccountsMapper.class);

    AccountsDTO accountsToaccountsDTO(Accounts accounts);

    Accounts accountsDTOToAccounts(AccountsDTO accountsDTO);
}
