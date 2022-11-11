package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.ShaPan;
import org.apache.ibatis.annotations.Select;

public interface ShaPanMapper extends BaseMapper<ShaPan> {

    @Select("select sum(bounce) from shapan where teacher_name = #{teacherName}")
    double countAll(String teacherName);


    @Select("select sum(bounce) from shapan")
    double countA();

}
