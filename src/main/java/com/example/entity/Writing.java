package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName(value="writing",autoResultMap = true)
@ToString
public class Writing {
    /**
     * 序号
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 著作姓名
     */
    private String writing_name;
    /**
     * 老师工号
     */
    private String user_id;
    /**
     * 国际标准书号
     */
    @TableField(value = "ISBN")
    private String ISBN;
    /**
     * 署名单位
     */
    private String writing_unit;
    /**
     * 出版社名称
     */
    private String press_name;
    /**
     * 出版时间
     */
    private String press_time;
    /**
     * 著作类型
     */
    private String writing_type;
    /**
     * 总字数
     */
    private String writing_number;
    /**
     * 出版主编
     */
    private String writing_editor;
    /**
     * 作者
     */
    private String writing_author;
    /**
     * 年度
     */
    private String writing_year;
    /**
     * 是否国家规划
     */
    private Integer is_national;
    /**
     * 核实状态
     */
    private Integer confirm_status;
    /**
     * 录入人
     */
    private String writing_enter;
    /**
     * 是否再版
     */
    private Integer is_second;
    /**
     * 是否国外
     */
    private Integer is_foreign;
}
