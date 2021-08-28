package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ums_role_group")
public class RoleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "group_id")
    private Long groupId;

    /**
     * 数据权限组织id
     */
    @Column(name = "data_auth_id")
    private Long dataAuthId;

    /**
     * 系统编码
     */
    @Column(name = "sys_code", length = 50)
    private String sysCode;
}
