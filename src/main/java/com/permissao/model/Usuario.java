package com.permissao.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Miguel Castro
 */
@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private boolean status;
    @Column(nullable = false)
    private Long permissao;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
