package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Practice;
import com.example.mapper.PracticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PracticeService extends ServiceImpl<PracticeMapper, Practice> {
    @Resource
    private PracticeMapper practiceMapper;
    public double countAll(String teacherName) {
        return practiceMapper.countAll(teacherName);
    }

    public double countA() {
        return practiceMapper.countA();
    }
}
