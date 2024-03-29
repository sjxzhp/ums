package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户表
 */
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

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "dep_id")
    private Long depId;
}
