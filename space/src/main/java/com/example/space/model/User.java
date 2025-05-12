package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "space")
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

    @Column(name = "password")
    @Schema(description = "用户密码", example = "123456")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20位之间")
    private String password;

}
