package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("internship")
public class InternShip extends Model<InternShip> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 年度 
      */
    private String year;

    /**
      * 专业班级 
      */
    private String className;

    /**
      * 学生人数 
      */
    private Integer studentNum;

    /**
      * 教师姓名 
      */
    private String teacherName;

    /**
      * 职称 
      */
    private String position;

    /**
      * 是否队长 
      */
    private String isMonitor;

    /**
      * 起止日期 
      */
    private String time;

    /**
      * 持续周数 
      */
    private Integer weekNum;

    /**
      * 实习类别 
      */
    private String type;
    @TableField(value = "orgnization")
    private String orgnazition;

    /**
      * 工作量 
      */
    private double internshipBounce;

}