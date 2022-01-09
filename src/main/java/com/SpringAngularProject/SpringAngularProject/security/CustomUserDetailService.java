package com.SpringAngularProject.SpringAngularProject.security;

import com.SpringAngularProject.SpringAngularProject.model.User;
import com.SpringAngularProject.SpringAngularProject.service.IUserService;
import com.SpringAngularProject.SpringAngularProject.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    //Varsayılan kullanıcı servisi UserDetailsService
    @Autowired
    private IUserService userService;

    //username 'e göre kullanıcı bulunacak
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //username'e gçre kullanıcı bulunacak.
        //eğer kullanıcı yoksa exception fırlatır.
        User user=userService.findByUserName(username).
                orElseThrow(()->new UsernameNotFoundException(username));

        //Yetki tanımlaması yapılır.
        Set<GrantedAuthority> authorities=Set.of(SecurityUtils.covertToAuthority(user.getRole().name()));

        //userPrincipalî set edilir.
        return UserPrincipal.builder().user(user).id(user.getId()).
                username(username).password(user.getPassword()).authorities(authorities).build();
    }
}
