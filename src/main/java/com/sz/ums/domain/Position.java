package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 身份表（职位）
 */
@Data
@Entity
@Table(name = "ums_position")
public class Position {
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
     * 组织id
     */
    @Column(name = "org_id")
    private Long orgId;

}
