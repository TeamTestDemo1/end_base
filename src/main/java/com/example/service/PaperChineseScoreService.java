package com.example.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.PaperChinese;
import com.example.entity.ScoreRule;
import com.example.mapper.PaperChineseMapper;
import com.example.mapper.ScoreRuleMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.font.TrueTypeFont;


import javax.annotation.Resource;
import java.awt.print.Paper;
import java.util.*;

@Service
public class PaperChineseScoreService{

    @Resource
    private ScoreRuleMapper scoreRuleMapper;

    @Resource
    private PaperChineseMapper paperChineseMapper;
    /**
     * 一键核算
     */
    public boolean paperChineseScoreAll(){
        boolean flag = true;
        List<PaperChinese> papers = paperChineseMapper.selectList(new QueryWrapper<PaperChinese>().like("paper_time", Calendar.getInstance().get(Calendar.YEAR)));
        for(PaperChinese paper : papers){
            float score = paperChineseScore(paper);
            System.out.println(score);
            PaperChinese newpaper = paperChineseMapper.selectById(paper);
            newpaper.setPaperScore(score);
            flag &= (paperChineseMapper.updateById(newpaper) > 0);
        }
        return flag;
    }
    /**
     * 计算中文期刊
     * @param paper
     * @return
     */
    public Float paperChineseScore(PaperChinese paper){
        String[] authors = paper.getAuthorAll().split("，");
        float score_sum = 0.0f;
//        进行算分
        float score_chinese =  scoreRuleMapper.selectOne(new QueryWrapper<ScoreRule>().eq("score_type","中文" + paper.getJournistRank())).getScore();
        System.out.println(score_chinese);
        float score_remark = 0.0f;
        if(paper.getRemark() != null){
            score_remark += scoreRuleMapper.selectOne(new QueryWrapper<ScoreRule>().eq("score_type","remark_" + paper.getRemark())).getScore();
        }
        score_sum += paper.getRemark() == null ? score_chinese : score_remark;

        Integer authorRank = paper.getAuthorRank();

        String ans  = scoreRuleMapper.selectOne(new QueryWrapper<com.example.entity.ScoreRule>().eq("score_type", "中文系数")).getCoef();
        String[] ans1 = ans.split(",");
        List<Float> scoreOfCoef = new ArrayList<>();
        for(String str : ans1){
            scoreOfCoef.add(Float.parseFloat(str));
        }

        float Ri = scoreOfCoef.get(Math.min(authorRank-1, scoreOfCoef.size() - 1));
        float Ri_sum = 0.0f;
        for(int i = 0; i < authors.length; ++ i) {
            Ri_sum += scoreOfCoef.get(Math.min(i,scoreOfCoef.size() - 1));
        }
        return score_sum * Ri / Ri_sum;
    }

    /**
     * 修改算分标准（中文）
     */
    public boolean paperChineseScoreStandard(Map map){
        String[] types = {"中文A+", "中文A", "中文A-", "中文B"};
        for(String str : types){
            if(map.get(str) != null){
                continue;
            }
            ScoreRule ans = (ScoreRule) scoreRuleMapper.selectOne(new QueryWrapper<com.example.entity.ScoreRule>().eq("score_type", str));
            ans.setScore(Float.parseFloat((String) map.get(str)));
            scoreRuleMapper.updateById(ans);
        }
        return true;
    }

    /**
     *  * 传入核定系数
     * @param score
     * @return
     */
    public boolean paperScoreCoef(@RequestBody String[] score){
        try {
            ;
            ScoreRule ans = (ScoreRule) scoreRuleMapper.selectOne(new QueryWrapper<com.example.entity.ScoreRule>().eq("score_type", "中文系数"));
            ans.setCoef(Arrays.toString(score));
            int res = scoreRuleMapper.updateById(ans);
            return res > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
