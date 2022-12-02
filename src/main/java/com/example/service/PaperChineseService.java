package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.PaperChinese;
import com.example.entity.ScoreRule;
import com.example.mapper.PaperChineseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Paper;
import java.util.HashMap;
import java.util.List;

/**
*
*/
@Service
public class PaperChineseService extends ServiceImpl<PaperChineseMapper, PaperChinese> {
    @Resource
    private PaperChineseMapper paperMapper;

    @Resource
    private PaperChineseScoreService paperChineseScoreService;

    /**
     * 查询所有paper
     * @return
     */
    public List<PaperChinese> findAll(){
        return paperMapper.findAll();
        }
    /**
     * 删除paper
      */
    public boolean deletePaper(Integer id){
        return this.paperMapper.deleteById(id) > 0;
    }
    /**
     * 增加paper
     * @param paper
     * @return
     */
    public int addPaper(PaperChinese paper){
        int insert = paperMapper.insert(paper);
        return insert;
    }
    /**
     * 审核通过paper
     * @return
     */
    public boolean passPaper(int id, String state){
        PaperChinese paperChinese = paperMapper.selectById(id);
        paperChinese.setPaperState(state);
        return this.updatePaper(paperChinese);
    }

    /**
     * 删除paper
     * @param paper
     * @return
     */
    public boolean updatePaper(PaperChinese paper){
        int result = paperMapper.updateById(paper);
        return result > 0;
    }

    /**
     * 多条件查询
     * @param paperId
     * @return
     */

    public PaperChinese selectByPaperId(Long paperId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("paper_id", paperId);
        List<PaperChinese> papers = paperMapper.selectByMap(map);

        return papers.get(0);
    }


}
