package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.PaperEnglish;
import com.example.mapper.PaperEnglishMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
*
*/
@Service
public class PaperEnglishService extends ServiceImpl<PaperEnglishMapper, PaperEnglish> {
    @Resource
    private PaperEnglishMapper paperMapper;

    @Resource
    private PaperEnglishScoreService paperEnglishScoreService;




    /**
     * 查询所有paper
     * @return
     */
    public List<PaperEnglish> findAll(){
        List<PaperEnglish> papers = paperMapper.selectList(null);
        return papers;
    }

    /**
     * 增加paper
     * @param paper
     * @return
     */
    public int addPaper(PaperEnglish paper){
        int insert = paperMapper.insert(paper);
        return insert;
    }

    /**
     * 审核通过paper
     * @return
     */
    public boolean passPaper(int id, String state){
        PaperEnglish paperEnglish = paperMapper.selectById(id);
        paperEnglish.setPaperState(state);
        return this.save(paperEnglish);
    }

    /**
     * 删除paper
     * @param paper
     * @return
     */
    public int updatePaper(PaperEnglish paper){
        int result = paperMapper.updateById(paper);
        return result;
    }

    /**
     * 多条件查询
     * @param paperId
     * @return
     */

    public PaperEnglish selectByPaperId(Long paperId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("paper_id", paperId);
        List<PaperEnglish> papers = paperMapper.selectByMap(map);

        return papers.get(0);
    }


}
