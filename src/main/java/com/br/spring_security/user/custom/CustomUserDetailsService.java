package com.br.spring_security.security.custom;

import com.br.spring_security.user.entity.User;
import com.br.spring_security.user.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Getter @Setter
@Service
public class CustomUserDetailsService implements UserDetailsService
{
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Erro no servidor, volte mais tarde"));

        return new CustomUserDetails(user);
    }
}
