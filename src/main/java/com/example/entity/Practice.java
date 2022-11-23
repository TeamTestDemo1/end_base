package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("practice")
public class Practice extends Model<Practice> {
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
      * 课程名称 
      */
    private String courseName;

    /**
      * 指导教师 
      */
    private String teacherName;

    /**
      * 指导班级 
      */
    private String className;

    /**
      * 工作量 
      */
    private Double bounce;

    /**
      * 备注 
      */
    private String note;

}