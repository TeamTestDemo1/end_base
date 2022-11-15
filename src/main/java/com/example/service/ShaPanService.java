package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ShaPan;
import com.example.mapper.ShaPanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShaPanService extends ServiceImpl<ShaPanMapper, ShaPan> {

    @Resource
    private ShaPanMapper shaPanMapper;

    public double countAll(String teacherName) {
        return shaPanMapper.countAll(teacherName);
    }

    public double countA()
    {
        return shaPanMapper.countA();
    }
}
