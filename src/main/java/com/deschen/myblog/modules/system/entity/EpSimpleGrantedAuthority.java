package com.deschen.myblog.modules.system.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Author deschen
 * @Create 2019/10/12
 * @Description 自定义角色类
 * @Since 1.0.0
 */
@Data
public class EpSimpleGrantedAuthority implements GrantedAuthority {
    private Long roleId;

    private String role;


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof EpSimpleGrantedAuthority) {
            return role.equals(((EpSimpleGrantedAuthority) obj).role);
        }

        return false;
    }

    public int hashCode() {
        return this.role.hashCode();
    }

    public String toString() {
        return this.role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
