package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "space_user", schema = "space")
@Schema(description = "用户表")
public class SpaceUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint unsigned not null comment '用户ID'")
    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "角色ID")
    @ManyToOne(optional = true)
    @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @jakarta.persistence.ForeignKey(name = "spaceUser_spaceRole_id_fk"))
    private SpaceRole roleId;

    @Column(name = "user_name", length = 64, columnDefinition = "varchar(64) comment '用户名'")
    @Schema(description = "用户名")
    private String userName;

    @Column(name = "user_passwd", length = 255, columnDefinition = "varchar(255) comment '用户密码'")
    @Schema(description = "用户密码")
    private String userPasswd;

    @Column(name = "user_last_time", columnDefinition = "datetime null comment '上一次登录时间'")
    @Schema(description = "上一次登录时间")
    private Date userLastTime;

}
