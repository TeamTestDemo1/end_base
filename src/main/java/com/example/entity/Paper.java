package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName paper
 */
@Data
@TableName(value ="paper")
public class Paper implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(exist = false)
    private PaperType paperType;
    /**
     * 
     */
    @TableId(value = "paperId", type = IdType.AUTO)
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
     * 署名单位
     */
    private String paperUnit;

    /**
     * 发行日期
     */
    private Date paperTime;

    /**
     * 
     */
    private String paperDoi;



    /**
     * 
     */


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
        Paper other = (Paper) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaperId() == null ? other.getPaperId() == null : this.getPaperId().equals(other.getPaperId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPaperTitle() == null ? other.getPaperTitle() == null : this.getPaperTitle().equals(other.getPaperTitle()))
            && (this.getAuthorAll() == null ? other.getAuthorAll() == null : this.getAuthorAll().equals(other.getAuthorAll()))
            && (this.getAuthorFirst() == null ? other.getAuthorFirst() == null : this.getAuthorFirst().equals(other.getAuthorFirst()))
            && (this.getPaperUnit() == null ? other.getPaperUnit() == null : this.getPaperUnit().equals(other.getPaperUnit()))
            && (this.getPaperTime() == null ? other.getPaperTime() == null : this.getPaperTime().equals(other.getPaperTime()))
            && (this.getPaperDoi() == null ? other.getPaperDoi() == null : this.getPaperDoi().equals(other.getPaperDoi()));
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
        result = prime * result + ((getPaperUnit() == null) ? 0 : getPaperUnit().hashCode());
        result = prime * result + ((getPaperTime() == null) ? 0 : getPaperTime().hashCode());
        result = prime * result + ((getPaperDoi() == null) ? 0 : getPaperDoi().hashCode());
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
        sb.append(", paperUnit=").append(paperUnit);
        sb.append(", paperTime=").append(paperTime);
        sb.append(", paperDoi=").append(paperDoi);
        sb.append("]");
        return sb.toString();
    }
}