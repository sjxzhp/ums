package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 组织类型
     */
    @Column(name = "type")
    private Integer type;

    @OneToMany(
            mappedBy = "id",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Department> departments=new ArrayList<>();
}
