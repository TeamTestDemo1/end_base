package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Paper;
import com.example.entity.PaperType;
import com.example.mapper.PaperMapper;
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


    /**
     * 查询所有paper
     * @return
     */
    public List<Paper> findAll(){
        List<Paper> papers = paperMapper.selectList(null);
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
     * 教师id查询
     * @param userId
     * @return
     */
    public List<Paper> selectById(int userId){
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        return paperMapper.selectByMap(map);
    }




}
