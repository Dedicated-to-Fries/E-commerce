package com.example.ecommerce.security.plugin;

import com.example.ecommerce.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Title: UserDetail
 * @Author 杨金鹏
 * @Package com.example.ecommerce.security.plugin
 * @Date 2024/1/17 19:43
 */


@Getter
@ToString
public class UserDetail extends User {

    private UserEntity userEntity;


    public UserDetail(UserEntity userEntity, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getUsername(),userEntity.getPassword(), authorities);
        this.userEntity=userEntity;
    }
}
