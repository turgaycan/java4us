/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.service.auth;

import com.java4us.domain.User;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author TCAN
 */
public class UserDetailsAdapter extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = -3124540462615550359L;
	private final Long id;

    public UserDetailsAdapter(User userEntity, List<GrantedAuthority> authorities) {
        super(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled(), true, true, true, authorities);
        this.id = userEntity.getId();
    }

    public Long getId() {
        return id;
    }
}
