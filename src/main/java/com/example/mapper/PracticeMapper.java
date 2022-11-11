package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Practice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PracticeMapper extends BaseMapper<Practice> {
    @Select("select sum(bounce) from practice where teacher_name = #{teacherName}")
    double countAll(String teacherName);

    @Select("select sum(bounce) from practice")
    double countA();
}

