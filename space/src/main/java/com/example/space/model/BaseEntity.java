package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Map;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity {

    @Column(name = "create_by", columnDefinition = "bigint unsigned null comment '创建者'")
    @Schema(description = "创建者")
    private Long createBy;

    @Column(name = "update_by", columnDefinition = "bigint unsigned null comment '更新者'")
    @Schema(description = "更新者")
    private Long updateBy;

    @Column(name = "remark", columnDefinition = "varchar(255) null comment '备注'")
    @Schema(description = "备注")
    private String remark;

    @Column(name = "status", columnDefinition = "int default 0 not null comment '状态标志，0-正常，1-删除。默认值为0'")
    @Schema(description = "状态标志，0-正常，1-删除。默认值为0", example = "0")
    private int status = 0;

    @CreatedDate
    @Column(name = "create_time", columnDefinition = "datetime default CURRENT_TIMESTAMP null comment '创建时间'")
    @Schema(description = "创建时间")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time", columnDefinition = "datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'")
    @Schema(description = "更新时间")
    private Date updateTime;

    @Column(name = "delete_time", columnDefinition = "datetime null comment '删除时间'")
    @Schema(description = "删除时间")
    private Date deleteTime;

    @Transient
    @Schema(description = "请求参数")
    private Map<String, Object> params;

    /**
     * 数据库更新时，如果status为1，则将deleteTime设置为当前时间
     */
    @PreUpdate
    protected void onUpdate() {
        if (status == 1) {
            deleteTime = new Date();
        }
    }

}
