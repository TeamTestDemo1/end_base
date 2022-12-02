package com.example.controller;


import com.example.entity.PaperChinese;
import com.example.service.PaperChineseScoreService;
import com.example.service.PaperChineseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/paper/calculate/chinese")
public class PaperScoreController {
    @Resource
    PaperChineseScoreService paperScoreService;
    @Resource
    PaperChineseService paperChineseService;
    /**
     * 更改计算中文计算标准
     */
    @GetMapping
    public boolean paperCalculate(){
        List<PaperChinese> allPaper = paperChineseService.findAll();
        for(PaperChinese paper : allPaper){
            Float score = paperScoreService.paperChineseScore(paper);
            paper.setPaperScore(score);
            paperChineseService.updatePaper(paper);
        }
        return true;
    }


    @PostMapping("/update")
    public boolean paperChineseScoreStandard(@RequestBody  HashMap<String, Object> chinese_standard){
        System.out.println(chinese_standard);
        return paperScoreService.paperChineseScoreStandard(chinese_standard);
    }


    @PostMapping("/update/coef")
    public boolean setCoef(@RequestBody String[] score){
        return paperScoreService.paperScoreCoef(score);
    }

}
