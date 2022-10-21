package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.PaperType;
import com.example.mapper.PaperTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 李子域~
 * @version 1.0
 */
@Service
public class PaperTypeService extends ServiceImpl<PaperTypeMapper, PaperType> {
    @Resource
    private  PaperTypeMapper paperTypeMapper;

    //查询此期刊信息
    public List<PaperType> findAll(){
        List<PaperType> paperTypes = paperTypeMapper.selectList(null);
        return paperTypes;
    }

    //添加期刊信息
    public int addPaperType(PaperType paperType){
        int insert = paperTypeMapper.insert(paperType);
        return insert;
    }

    //更新期刊信息
    public int updatePaperType(PaperType paperType){
        int update = paperTypeMapper.updateById(paperType);
        return update;
    }



}
