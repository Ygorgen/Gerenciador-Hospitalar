package com.GerenciamentoHP.Controller.mappers;

import com.GerenciamentoHP.Controller.DTO.Security.AccountCredentialsDTO;
import com.GerenciamentoHP.Model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T21:11:41-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class AccountCredentialsMapperImpl implements AccountCredentialsMapper {

    @Override
    public User toEntity(AccountCredentialsDTO credentialsDTO) {
        if ( credentialsDTO == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( credentialsDTO.getPassword() );

        return user;
    }

    @Override
    public AccountCredentialsDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        AccountCredentialsDTO accountCredentialsDTO = new AccountCredentialsDTO();

        accountCredentialsDTO.setUsername( user.getUsername() );
        accountCredentialsDTO.setPassword( user.getPassword() );

        return accountCredentialsDTO;
    }
}
