package com.example.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.common.handler.ListHandler;
import lombok.Data;

import java.util.List;

@Data
@TableName(value = "project", autoResultMap = true)
public class Project extends Model<Project> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String projectId;
    private String projectName;

    private String startTime;

    private String endTime;

    private String fundingDirect;
    private String fundingInirect;
    private String projectGrade;
    private String projectLeader;
    private String projectType;
    private String concludeTime;
    private String projectJoin;
    private String projectEnter;

//    @TableField(exist = false)
//    private String token;
//
//    /**
//     * 权限
//     */
//    @TableField(typeHandler = ListHandler.class)
//    private List<Long> role;
//
//    @TableField(exist = false)
//    private List<Long> permission;

}
