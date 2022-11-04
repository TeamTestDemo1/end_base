package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Writing;
import com.example.mapper.WritingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WritingService extends ServiceImpl<WritingMapper, Writing> {

    @Resource
    private WritingMapper writingMapper;
}
