package com.sz.ums.ums.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ums_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password", length = 50)
    private String password;
}
