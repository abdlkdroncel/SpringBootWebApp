package com.SpringAngularProject.SpringAngularProject.service;

import com.SpringAngularProject.SpringAngularProject.security.jwt.IJwtProvider;
import com.SpringAngularProject.SpringAngularProject.model.User;
import com.SpringAngularProject.SpringAngularProject.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJwtProvider jwtProvider;

    public User signInReturnJWT(User signInRequest){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword())
        );

        UserPrincipal userPrincipal= (UserPrincipal) authentication.getPrincipal();
        String jwt=jwtProvider.generateToken(userPrincipal);

        User singInUser=userPrincipal.getUser();
        singInUser.setToken(jwt);

        return singInUser;

    }
}
