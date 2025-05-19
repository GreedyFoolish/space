package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "space")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "用户ID", example = "1")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名", example = "张三")
    private String name;

    @Column(name = "auth_code")
    @Schema(description = "权限代码", example = "1000")
    private Long authCode;

    @Column(name = "password")
    @NotBlank(message = "密码不能为空")
    @Schema(description = "用户密码", example = "123456")
    private String password;

}
