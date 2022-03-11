package com.koreait.cloneinstagram.security;

import com.koreait.cloneinstagram.security.model.CustomUserPrincipal;
import com.koreait.cloneinstagram.user.model.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public UserEntity getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal userDetails = (CustomUserPrincipal)auth.getPrincipal();
        return userDetails.getUser();
    }

    public long getLoginUserPk() {
        return getLoginUser().getIuser();
    }
}
