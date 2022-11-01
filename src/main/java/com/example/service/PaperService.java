package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Paper;
import com.example.entity.PaperType;
import com.example.mapper.PaperMapper;
import com.example.mapper.PaperTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
*
*/
@Service
public class PaperService extends ServiceImpl<PaperMapper, Paper> {
    @Resource
    private PaperMapper paperMapper;

    @Resource
    private PaperTypeMapper paperTypeMapper;


    /**
     * 查询所有paper
     * @return
     */
    public List<Paper> findAll(){
        List<Paper> papers = paperMapper.selectList(null);
        for (Paper paper : papers) {
            String paperId = paper.getPaperId();
            HashMap<String, Object> map = new HashMap<>();
            map.put("paper_id", paperId);
            paper.setPaperType(paperTypeMapper.selectByMap(map).get(0));
        }
        return papers;
}

    /**
     * 增加paper
     * @param paper
     * @return
     */
    public int addPaper(Paper paper){
        int insert = paperMapper.insert(paper);
        return insert;
    }

    /**
     * 删除paper
     * @param paper
     * @return
     */
    public int updatePaper(Paper paper){
        int result = paperMapper.updateById(paper);
        return result;
    }

    /**
     * 通过教师id查询paper
     * @param userId
     * @return
     */
    public List<Paper> selectByUserId(int userId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        return paperMapper.selectByMap(map);
    }

    /**
     * 按照论文id查paper
     * @param paperId
     * @return
     */
    public Paper selectByPaperId(Long paperId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("paper_id", paperId);

        List<Paper> papers = paperMapper.selectByMap(map);
        List<PaperType> paperTypes = paperTypeMapper.selectByMap(map);
        papers.get(0).setPaperType(paperTypes.get(0));
        return papers.get(0);
    }


}
