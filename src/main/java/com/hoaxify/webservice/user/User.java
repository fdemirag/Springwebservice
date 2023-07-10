package com.hoaxify.webservice.user;


import jakarta.persistence.*;
import lombok.Data;

import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String displayName;

    private String password;
}
