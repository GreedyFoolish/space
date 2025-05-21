package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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

    @Column(name = "create_by")
    @Schema(description = "创建者")
    private Long createBy;

    @Column(name = "update_by")
    @Schema(description = "更新者")
    private Long updateBy;

    @Column(name = "remark")
    @Schema(description = "备注")
    private String remark;

    @Column(name = "status")
    @Schema(description = "状态标志，0 正常 1 删除", example = "0")
    private Integer status = 0;

    @CreatedDate
    @Column(name = "create_time")
    @Schema(description = "创建时间")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    @Schema(description = "更新时间")
    private Date updateTime;

    @Column(name = "delete_time")
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
        if (status != null && status.equals(1)) {
            deleteTime = new Date();
        }
    }

}
