package com.cm.bbuserapi.dto;

import com.cm.bbuserapi.enums.UserStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Getter
@Setter
public class UserDto implements UserDetails, Serializable {
    private Long id;
    private String name;
    private String imgUrl;
    private String userName;
    private String email;
    private String phone;
    private String profilePic;
    private UserStatus status;

    private String password;
    private Date createDateTime;
    private Date updateDateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
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
