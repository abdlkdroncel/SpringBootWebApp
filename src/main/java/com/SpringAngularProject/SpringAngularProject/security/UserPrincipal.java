package com.SpringAngularProject.SpringAngularProject.security;

import com.SpringAngularProject.SpringAngularProject.model.Role;
import com.SpringAngularProject.SpringAngularProject.model.User;
import com.SpringAngularProject.SpringAngularProject.util.SecurityUtils;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    transient private String password;
    transient private User user;
    private Set<GrantedAuthority> authorities;

    public static UserPrincipal createSuperUsser(){
        Set<GrantedAuthority> authorities=Set.of(SecurityUtils.covertToAuthority(Role.SYSTEM_MANAGER.name()));

        return UserPrincipal.builder()
        .id(-1L).username("system administrator").
                authorities(authorities).
                build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
