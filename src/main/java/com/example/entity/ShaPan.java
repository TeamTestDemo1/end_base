package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("shapan")
public class ShaPan extends Model<ShaPan> {
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
    private String className;

    /**
      * 指导教师 
      */
    private String teacherName;

    /**
      * 班级人数 
      */
    private String classNum;

    /**
      * 学分 
      */
    private Integer score;

    /**
      * 实际课时 
      */
    private Integer trueScore;

    /**
      * 工作量 
      */
    private Double bounce;

}