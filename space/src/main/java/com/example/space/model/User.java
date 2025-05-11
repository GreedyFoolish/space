package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "almn")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "用户ID", example = "1")
    private Long id;

    @Column(name = "name")
    @Schema(description = "用户名", example = "张三")
    private String name;

    @Column(name = "auth_code")
    @Schema(description = "权限代码", example = "1000")
    private Long authCode;

}
