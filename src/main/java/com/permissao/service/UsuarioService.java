package com.permissao.service;

import com.permissao.model.Usuario;
import com.permissao.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel Castro
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> list() {

        return usuarioRepository.findAll();
    }

    public Usuario update(Long id) {

        return usuarioRepository.getReferenceById(id);
    }

    public void delete(Long id) {

        usuarioRepository.deleteById(id);
    }
}
