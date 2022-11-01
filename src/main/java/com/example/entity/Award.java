package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

@Data
@TableName(value="award",autoResultMap = true)
@ToString
public class Award extends Model<Award> {

    /**
     * 序号
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 年度
     */
    private Integer award_year;
    /**
     * 奖励类型
     */
    private String award_type;
    /**
     * 奖励等级
     */
    private String award_grade;
    /**
     * 奖励名称
     */
    private String award_name;
    /**
     * 成果名称
     */
    private String result_name;
    /**
     * 第一完成单位
     */
    private String unit_first;
    /**
     * 第一获奖人
     */
    private String award_first;
    /**
     * 全体获奖人姓名
     */
    private String award_all;
    /**
     * 奖励计分
     */
    private Float award_score;
    /**
     * 录入人
     */
    private String award_enter;

}