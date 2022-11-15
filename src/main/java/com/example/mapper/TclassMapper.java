package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Tclass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TclassMapper extends BaseMapper<Tclass> {
    @Select("select sum(bounce) as work from t_class where teacher_name = #{teachername}")
    Integer countwork(String teachername);

    @Select("select sum(bounce) as work from t_class")
    Integer countAll();
}
