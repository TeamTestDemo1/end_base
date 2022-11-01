package com.example.service;

import com.example.entity.Paper;
import com.example.entity.PaperScoreCalculate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PaperScoreService {
    /**
     * 计算总分数
     * @param paper
     * @return
     */

    public Float PaperScore(Paper paper){
        if(paper.getPaperType().getJournistType() == "英文"){
            return null;
        }
        else{
            return paperChinese(paper);
        }

    }

    /**
     * 计算中文期刊
     * @param paper
     * @return
     */

    public Float paperChinese(Paper paper){
        HashMap<String, Object> map =   PaperScoreCalculate.getPaper_chinese();
        String[] authors = paper.getAuthorAll().split("，");
        float score_sum = 0.0f;
        score_sum += (Float)map.get(paper.getPaperType().getJournistRank());
//        进行算分
        Integer rank = paper.getPaperType().getAuthorRank(); // 得到rank

        List<Float> scores = PaperScoreCalculate.getPaper_score();
        float Ri = scores.get(Math.min(rank-1, scores.size() - 1));
        float Ri_sum = 0.0f;
        for(int i = 0; i < authors.length; ++ i) {
            Ri_sum += scores.get(Math.min(i,scores.size() - 1));
        }
        return score_sum * Ri / Ri_sum;
    }

    /**
     * 修改算分标准（中文）
     */
    public boolean paperChineseScoreStandard(HashMap<String, Object> chinese_standard){
        try {
            PaperScoreCalculate.setPaper_chinese(chinese_standard);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 修改算分标准（英文）
     */
    public boolean paperEnglishScoreStandard(HashMap<String, Object> chinese_standard){
        try {
            PaperScoreCalculate.setPaper_chinese(chinese_standard);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *  * 传入核定系数
     * @param score
     * @return
     */
    public boolean paperScoreCoef(@RequestBody String[] score){
        List<Float> paper_score = new ArrayList<>();
        try {
            for(String s: score){
                System.out.println(s);
                paper_score.add(Float.parseFloat(s));
            }
            System.out.println(paper_score);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
