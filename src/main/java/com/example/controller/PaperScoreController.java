package com.example.controller;


import com.example.service.PaperScoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/api/paper/calculate/update")
public class PaperScoreController {
    @Resource
    PaperScoreService paperScoreService;
    /**
     * 更改计算中文计算标准
     */
    @PostMapping("/chinese")
    public boolean paperChineseScoreStandard(@RequestBody  HashMap<String, Object> chinese_standard){
        System.out.println(chinese_standard);
        return paperScoreService.paperChineseScoreStandard(chinese_standard);
    }

    /**
     * 更改英文计算标准
     * @param english_standard
     * @return
     */
    @PostMapping("/english")
    public boolean paperEnglishScoreStandard(@RequestBody  HashMap<String, Object> english_standard){
        System.out.println(english_standard);
        return paperScoreService.paperEnglishScoreStandard(english_standard);
    }

    @PostMapping("/coef")
    public boolean setCoef(@RequestBody String[] score){
        return paperScoreService.paperScoreCoef(score);
    }

}
