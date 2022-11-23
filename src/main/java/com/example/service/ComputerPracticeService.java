package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ComputerPractice;
import com.example.mapper.ComputerPracticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ComputerPracticeService extends ServiceImpl<ComputerPracticeMapper, ComputerPractice> {

    @Resource
    private ComputerPracticeMapper computerPracticeMapper;

    public Double countA(String teacherName) {
        return computerPracticeMapper.countA(teacherName);
    }

    public Double countAll() {
        return computerPracticeMapper.countAll();
    }
}
