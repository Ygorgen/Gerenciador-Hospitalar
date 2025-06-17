package com.GerenciamentoHP.Controller.Docs;

import com.GerenciamentoHP.Controller.DTO.Security.AccountCredentialsDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface AuthControllerDocs {
    @Operation(summary = "Authenticates an user and retuerns a token")
    ResponseEntity<?> signin(AccountCredentialsDTO credentials);

    @Operation(summary = "Refresh token for Authenticated user and retuerns a token")
    ResponseEntity<?> refreshToken(
            String username,
            String refreshToken);

    AccountCredentialsDTO create(AccountCredentialsDTO credentials);
}
