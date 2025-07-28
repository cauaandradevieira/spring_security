package com.br.spring_security.auth.service.imp;

import com.br.spring_security.auth.entity.PendingUser;
import com.br.spring_security.auth.repository.PendingUserRepository;
import com.br.spring_security.auth.service.IPendingUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PendingUserService implements IPendingUserService
{
    private final PendingUserRepository pedingUserRepository;

    @Override
    @Transactional
    public void save(PendingUser pedingUser) {
        pedingUserRepository.save(pedingUser);
    }

    @Override
    public PendingUser findByEmailAndCode(String email, String code) {
        return pedingUserRepository.findByEmailAndCode(email, code)
                .orElseThrow(() -> new IllegalArgumentException("Codigo invalido ou Expiração do codigo, gere um novo."));
    }

    @Override
    @Transactional
    public void deleteAllByEmail(String email) {
        pedingUserRepository.deleteAllByEmail(email);
    }
}
