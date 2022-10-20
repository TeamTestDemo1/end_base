package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName paper_type
 */
@TableName(value ="paper_type")
public class PaperType implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String issn;

    /**
     * 
     */
    private String journistId;

    /**
     * 期刊名称
     */
    private String journistName;

    /**
     * 期刊等级
     */
    private Integer journistRank;

    /**
     * 作者等级
     */
    private Integer authorRank;

    /**
     * 期刊类型
     */
    private String journistType;

    /**
     * 备注
     */
    private String journistRemark;

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
    public String getIssn() {
        return issn;
    }

    /**
     * 
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    /**
     * 
     */
    public String getJournistId() {
        return journistId;
    }

    /**
     * 
     */
    public void setJournistId(String journistId) {
        this.journistId = journistId;
    }

    /**
     * 期刊名称
     */
    public String getJournistName() {
        return journistName;
    }

    /**
     * 期刊名称
     */
    public void setJournistName(String journistName) {
        this.journistName = journistName;
    }

    /**
     * 期刊等级
     */
    public Integer getJournistRank() {
        return journistRank;
    }

    /**
     * 期刊等级
     */
    public void setJournistRank(Integer journistRank) {
        this.journistRank = journistRank;
    }

    /**
     * 作者等级
     */
    public Integer getAuthorRank() {
        return authorRank;
    }

    /**
     * 作者等级
     */
    public void setAuthorRank(Integer authorRank) {
        this.authorRank = authorRank;
    }

    /**
     * 期刊类型
     */
    public String getJournistType() {
        return journistType;
    }

    /**
     * 期刊类型
     */
    public void setJournistType(String journistType) {
        this.journistType = journistType;
    }

    /**
     * 备注
     */
    public String getJournistRemark() {
        return journistRemark;
    }

    /**
     * 备注
     */
    public void setJournistRemark(String journistRemark) {
        this.journistRemark = journistRemark;
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
        PaperType other = (PaperType) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIssn() == null ? other.getIssn() == null : this.getIssn().equals(other.getIssn()))
            && (this.getJournistId() == null ? other.getJournistId() == null : this.getJournistId().equals(other.getJournistId()))
            && (this.getJournistName() == null ? other.getJournistName() == null : this.getJournistName().equals(other.getJournistName()))
            && (this.getJournistRank() == null ? other.getJournistRank() == null : this.getJournistRank().equals(other.getJournistRank()))
            && (this.getAuthorRank() == null ? other.getAuthorRank() == null : this.getAuthorRank().equals(other.getAuthorRank()))
            && (this.getJournistType() == null ? other.getJournistType() == null : this.getJournistType().equals(other.getJournistType()))
            && (this.getJournistRemark() == null ? other.getJournistRemark() == null : this.getJournistRemark().equals(other.getJournistRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssn() == null) ? 0 : getIssn().hashCode());
        result = prime * result + ((getJournistId() == null) ? 0 : getJournistId().hashCode());
        result = prime * result + ((getJournistName() == null) ? 0 : getJournistName().hashCode());
        result = prime * result + ((getJournistRank() == null) ? 0 : getJournistRank().hashCode());
        result = prime * result + ((getAuthorRank() == null) ? 0 : getAuthorRank().hashCode());
        result = prime * result + ((getJournistType() == null) ? 0 : getJournistType().hashCode());
        result = prime * result + ((getJournistRemark() == null) ? 0 : getJournistRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", issn=").append(issn);
        sb.append(", journistId=").append(journistId);
        sb.append(", journistName=").append(journistName);
        sb.append(", journistRank=").append(journistRank);
        sb.append(", authorRank=").append(authorRank);
        sb.append(", journistType=").append(journistType);
        sb.append(", journistRemark=").append(journistRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}