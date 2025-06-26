package com.GerenciamentoHP.Services;

import com.GerenciamentoHP.Controller.DTO.Security.AccountCredentialsDTO;
import com.GerenciamentoHP.Controller.DTO.Security.TokenDTO;
import com.GerenciamentoHP.Controller.mappers.AccountCredentialsMapper;
import com.GerenciamentoHP.Exceptions.RequiredObjectIsNullException;
import com.GerenciamentoHP.Model.Permission;
import com.GerenciamentoHP.Model.User;
import com.GerenciamentoHP.Repository.PermissionRepository;
import com.GerenciamentoHP.Repository.UserRepository;
import com.GerenciamentoHP.Security.Jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private AccountCredentialsMapper mapper;

    Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<TokenDTO> signIn(AccountCredentialsDTO credentials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                )
        );

        var user = userRepository.findByUsername(credentials.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Username " + credentials.getUsername() + " not found!");
        }

        var token = tokenProvider.createAccessToken(
                credentials.getUsername(),
                user.getRoles()
        );
        return ResponseEntity.ok(token);
    }

    public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken) {
        var user = userRepository.findByUsername(username);
        TokenDTO token;
        if (user != null) {
            token = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(token);
    }

    public AccountCredentialsDTO create(AccountCredentialsDTO user) {
        if (user == null) {
            throw new RequiredObjectIsNullException();
        }
        logger.info("Creating a new user!");

        User entity = new User();
        entity.setFullName(user.getFullname());
        entity.setUserName(user.getUsername());
        entity.setPassword(generateHashedPassword(user.getPassword()));
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setEnabled(true);

        // 🔐 Adiciona a permissão COMMON_USER
        Permission commonUserPermission = permissionRepository.findByDescription("COMMON_USER")
                .orElseThrow(() -> new RuntimeException("Permissão 'COMMON_USER' não encontrada"));

        entity.setPermissions(List.of(commonUserPermission));

        var savedUser = userRepository.save(entity);

        return new AccountCredentialsDTO(savedUser.getUsername(), savedUser.getPassword(), savedUser.getFullName());
    }

    private String generateHashedPassword(String password) {

        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
                "", 8, 185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
        return passwordEncoder.encode(password);
    }

 }
