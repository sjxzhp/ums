package com.sz.ums.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ums_user_position")
public class UserPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "position_id")
    private Long positionId;
}
