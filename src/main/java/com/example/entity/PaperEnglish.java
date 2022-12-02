package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName paper_english
 */
@TableName(value ="paper_english")
public class PaperEnglish implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String paperId;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String paperTitle;

    /**
     * 
     */
    private String authorAll;

    /**
     * 
     */
    private String authorFirst;

    /**
     * 
     */
    private Date paperTime;

    /**
     * 
     */
    private String paperUnit;

    /**
     * 
     */
    private String paperDoi;

    /**
     * 
     */
    private Integer paperStatus;

    /**
     * 
     */
    private String correspondingAuthorFirst;

    /**
     * 
     */
    private String journistName;

    /**
     * 
     */
    private String journistRank;

    /**
     * 第一作者是否是此导师的学生
     */
    private Integer hasStudent;

    /**
     * 
     */
    private String jointAuthorFirst;

    /**
     * 
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getPaperId() {
        return paperId;
    }

    /**
     * 
     */
    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    /**
     * 
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     */
    public String getPaperTitle() {
        return paperTitle;
    }

    /**
     * 
     */
    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    /**
     * 
     */
    public String getAuthorAll() {
        return authorAll;
    }

    /**
     * 
     */
    public void setAuthorAll(String authorAll) {
        this.authorAll = authorAll;
    }

    /**
     * 
     */
    public String getAuthorFirst() {
        return authorFirst;
    }

    /**
     * 
     */
    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    /**
     * 
     */
    public Date getPaperTime() {
        return paperTime;
    }

    /**
     * 
     */
    public void setPaperTime(Date paperTime) {
        this.paperTime = paperTime;
    }

    /**
     * 
     */
    public String getPaperUnit() {
        return paperUnit;
    }

    /**
     * 
     */
    public void setPaperUnit(String paperUnit) {
        this.paperUnit = paperUnit;
    }

    /**
     * 
     */
    public String getPaperDoi() {
        return paperDoi;
    }

    /**
     * 
     */
    public void setPaperDoi(String paperDoi) {
        this.paperDoi = paperDoi;
    }

    /**
     * 
     */
    public Integer getPaperStatus() {
        return paperStatus;
    }

    /**
     * 
     */
    public void setPaperStatus(Integer paperStatus) {
        this.paperStatus = paperStatus;
    }

    /**
     * 
     */
    public String getCorrespondingAuthorFirst() {
        return correspondingAuthorFirst;
    }

    /**
     * 
     */
    public void setCorrespondingAuthorFirst(String correspondingAuthorFirst) {
        this.correspondingAuthorFirst = correspondingAuthorFirst;
    }

    /**
     * 
     */
    public String getJournistName() {
        return journistName;
    }

    /**
     * 
     */
    public void setJournistName(String journistName) {
        this.journistName = journistName;
    }

    /**
     * 
     */
    public String getJournistRank() {
        return journistRank;
    }

    /**
     * 
     */
    public void setJournistRank(String journistRank) {
        this.journistRank = journistRank;
    }

    /**
     * 第一作者是否是此导师的学生
     */
    public Integer getHasStudent() {
        return hasStudent;
    }

    /**
     * 第一作者是否是此导师的学生
     */
    public void setHasStudent(Integer hasStudent) {
        this.hasStudent = hasStudent;
    }

    /**
     * 
     */
    public String getJointAuthorFirst() {
        return jointAuthorFirst;
    }

    /**
     * 
     */
    public void setJointAuthorFirst(String jointAuthorFirst) {
        this.jointAuthorFirst = jointAuthorFirst;
    }

    /**
     * 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PaperEnglish other = (PaperEnglish) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaperId() == null ? other.getPaperId() == null : this.getPaperId().equals(other.getPaperId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPaperTitle() == null ? other.getPaperTitle() == null : this.getPaperTitle().equals(other.getPaperTitle()))
            && (this.getAuthorAll() == null ? other.getAuthorAll() == null : this.getAuthorAll().equals(other.getAuthorAll()))
            && (this.getAuthorFirst() == null ? other.getAuthorFirst() == null : this.getAuthorFirst().equals(other.getAuthorFirst()))
            && (this.getPaperTime() == null ? other.getPaperTime() == null : this.getPaperTime().equals(other.getPaperTime()))
            && (this.getPaperUnit() == null ? other.getPaperUnit() == null : this.getPaperUnit().equals(other.getPaperUnit()))
            && (this.getPaperDoi() == null ? other.getPaperDoi() == null : this.getPaperDoi().equals(other.getPaperDoi()))
            && (this.getPaperStatus() == null ? other.getPaperStatus() == null : this.getPaperStatus().equals(other.getPaperStatus()))
            && (this.getCorrespondingAuthorFirst() == null ? other.getCorrespondingAuthorFirst() == null : this.getCorrespondingAuthorFirst().equals(other.getCorrespondingAuthorFirst()))
            && (this.getJournistName() == null ? other.getJournistName() == null : this.getJournistName().equals(other.getJournistName()))
            && (this.getJournistRank() == null ? other.getJournistRank() == null : this.getJournistRank().equals(other.getJournistRank()))
            && (this.getHasStudent() == null ? other.getHasStudent() == null : this.getHasStudent().equals(other.getHasStudent()))
            && (this.getJointAuthorFirst() == null ? other.getJointAuthorFirst() == null : this.getJointAuthorFirst().equals(other.getJointAuthorFirst()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPaperId() == null) ? 0 : getPaperId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPaperTitle() == null) ? 0 : getPaperTitle().hashCode());
        result = prime * result + ((getAuthorAll() == null) ? 0 : getAuthorAll().hashCode());
        result = prime * result + ((getAuthorFirst() == null) ? 0 : getAuthorFirst().hashCode());
        result = prime * result + ((getPaperTime() == null) ? 0 : getPaperTime().hashCode());
        result = prime * result + ((getPaperUnit() == null) ? 0 : getPaperUnit().hashCode());
        result = prime * result + ((getPaperDoi() == null) ? 0 : getPaperDoi().hashCode());
        result = prime * result + ((getPaperStatus() == null) ? 0 : getPaperStatus().hashCode());
        result = prime * result + ((getCorrespondingAuthorFirst() == null) ? 0 : getCorrespondingAuthorFirst().hashCode());
        result = prime * result + ((getJournistName() == null) ? 0 : getJournistName().hashCode());
        result = prime * result + ((getJournistRank() == null) ? 0 : getJournistRank().hashCode());
        result = prime * result + ((getHasStudent() == null) ? 0 : getHasStudent().hashCode());
        result = prime * result + ((getJointAuthorFirst() == null) ? 0 : getJointAuthorFirst().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paperId=").append(paperId);
        sb.append(", userId=").append(userId);
        sb.append(", paperTitle=").append(paperTitle);
        sb.append(", authorAll=").append(authorAll);
        sb.append(", authorFirst=").append(authorFirst);
        sb.append(", paperTime=").append(paperTime);
        sb.append(", paperUnit=").append(paperUnit);
        sb.append(", paperDoi=").append(paperDoi);
        sb.append(", paperStatus=").append(paperStatus);
        sb.append(", correspondingAuthorFirst=").append(correspondingAuthorFirst);
        sb.append(", journistName=").append(journistName);
        sb.append(", journistRank=").append(journistRank);
        sb.append(", hasStudent=").append(hasStudent);
        sb.append(", jointAuthorFirst=").append(jointAuthorFirst);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}