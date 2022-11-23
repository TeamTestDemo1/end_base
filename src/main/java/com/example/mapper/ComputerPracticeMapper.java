package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.ComputerPractice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ComputerPracticeMapper extends BaseMapper<ComputerPractice> {

    @Select("select sum(computerpractice_bounce) from computerpractice where teacher_name = #{teacherName}")
    Double countA(String teacherName);

    @Select("select sum(computerpractice_bounce) from computerpractice")
    Double countAll();
}
