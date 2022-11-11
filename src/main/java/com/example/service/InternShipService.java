package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.InternShip;
import com.example.mapper.InternShipMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InternShipService extends ServiceImpl<InternShipMapper, InternShip> {

    @Resource
    private InternShipMapper internShipMapper;

    public Double countAll(String teacherName) {
        return internShipMapper.countAll(teacherName);
    }

    public Double countA() {
        return internShipMapper.countA();
    }
}
