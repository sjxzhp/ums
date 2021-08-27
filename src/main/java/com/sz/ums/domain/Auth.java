package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 权限表
 */
@Data
@Entity
@Table(name = "ums_auth")
public class Auth {

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
     * 系统路径
     */
    @Column(name = "sys_code", length = 50)
    private String sysCode;

    /**
     * 菜单路径
     */
    @Column(name = "menu_url", length = 50)
    private String menuUrl;

    /**
     * 排序
     */
    @Column(name = "order_number")
    private Integer orderNumber;
}
