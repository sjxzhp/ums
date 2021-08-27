package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色表
 */
@Data
@Entity
@Table(name = "ums_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "first_id")
    private Long firstId;

    /**
     * 系统编码
     */
    @Column(name = "sys_code", length = 50)
    private String sysCode;

}
