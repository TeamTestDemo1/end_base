package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("computerpractice")
public class ComputerPractice extends Model<ComputerPractice> {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程
     */
    private String courseName;

    /**
     * 教师
     */
    private String teacherName;

    /**
     * 课程属性
     */
    private String state;

    /**
     * 课程性质
     */
    private String type;

    /**
     * 学分
     */
    private Integer score;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 学期
     */
    private String year;

    /**
     * 实际上机时间
     */
    private Integer computerTime;

    /**
     * 工作量
     */
    private double computerpraticeBounce;

}