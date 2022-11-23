package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.entity.Tclass;
import com.example.mapper.TclassMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TclassService extends ServiceImpl<TclassMapper, Tclass> {
    @Resource
    private TclassService tclassService;
    @Resource
    private TclassMapper tclassMapper;
    public Result saveorUpdate(Tclass tclass) {
        if(tclass.getId() == null)
        {
            return Result.success("新增成功",tclassService.save(tclass));
        }
        else
        {
            return Result.success("更新成功",tclassService.updateById(tclass));
        }
    }


    public int countwork(String teachername) {
        return tclassMapper.countwork(teachername);
    }

    public int countAll()
    {
        return tclassMapper.countAll();
    }
}
