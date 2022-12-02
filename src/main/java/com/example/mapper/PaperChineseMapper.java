package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.PaperChinese;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @Entity com.example.entity.Paper
*/
@Mapper
public interface PaperChineseMapper extends BaseMapper<PaperChinese> {
    @Select("select * from paper_chinese where id in (select  min(id) as id from paper_chinese GROUP BY paper_title)")
    List<PaperChinese> findAll();
}
