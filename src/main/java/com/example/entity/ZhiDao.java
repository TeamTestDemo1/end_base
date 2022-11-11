package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("zhidao")
public class ZhiDao extends Model<ZhiDao> {
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
      * 指导人数 
      */
    private Integer num;

    /**
      * 学生姓名 
      */
    private String studentName;

    /**
      * 学生类型 
      */
    private String studentType;

    /**
      * 是否答辩秘书 
      */
    private String isSecretor;

    /**
      * 担任答辩秘书班级名称 
      */
    private String secretorClassName;

    /**
      * 答辩秘书工作量 
      */
    private Double secretorBounce;

    /**
      * 总工作量 
      */
    private Double zhidaoBounce;

    @TableField(value = "teacher_name")
    private String teacherName;

}