package generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private Integer paperStatus;

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
     * 论文名称
     */
    public String getPaperTitle() {
        return paperTitle;
    }

    /**
     * 论文名称
     */
    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    /**
     * 所有作者
     */
    public String getAuthorAll() {
        return authorAll;
    }

    /**
     * 所有作者
     */
    public void setAuthorAll(String authorAll) {
        this.authorAll = authorAll;
    }

    /**
     * 第一作者
     */
    public String getAuthorFirst() {
        return authorFirst;
    }

    /**
     * 第一作者
     */
    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    /**
     * 发行日期
     */
    public Date getPaperTime() {
        return paperTime;
    }

    /**
     * 发行日期
     */
    public void setPaperTime(Date paperTime) {
        this.paperTime = paperTime;
    }

    /**
     * 署名单位
     */
    public String getPaperUnit() {
        return paperUnit;
    }

    /**
     * 署名单位
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
     * 
     */
    public Integer getAuthorRank() {
        return authorRank;
    }

    /**
     * 
     */
    public void setAuthorRank(Integer authorRank) {
        this.authorRank = authorRank;
    }

    /**
     * 
     */
    public Integer getHasStudent() {
        return hasStudent;
    }

    /**
     * 
     */
    public void setHasStudent(Integer hasStudent) {
        this.hasStudent = hasStudent;
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
        PaperChinese other = (PaperChinese) that;
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
            && (this.getJournistName() == null ? other.getJournistName() == null : this.getJournistName().equals(other.getJournistName()))
            && (this.getJournistRank() == null ? other.getJournistRank() == null : this.getJournistRank().equals(other.getJournistRank()))
            && (this.getAuthorRank() == null ? other.getAuthorRank() == null : this.getAuthorRank().equals(other.getAuthorRank()))
            && (this.getHasStudent() == null ? other.getHasStudent() == null : this.getHasStudent().equals(other.getHasStudent()))
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
        result = prime * result + ((getJournistName() == null) ? 0 : getJournistName().hashCode());
        result = prime * result + ((getJournistRank() == null) ? 0 : getJournistRank().hashCode());
        result = prime * result + ((getAuthorRank() == null) ? 0 : getAuthorRank().hashCode());
        result = prime * result + ((getHasStudent() == null) ? 0 : getHasStudent().hashCode());
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
        sb.append(", journistName=").append(journistName);
        sb.append(", journistRank=").append(journistRank);
        sb.append(", authorRank=").append(authorRank);
        sb.append(", hasStudent=").append(hasStudent);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}