package com.example.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.common.Result;
import com.example.entity.Paper;
import com.example.entity.PaperType;
import com.example.mapper.PaperTypeMapper;
import com.example.service.LogService;
import com.example.service.PaperService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/paper")
public class PaperController {
    @Resource
    PaperService paperService;

    @Resource
    LogService logService;

    @Resource
    PaperTypeMapper paperTypemapper;

    @PostMapping
    public Result<?> save(@RequestBody String json) {
        JSONObject jsonObject = JSON.parseObject(json);

        Paper paper = jsonObject.getObject("paper", Paper.class);
        PaperType paperType = jsonObject.getObject("paperType", PaperType.class);
        paper.setPaperType(paperType);
        paperService.addPaper(paper);

        paperTypemapper.insert(paperType);

        logService.log(StrUtil.format("新增paper：{}", paper.getPaperTitle()));
        return Result.success();
    }
    @GetMapping()
    public Result<?> findALl() {
        List<Paper> all = paperService.findAll();
        for(int i= 0; i < all.size(); i++){
            String paperId = all.get(i).getPaperId();
            HashMap<String, Object> map = new HashMap<>();
            map.put("journist_id", paperId);
            all.get(i).setPaperType(paperTypemapper.selectByMap(map).get(0));
        }
        return Result.success(all);
    }
}
