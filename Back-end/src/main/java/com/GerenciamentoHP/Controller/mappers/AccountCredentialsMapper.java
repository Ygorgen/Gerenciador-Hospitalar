package com.GerenciamentoHP.Controller.mappers;

import com.GerenciamentoHP.Controller.DTO.Security.AccountCredentialsDTO;
import com.GerenciamentoHP.Model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountCredentialsMapper {

    User toEntity (AccountCredentialsDTO credentialsDTO);

    AccountCredentialsDTO toDTO(User  user);
}
