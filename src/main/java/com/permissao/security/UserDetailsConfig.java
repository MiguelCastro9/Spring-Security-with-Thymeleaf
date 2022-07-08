package com.permissao.security;

import com.permissao.model.Role;
import com.permissao.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Miguel Castro
 */
public class UserDetailsConfig implements UserDetails {

    private Usuario usuario;

    public UserDetailsConfig(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<Role> roles = usuario.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNome()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {

        return usuario.getEmail();
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

        return usuario.isStatus();
    }

}
