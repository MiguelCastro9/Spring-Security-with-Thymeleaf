package com.permissao.model;

import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Miguel Castro
 */
@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

}
