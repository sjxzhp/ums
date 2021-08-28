package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ums_user_organization")
public class UserOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "user_id")
    private Long userId;

    @Column(name = "org_id")
    private Long orgId;

    /**
     * 组织类型
     */
    @Column(name = "org_type")
    private Integer orgType;
}
