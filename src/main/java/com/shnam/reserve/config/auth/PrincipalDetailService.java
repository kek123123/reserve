package com.shnam.reserve.config.auth;

import com.shnam.reserve.domain.user.User;
import com.shnam.reserve.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(userId).orElse(null);

        if (user == null) {
            throw new NoSuchElementException("존재하지 않는 아이디입니다.");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(user.getUserId() ,user.getUserPwd(), grantedAuthorities);
    }
}
