package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("t_class")
@ToString
public class Tclass {
    @TableId(value = "id",type = IdType.AUTO)//代表id属性是自增的
    private Integer id;
    @TableField(value = "year")
    private String year;
    @TableField(value = "course_name")
    private String courseName;
    @TableField(value = "teacher_name")
    private String teacherName;
    @TableField(value = "teacher_title")
    private String teacherTitle;
    @TableField(value = "is_special")
    private String isSpecial;
    @TableField(value = "class_state")
    private String classState;
    @TableField(value = "class_xinzhi")
    private String classXinZhi;
    @TableField(value = "score")
    private double score;
    @TableField(value = "time")
    private Integer time;
    @TableField(value = "l_time")
    private Integer lTime;
    @TableField(value = "s_time")
    private Integer sTime;
    @TableField(value = "c_time")
    private Integer cTime;
    @TableField(value = "p_time")
    private Integer pTime;
    @TableField(value = "together")
    private String together;
    @TableField(value = "class_num")
    private Integer classNum;
    @TableField(value = "class_name")
    private String className;
    @TableField(value = "class_number")
    private String classNumber;
    @TableField(value = "num")
    private Integer num;
    @TableField(value = "orginazition")
    private String orginazition;
    @TableField(value = "bounce")
    private double bounce;
}
