package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * @TableName score_rule
 */
@TableName(value ="score_rule")
public class ScoreRule implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String scoreType;

    /**
     * 
     */
    private float score;

    public float getScore() {
        return score;
    }

    /**
     * 
     */
    private String coef;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getScoreType() {
        return scoreType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreRule scoreRule = (ScoreRule) o;
        return Float.compare(scoreRule.score, score) == 0 &&
                Objects.equals(id, scoreRule.id) &&
                Objects.equals(scoreType, scoreRule.scoreType) &&
                Objects.equals(coef, scoreRule.coef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scoreType, score, coef);
    }

    /**
     * 
     */
    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 
     */
    public String getCoef() {
        return coef;
    }

    /**
     * 
     */
    public void setCoef(String coef) {
        this.coef = coef;
    }

}