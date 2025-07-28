package com.br.spring_security.auth.service;

import com.br.spring_security.auth.entity.PendingUser;

public interface IPendingUserService {
    void save(PendingUser pedingUser);
    PendingUser findByEmailAndCode(String email, String code);
    void deleteAllByEmail(String email);
}
