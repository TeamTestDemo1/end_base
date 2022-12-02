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
     *  所有作者的姓名，包括通讯作者
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
     * 论文第一单位（第一作者与通讯作者单位）
     */
    private String paperFirstUnit;

    /**
     * 
     */
    private String paperDoi;

    /**
     * 此老师是否为第一通讯作者
     */
    private Integer correspondingAuthorFirst;

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
     * 所有的通讯作者
     */
    private String correspondingAuthor;

    /**
     * 通讯作者单位
     */
    private String correspondingAuthorUnit;

    /**
     * 
     */
    private String remark;

    /**
     * 此老师的作者排位，不包括通讯作者
     */
    private Integer authorRank;

    /**
     * 每篇论文的分数
     */
    private Double paperScore;

    /**
     * 
     */
    private String paperState;

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
     *  所有作者的姓名，包括通讯作者
     */
    public String getAuthorAll() {
        return authorAll;
    }

    /**
     *  所有作者的姓名，包括通讯作者
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
     * 论文第一单位（第一作者与通讯作者单位）
     */
    public String getPaperFirstUnit() {
        return paperFirstUnit;
    }

    /**
     * 论文第一单位（第一作者与通讯作者单位）
     */
    public void setPaperFirstUnit(String paperFirstUnit) {
        this.paperFirstUnit = paperFirstUnit;
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
     * 此老师是否为第一通讯作者
     */
    public Integer getCorrespondingAuthorFirst() {
        return correspondingAuthorFirst;
    }

    /**
     * 此老师是否为第一通讯作者
     */
    public void setCorrespondingAuthorFirst(Integer correspondingAuthorFirst) {
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
     * 所有的通讯作者
     */
    public String getCorrespondingAuthor() {
        return correspondingAuthor;
    }

    /**
     * 所有的通讯作者
     */
    public void setCorrespondingAuthor(String correspondingAuthor) {
        this.correspondingAuthor = correspondingAuthor;
    }

    /**
     * 通讯作者单位
     */
    public String getCorrespondingAuthorUnit() {
        return correspondingAuthorUnit;
    }

    /**
     * 通讯作者单位
     */
    public void setCorrespondingAuthorUnit(String correspondingAuthorUnit) {
        this.correspondingAuthorUnit = correspondingAuthorUnit;
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

    /**
     * 此老师的作者排位，不包括通讯作者
     */
    public Integer getAuthorRank() {
        return authorRank;
    }

    /**
     * 此老师的作者排位，不包括通讯作者
     */
    public void setAuthorRank(Integer authorRank) {
        this.authorRank = authorRank;
    }

    /**
     * 每篇论文的分数
     */
    public Double getPaperScore() {
        return paperScore;
    }

    /**
     * 每篇论文的分数
     * @param paperScore
     */
    public void setPaperScore(Float paperScore) {
        this.paperScore = paperScore;
    }

    /**
     * 
     */
    public String getPaperState() {
        return paperState;
    }

    /**
     * 
     */
    public void setPaperState(String paperState) {
        this.paperState = paperState;
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
            && (this.getPaperFirstUnit() == null ? other.getPaperFirstUnit() == null : this.getPaperFirstUnit().equals(other.getPaperFirstUnit()))
            && (this.getPaperDoi() == null ? other.getPaperDoi() == null : this.getPaperDoi().equals(other.getPaperDoi()))
            && (this.getCorrespondingAuthorFirst() == null ? other.getCorrespondingAuthorFirst() == null : this.getCorrespondingAuthorFirst().equals(other.getCorrespondingAuthorFirst()))
            && (this.getJournistName() == null ? other.getJournistName() == null : this.getJournistName().equals(other.getJournistName()))
            && (this.getJournistRank() == null ? other.getJournistRank() == null : this.getJournistRank().equals(other.getJournistRank()))
            && (this.getHasStudent() == null ? other.getHasStudent() == null : this.getHasStudent().equals(other.getHasStudent()))
            && (this.getCorrespondingAuthor() == null ? other.getCorrespondingAuthor() == null : this.getCorrespondingAuthor().equals(other.getCorrespondingAuthor()))
            && (this.getCorrespondingAuthorUnit() == null ? other.getCorrespondingAuthorUnit() == null : this.getCorrespondingAuthorUnit().equals(other.getCorrespondingAuthorUnit()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getAuthorRank() == null ? other.getAuthorRank() == null : this.getAuthorRank().equals(other.getAuthorRank()))
            && (this.getPaperScore() == null ? other.getPaperScore() == null : this.getPaperScore().equals(other.getPaperScore()))
            && (this.getPaperState() == null ? other.getPaperState() == null : this.getPaperState().equals(other.getPaperState()));
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
        result = prime * result + ((getPaperFirstUnit() == null) ? 0 : getPaperFirstUnit().hashCode());
        result = prime * result + ((getPaperDoi() == null) ? 0 : getPaperDoi().hashCode());
        result = prime * result + ((getCorrespondingAuthorFirst() == null) ? 0 : getCorrespondingAuthorFirst().hashCode());
        result = prime * result + ((getJournistName() == null) ? 0 : getJournistName().hashCode());
        result = prime * result + ((getJournistRank() == null) ? 0 : getJournistRank().hashCode());
        result = prime * result + ((getHasStudent() == null) ? 0 : getHasStudent().hashCode());
        result = prime * result + ((getCorrespondingAuthor() == null) ? 0 : getCorrespondingAuthor().hashCode());
        result = prime * result + ((getCorrespondingAuthorUnit() == null) ? 0 : getCorrespondingAuthorUnit().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getAuthorRank() == null) ? 0 : getAuthorRank().hashCode());
        result = prime * result + ((getPaperScore() == null) ? 0 : getPaperScore().hashCode());
        result = prime * result + ((getPaperState() == null) ? 0 : getPaperState().hashCode());
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
        sb.append(", paperFirstUnit=").append(paperFirstUnit);
        sb.append(", paperDoi=").append(paperDoi);
        sb.append(", correspondingAuthorFirst=").append(correspondingAuthorFirst);
        sb.append(", journistName=").append(journistName);
        sb.append(", journistRank=").append(journistRank);
        sb.append(", hasStudent=").append(hasStudent);
        sb.append(", correspondingAuthor=").append(correspondingAuthor);
        sb.append(", correspondingAuthorUnit=").append(correspondingAuthorUnit);
        sb.append(", remark=").append(remark);
        sb.append(", authorRank=").append(authorRank);
        sb.append(", paperScore=").append(paperScore);
        sb.append(", paperState=").append(paperState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}