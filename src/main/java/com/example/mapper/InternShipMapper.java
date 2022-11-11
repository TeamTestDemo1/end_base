package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.InternShip;
import org.apache.ibatis.annotations.Select;

public interface InternShipMapper extends BaseMapper<InternShip> {

    @Select("select sum(bounce) from internship where teacher_name = #{teacherName}")
    Double countAll(String teacherName);


    @Select("select sum(bounce) from internship")
    Double countA();
}
