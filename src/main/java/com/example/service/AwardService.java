package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Award;
import com.example.mapper.AwardMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AwardService extends ServiceImpl<AwardMapper, Award> {

    @Resource
    private AwardMapper awardMapper;
}
