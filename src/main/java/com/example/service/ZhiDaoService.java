package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ZhiDao;
import com.example.mapper.ZhiDaoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ZhiDaoService extends ServiceImpl<ZhiDaoMapper, ZhiDao> {

    @Resource
    private ZhiDaoMapper zhiDaoMapper;

    public Double countAll(String teacherName) {
        return zhiDaoMapper.countAll(teacherName);
    }

    public double countA() {
        return zhiDaoMapper.countA();
    }
}
