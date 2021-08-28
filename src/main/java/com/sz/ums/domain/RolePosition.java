package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ums_role_position")
public class RolePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "position_id")
    private Long positionId;

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
