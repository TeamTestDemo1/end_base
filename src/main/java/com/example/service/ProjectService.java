package com.example.service;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import com.example.entity.Project_score;
import com.example.entity.Project;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProjectService extends ServiceImpl<ProjectMapper, Project> {

    @Resource
    private ProjectMapper projectMapper;


    public Project_score selectscore(String type, String grade, Long id) {
        return projectMapper.selectScore(type,grade,id);
    }
}

