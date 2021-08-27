package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 组织表
 */
@Data
@Entity
@Table(name = "ums_organization")
public class Organization {

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
     * 组织类型
     */
    @Column(name = "type")
    private Integer type;
}
