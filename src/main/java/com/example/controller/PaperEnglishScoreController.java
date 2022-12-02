package com.example.controller;



import com.example.entity.PaperEnglish;
import com.example.service.PaperEnglishScoreService;
import com.example.service.PaperEnglishService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/paper/calculate")
public class PaperEnglishScoreController {
    @Resource
    PaperEnglishScoreService paperScoreService;
    @Resource
    PaperEnglishService paperEnglishService;
    /**
     * 更改计算英文计算标准
     */
    @RequestMapping("/english")
    public boolean paperCalculate(){
        List<PaperEnglish> allPaper = paperEnglishService.findAll();
        for(PaperEnglish paper : allPaper){
            Float score = paperScoreService.paperEnglishScore(paper);
            paper.setPaperScore(score);
            paperEnglishService.updatePaper(paper);
        }
        return true;
    }

    @PostMapping("/update/english")
    public boolean paperEnglishScoreStandard(@RequestBody  HashMap<String, Object> english_standard){
        System.out.println(english_standard);
        return paperScoreService.paperEnglishScoreStandard(english_standard);
    }


    @PostMapping("update/coef")
    public boolean setCoef(@RequestBody String[] score){
        return paperScoreService.paperScoreCoef(score);
    }

}
