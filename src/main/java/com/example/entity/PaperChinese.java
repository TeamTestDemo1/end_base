package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName paper_chinese
 */
@TableName(value ="paper_chinese")
public class PaperChinese implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Override
    public String toString() {
        return "PaperChinese{" +
                "id=" + id +
                ", paperId='" + paperId + '\'' +
                ", userId='" + userId + '\'' +
                ", paperTitle='" + paperTitle + '\'' +
                ", authorAll='" + authorAll + '\'' +
                ", authorFirst='" + authorFirst + '\'' +
                ", paperTime=" + paperTime +
                ", paperUnit='" + paperUnit + '\'' +
                ", paperDoi='" + paperDoi + '\'' +
                ", paperScore=" + paperScore +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", paperState='" + paperState + '\'' +
                ", journistName='" + journistName + '\'' +
                ", journistRank='" + journistRank + '\'' +
                ", authorRank=" + authorRank +
                ", hasStudent=" + hasStudent +
                ", remark='" + remark + '\'' +
                '}';
    }

    /**
     * 
     */
    private String paperId;

    /**
     * 
     */
    private String userId;

    /**
     * 论文名称
     */
    private String paperTitle;

    /**
     * 所有作者
     */
    private String authorAll;

    /**
     * 第一作者
     */
    private String authorFirst;

    /**
     * 发行日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paperTime;

    /**
     * 署名单位
     */
    private String paperUnit;

    /**
     * 
     */
    private String paperDoi;
    /**
     * 
     */
    private Float paperScore;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    private Integer teacherId;
    private String teacherName;



    private String paperState;

    /**
     * 
     */
    private String journistName;

    /**
     * 
     */
    private String journistRank;

    /**
     * 
     */
    private Integer authorRank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getAuthorAll() {
        return authorAll;
    }

    public void setAuthorAll(String authorAll) {
        this.authorAll = authorAll;
    }

    public String getAuthorFirst() {
        return authorFirst;
    }

    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    public Date getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(Date paperTime) {
        this.paperTime = paperTime;
    }

    public String getPaperUnit() {
        return paperUnit;
    }

    public void setPaperUnit(String paperUnit) {
        this.paperUnit = paperUnit;
    }

    public String getPaperDoi() {
        return paperDoi;
    }

    public void setPaperDoi(String paperDoi) {
        this.paperDoi = paperDoi;
    }

    public Float getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(Float paperScore) {
        this.paperScore = paperScore;
    }

    public String getPaperState() {
        return paperState;
    }

    public void setPaperState(String state) {
        this.paperState = state;
    }

    public String getJournistName() {
        return journistName;
    }

    public void setJournistName(String journistName) {
        this.journistName = journistName;
    }

    public String getJournistRank() {
        return journistRank;
    }

    public void setJournistRank(String journistRank) {
        this.journistRank = journistRank;
    }

    public Integer getAuthorRank() {
        return authorRank;
    }

    public void setAuthorRank(Integer authorRank) {
        this.authorRank = authorRank;
    }

    public Integer getHasStudent() {
        return hasStudent;
    }

    public void setHasStudent(Integer hasStudent) {
        this.hasStudent = hasStudent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 
     */
    private Integer hasStudent;

    /**
     * 
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}