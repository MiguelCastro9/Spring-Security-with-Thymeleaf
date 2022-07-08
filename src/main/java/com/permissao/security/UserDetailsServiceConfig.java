package com.permissao.security;

import com.permissao.model.Usuario;
import com.permissao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Miguel Castro
 */
public class UserDetailsServiceConfig implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepository.getUserByUsername(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Não foi possível encontrar o usuário.");
        }
        return new UserDetailsConfig(usuario);
    }

}
