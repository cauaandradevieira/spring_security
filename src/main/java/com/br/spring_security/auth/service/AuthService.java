package com.br.spring_security.auth.service;

import com.br.spring_security.auth.dto.request.ConfirmRegistrationRequest;
import com.br.spring_security.auth.dto.request.LoginRequestDTO;
import com.br.spring_security.auth.dto.request.RegisterRequestDTO;
import com.br.spring_security.auth.dto.response.JwtReponseDTO;
import com.br.spring_security.auth.entity.PendingUser;
import com.br.spring_security.auth.exception.EmailAlreadyInUseException;
import com.br.spring_security.notification.sender.EmailSender;
import com.br.spring_security.role.entity.Role;
import com.br.spring_security.user.entity.User;
import com.br.spring_security.utils.CodeGenerator;
import com.br.spring_security.user.custom.CustomUserDetails;
import com.br.spring_security.role.repository.RoleRepository;
import com.br.spring_security.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService
{
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailSender emailSender;
    private final IPendingUserService pedingUserService;
    private final CodeGenerator codeGenerator;

    @Transactional
    public String register(@Valid RegisterRequestDTO registerRequestDTO)
    {
        log.info("Iniciando o metodo register");
        String email = registerRequestDTO.email();

        if(userRepository.existsByEmail(email))
        {
            throw new EmailAlreadyInUseException("Já existe uma conta associada a este e-mail.", email);
        }

        String passwordHash = passwordEncoder.encode(registerRequestDTO.password());

        String code = codeGenerator.generateSixDigitCode();

        Instant instant = Instant.now().plus(Duration.ofHours(3));

        PendingUser pedingUser = new PendingUser(
                registerRequestDTO,
                passwordHash,
                code,
                instant);

        pedingUserService.save(pedingUser);

        emailSender.sendRegistrationVerificationCode(
                email,
                code,
                pedingUser.getName(),
                instant);

        log.info("Acabou o metodo register");
        return email;
    }

    public void confirmRegistration(ConfirmRegistrationRequest request)
    {
        PendingUser pendingUser = pedingUserService.findByEmailAndCode(request.email(), request.confirmationCode());

        pedingUserService.deleteAllByEmail(request.email());

        Role role = roleRepository.findByName("ROLE_BASIC")
                .orElseThrow(() -> new EntityNotFoundException("Erro no sistema tente mais tarde."));

        User user = new User(pendingUser, role);
        userRepository.save(user);

        emailSender.sendRegistrationConfirm(
                user.getEmail(),
                user.getName());
    }

    public JwtReponseDTO login(@Valid LoginRequestDTO loginDTO)
    {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.email(),
                loginDTO.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(customUserDetails, new HashMap<>());

        return JwtReponseDTO.builder()
                .token(token)
                .username(customUserDetails.getUsername())
                .tokenType("Bearer")
                .build();
    }

    public void logout() // parametro cookie e token de acess
    {
        // pegar o token de acesso e colocar na blacklist
        // pegar o refresh token e colocar na blacklist
        // setar o Cookie do refresh para expirado
    }

    public void refreshToken(String cookie)
    {
        // validar o cookie ( se ele existe ou se ele ja estar expirado )
        // verificar se estar na blacklist
        // extrair o refresh token pelo valor do cookie ( acho q n precisa )
        // extrair o id do usuário
        // pegar o usuário do banco de dados
        // validar o refresh token
        // colocar o token acesso antigo na blacklist
        // colocar o refresh token antigo na blacklist
        // gerar outro token de acesso
        // gerar outro refresh token
        // retornar o token de acesso e refresh token como cookie http only
    }
}
