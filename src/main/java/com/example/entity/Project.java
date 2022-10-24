package com.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("project")
@ToString
public class Project extends Model<Project> {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 项目编号
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 项目录入人
     */
    private String project_enter;

    private Integer year;

}
