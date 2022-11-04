package com.example.mapper;
import org.apache.ibatis.annotations.Param;
import com.example.entity.PaperType;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @Entity com.example.entity.Paper
*/
public interface PaperMapper extends BaseMapper<Paper> {

    /*通过作者id与论文类型查询doi*/
    List<Paper> selectPaperDoiByUserIdAndPaperType(@Param("userId") String userId, @Param("paperType") PaperType paperType);






}
