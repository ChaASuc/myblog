package com.deschen.myblog.modules.system.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Author deschen
 * @Create 2019/10/11
 * @Description
 * @Since 1.0.0
 */
@Data
public class EpUserDetails implements Serializable, UserDetails {

    private Long userId;

    private String username;

    private String password;

//    private Set<Example> examples;

    private Set<SimpleGrantedAuthority> authorities;

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

@Data
class Example {
    private String role;
}

