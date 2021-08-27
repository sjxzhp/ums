package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 系统表
 */
@Data
@Entity
@Table(name = "win_system")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sys_name", length = 50)
    private String sysName;

    @Column(name = "sys_code", length = 50)
    private String sysCode;
}
