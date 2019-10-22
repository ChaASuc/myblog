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

    private Set<GrantedAuthority> authorities;

    private Set<EpUserDetialsPermission> permissions;

    //账号是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //账号凭证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

