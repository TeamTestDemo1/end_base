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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWriting_name() {
        return writing_name;
    }

    public void setWriting_name(String writing_name) {
        this.writing_name = writing_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getWriting_unit() {
        return writing_unit;
    }

    public void setWriting_unit(String writing_unit) {
        this.writing_unit = writing_unit;
    }

    public String getPress_name() {
        return press_name;
    }

    public void setPress_name(String press_name) {
        this.press_name = press_name;
    }

    public String getPress_time() {
        return press_time;
    }

    public void setPress_time(String press_time) {
        this.press_time = press_time;
    }

    public String getWriting_type() {
        return writing_type;
    }

    public void setWriting_type(String writing_type) {
        this.writing_type = writing_type;
    }

    public String getWriting_number() {
        return writing_number;
    }

    public void setWriting_number(String writing_number) {
        this.writing_number = writing_number;
    }

    public String getWriting_editor() {
        return writing_editor;
    }

    public void setWriting_editor(String writing_editor) {
        this.writing_editor = writing_editor;
    }

    public String getWriting_author() {
        return writing_author;
    }

    public void setWriting_author(String writing_author) {
        this.writing_author = writing_author;
    }

    public String getWriting_year() {
        return writing_year;
    }

    public void setWriting_year(String writing_year) {
        this.writing_year = writing_year;
    }

    public Integer getIs_national() {
        return is_national;
    }

    public void setIs_national(Integer is_national) {
        this.is_national = is_national;
    }

    public Integer getConfirm_status() {
        return confirm_status;
    }

    public void setConfirm_status(Integer confirm_status) {
        this.confirm_status = confirm_status;
    }

    public String getWriting_enter() {
        return writing_enter;
    }

    public void setWriting_enter(String writing_enter) {
        this.writing_enter = writing_enter;
    }

    public Integer getIs_second() {
        return is_second;
    }

    public void setIs_second(Integer is_second) {
        this.is_second = is_second;
    }

    public Integer getIs_foreign() {
        return is_foreign;
    }

    public void setIs_foreign(Integer is_foreign) {
        this.is_foreign = is_foreign;
    }

    public double getWriting_score() {
        return writing_score;
    }

    public void setWriting_score(double writing_score) {
        this.writing_score = writing_score;
    }

    /**
     * 著作分数
     */
    private double writing_score;
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
