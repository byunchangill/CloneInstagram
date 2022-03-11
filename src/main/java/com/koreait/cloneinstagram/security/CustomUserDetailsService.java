package com.koreait.cloneinstagram.security;

import com.koreait.cloneinstagram.security.model.CustomUserPrincipal;
import com.koreait.cloneinstagram.user.UserMapper;
import com.koreait.cloneinstagram.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity param = new UserEntity();
        param.setEmail(email);
        param.setProvider(ProviderType.LOCAL.toString());

        UserEntity savedUser = mapper.findByEmail(param);
        if(savedUser == null) {
            throw new UsernameNotFoundException(" Can not find user. ");
        }
        return CustomUserPrincipal.create(savedUser);
    }
}
