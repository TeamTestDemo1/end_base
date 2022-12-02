package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.PaperEnglish;
import com.example.entity.ScoreRule;
import com.example.mapper.PaperEnglishMapper;
import com.example.mapper.ScoreRuleMapper;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PaperEnglishScoreService {

    @Resource
    private ScoreRuleMapper scoreRuleMapper;

    /**
     * 计算英文期刊
     * @param
     * @return
     */
    public Float paperEnglishScore(PaperEnglish paperEnglish){
        String[] authors = paperEnglish.getAuthorAll().split(",");
        String[] authorFirst = paperEnglish.getAuthorFirst().split(",");//存放第一作者，用于计算第一作者人数
        String[] correspondingAuthor = paperEnglish.getCorrespondingAuthor().split(",");//存放所有通讯作者
        float score_sum = 0.0f;
        //从算分规则中检索出对应等级的对应分数
        //是否将A1b和A2属于商学院经济学与管理学期刊论文加3分与加1分的条件加入？还是在数据库中操作？
        score_sum += scoreRuleMapper.selectOne(new QueryWrapper<ScoreRule>().eq("score_type","英文" + paperEnglish.getJournistRank())).getScore();
        if (paperEnglish.getPaperFirstUnit()!="中南大学商学院"&&((paperEnglish.getJournistRank()=="英文Top")||paperEnglish.getJournistRank()=="英文A1a")){
            score_sum=score_sum/2;
        }//检测第一单位
        //获得作者对应的排位
        Integer authorRank = paperEnglish.getAuthorRank();
        //获得对应系数（字符串）
        String ans  = scoreRuleMapper.selectOne(new QueryWrapper<ScoreRule>().eq("score_type", "英文系数")).getCoef();
        String[] ans1 = ans.split(",");//数组存放排位系数（String）类型
        List<Float> scoreOfCoef = new ArrayList<>();
        for(String str : ans1){
            scoreOfCoef.add(Float.parseFloat(str));
        }//将数组排位系数转为Float类型并存入list中

        float Ri_sum = 0.0f;

            for(int i = 0; i < authors.length-authorFirst.length+1-correspondingAuthor.length; ++ i) {
                Ri_sum += scoreOfCoef.get(Math.min(i,scoreOfCoef.size() - 1));//系数和
            }

         if(paperEnglish.getHasStudent()==1&&paperEnglish.getAuthorRank()==2&&paperEnglish.getCorrespondingAuthorFirst()==0){
             //学生为第一作者，导师为第二作者，导师非通讯作者
             return (score_sum*scoreOfCoef.get(0)/Ri_sum)/(authorFirst.length+((correspondingAuthor.length>0)?1:0)+1);
             //导师变为第一作者是直接把导师升级为第一作者还是导师把学生替换？
             //学生的那一部分分是否归老师所有？
         }else if(paperEnglish.getAuthorRank()==1&&paperEnglish.getCorrespondingAuthorFirst()==0 ){
             //导师为第一作者，导师非通讯作者
             return (score_sum*scoreOfCoef.get(0)/Ri_sum)/(authorFirst.length+((correspondingAuthor.length>0)?1:0));
         } else if(paperEnglish.getAuthorRank()==null&&paperEnglish.getCorrespondingAuthorFirst()==1) {
             //导师为通讯作者
             return (score_sum*scoreOfCoef.get(0)/Ri_sum)/(authorFirst.length+((correspondingAuthor.length>0)?1:0));
         }else if(paperEnglish.getHasStudent()==1&&authorRank>2){
             //学生为第一作者，但导师不是第一二作者
             float Ri = scoreOfCoef.get(Math.min(authorRank-1, scoreOfCoef.size() - 1));
             return score_sum * Ri / Ri_sum;
        } else {
             // 学生不为第一作者,且导师不为第一作者
             float Ri = scoreOfCoef.get(Math.min(authorRank-1, scoreOfCoef.size() - 1));
             return score_sum * Ri / Ri_sum;
         }

    }

    /**
     * 修改算分标准（英文）
     */
    public boolean paperEnglishScoreStandard(@RequestBody Map map){
        String[] types = {"英文Top", "英文A1a", "英文A1b", "英文A2","英文B1","英文B2"};
        for(String str : types){
            if(map.get(str) != null){
                continue;
            }
            ScoreRule ans = (ScoreRule) scoreRuleMapper.selectOne(new QueryWrapper<ScoreRule>().eq("score_type", str));
            ans.setScore((Double)map.get(str));
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
            ScoreRule ans = (ScoreRule) scoreRuleMapper.selectOne(new QueryWrapper<ScoreRule>().eq("score_type", "英文系数"));
            ans.setCoef(Arrays.toString(score));
            int res = scoreRuleMapper.updateById(ans);
            return res > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
