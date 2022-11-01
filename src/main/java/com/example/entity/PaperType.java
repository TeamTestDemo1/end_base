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
    private String paperId;

    /**
     * 期刊名称
     */
    private String journistName;

    /**
     * 期刊等级
     */
    private String journistRank;

    /**
     * 作者等级
     */
    private Integer authorRank;

    @Override
    public String toString() {
        return "PaperType{" +
                "id=" + id +
                ", issn='" + issn + '\'' +
                ", paperId='" + paperId + '\'' +
                ", journistName='" + journistName + '\'' +
                ", journistRank='" + journistRank + '\'' +
                ", authorRank=" + authorRank +
                ", journistType='" + journistType + '\'' +
                ", journistRemark='" + journistRemark + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
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

    public String getJournistType() {
        return journistType;
    }

    public void setJournistType(String journistType) {
        this.journistType = journistType;
    }

    public String getJournistRemark() {
        return journistRemark;
    }

    public void setJournistRemark(String journistRemark) {
        this.journistRemark = journistRemark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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


}