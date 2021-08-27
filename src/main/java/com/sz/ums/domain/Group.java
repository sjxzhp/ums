package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户组表
 */
@Data
@Entity
@Table(name = "ums_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

}
