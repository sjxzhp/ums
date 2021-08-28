package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ums_role_auth")
public class RoleAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "auth_id")
    private Long authId;

    /**
     * 系统编码
     */
    @Column(name = "sys_code", length = 50)
    private String sysCode;
}
