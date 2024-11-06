package com.clientapi.security;

import com.clientapi.model.UserEntity;
import com.clientapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = Optional.ofNullable(userRepository.findByUsername(username))
                .orElse(findByToken(username));
        if (user != null) {
            List<SimpleGrantedAuthority> authorities = Optional.ofNullable(user.getRoles())
                    .map(roles -> roles.split(","))
                    .map(Arrays::stream)
                    .map(stream -> stream.map(v -> "ROLE_" + v).map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                    .orElse(List.of());

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
    }

    private UserEntity findByToken(String username) {
        return Optional.ofNullable(userRepository.findByToken(username))
                .map(user -> user.withName(user.getToken()))
                .orElse(null);
    }
}
