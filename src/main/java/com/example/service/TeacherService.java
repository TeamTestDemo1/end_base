package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Teacher;
import com.example.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherService extends ServiceImpl<TeacherMapper, Teacher> {

    @Resource
    private TeacherMapper teacherMapper;

}
