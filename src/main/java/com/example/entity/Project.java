package com.example.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.common.handler.ListHandler;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@TableName(value = "project", autoResultMap = true)
@ToString
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

    private Float fundingDirect;
    private Float fundingIndirect;
    private String projectGrade;
    private String projectLeader;
    private String projectType;
    private String concludeTime;
    private String projectJoin;
    private String projectEnter;
    private int year;
    private float projectScore;
    private String state;

    public void setProjectScore(float projectScore) {
        this.projectScore = projectScore;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setProject_score(float project_score) {
        this.projectScore = project_score;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setFundingDirect(Float fundingDirect) {
        this.fundingDirect = fundingDirect;
    }

    public void setFundingIndirect(Float fundingInirect) {
        this.fundingIndirect = fundingInirect;
    }

    public void setProjectGrade(String projectGrade) {
        this.projectGrade = projectGrade;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public void setConcludeTime(String concludeTime) {
        this.concludeTime = concludeTime;
    }

    public void setProjectJoin(String projectJoin) {
        this.projectJoin = projectJoin;
    }

    public void setProjectEnter(String projectEnter) {
        this.projectEnter = projectEnter;
    }
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
