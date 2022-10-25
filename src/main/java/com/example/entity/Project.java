package com.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

@Data
@TableName(value="project",autoResultMap = true)
@ToString
public class Project extends Model<Project> {
    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 项目编号
     */

    private String project_id;

    /**
     * 项目名称
     */
    private String project_name;

    /**
     * 开始时间
     */
    private String start_time;

    /**
     * 结束时间
     */
    private String end_time;

    /**
     * 直接经费
     */
    private float funding_direct;

    /**
     * 间接经费
     */
    private float funding_indirect;

    /**
     * 项目等级
     */
    private String project_grade;

    /**
     * 项目主持人
     */
    private String project_leader;

    /**
     * 项目类型
     */
    private String project_type;

    /**
     * 结题时间
     */
    private String conclude_time;

    /**
     * 项目参与人
     */
    private String project_join;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public float getFunding_direct() {
        return funding_direct;
    }

    public void setFunding_direct(float funding_direct) {
        this.funding_direct = funding_direct;
    }

    public float getFunding_indirect() {
        return funding_indirect;
    }

    public void setFunding_indirect(float funding_indirect) {
        this.funding_indirect = funding_indirect;
    }

    public String getProject_grade() {
        return project_grade;
    }

    public void setProject_grade(String project_grade) {
        this.project_grade = project_grade;
    }

    public String getProject_leader() {
        return project_leader;
    }

    public void setProject_leader(String project_leader) {
        this.project_leader = project_leader;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getConclude_time() {
        return conclude_time;
    }

    public void setConclude_time(String conclude_time) {
        this.conclude_time = conclude_time;
    }

    public String getProject_join() {
        return project_join;
    }

    public void setProject_join(String project_join) {
        this.project_join = project_join;
    }

    public String getProject_enter() {
        return project_enter;
    }

    public void setProject_enter(String project_enter) {
        this.project_enter = project_enter;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 项目录入人
     */
    private String project_enter;

    private Integer year;

}
