package com.permissao.repository;

import com.permissao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Miguel Castro
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario getUserByUsername(@Param("email") String email);

}
