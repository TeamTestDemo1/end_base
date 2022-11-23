package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.ZhiDao;
import org.apache.ibatis.annotations.Select;

public interface ZhiDaoMapper extends BaseMapper<ZhiDao> {

    @Select("select sum(zhidao_bounce) from zhidao where teacher_name = #{teachername} ")
    Double countAll(String teacherName);

    @Select("select sum(zhidao_bounce) from zhidao")
    double countA();

}
