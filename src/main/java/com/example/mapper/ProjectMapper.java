package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.entity.Project_score;
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    @Select("select r.score from project_rank r join project p on r.project_type = p.project_type and r.project_grade = p.project_grade where r.project_type = #{type} and r.project_grade = #{grade} and p.id = #{id}")
    Project_score selectScore(String type, String grade,Long id);
}