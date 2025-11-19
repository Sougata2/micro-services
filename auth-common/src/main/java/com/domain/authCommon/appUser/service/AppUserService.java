package com.domain.authCommon.appUser.service;

import com.domain.authCommon.appUser.details.AppUser;
import com.domain.authCommon.appUser.entity.UserEntity;
import com.domain.authCommon.appUser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.findByEmail(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return new AppUser(user.get());
    }
}
