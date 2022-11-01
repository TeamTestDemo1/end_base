package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.common.Result;
import com.example.entity.Paper;
import com.example.entity.PaperType;

import com.example.mapper.PaperTypeMapper;
import com.example.service.LogService;
import com.example.service.PaperScoreService;
import com.example.service.PaperService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/paper")
public class PaperController {
    @Resource
    PaperService paperService;

    @Resource
    LogService logService;

    @Resource
    PaperTypeMapper paperTypemapper;

    @Resource
    PaperScoreService paperScoreService;

    /**
     * 新增paper
     * @param json
     * @return
     */

    @PostMapping
    public Result<?> save(@RequestBody String json) {
        JSONObject jsonObject = JSON.parseObject(json);

        Paper paper = jsonObject.getObject("paper", Paper.class);
        PaperType paperType = jsonObject.getObject("paperType", PaperType.class);
        paper.setPaperType(paperType);
        paperService.addPaper(paper);

        paperTypemapper.insert(paperType);

        logService.log(StrUtil.format("新增paper：{}", paper.getPaperTitle()));
        return Result.success();
    }

    /**
     * 查看所有paper
     * @return
     */
    @GetMapping()
        public Result<?> findALl() {
        List<Paper> all = paperService.findAll();
        return Result.success(all);
    }

    /**
     * 计算score
     * @param id
     * @return
     */
    @GetMapping("/score/{id}")
    public Float score(@PathVariable Long id){
        Paper paper = paperService.selectByPaperId(id);
        return paperScoreService.PaperScore(paper);
    }

    /**
     * 导出表格（中文）
     * @param response
     * @param id
     * @throws IOException
     */

    //表格导出接口
    @GetMapping("/export/chinese")
    public void export(HttpServletResponse response, @PathVariable String id) throws IOException {
        //查询所有用户
        List<Paper> papers = paperService.findAll();
        List<Map<String, Object>> lists = new ArrayList<>();
        //在内存操作，写到浏览器
        ExcelWriter writer= ExcelUtil.getWriter(true);
        for(Paper paper : papers){
            if(paper.getPaperType().getJournistType().equals("英文")){
                continue;
            }
            Map<String, Object> map = new LinkedHashMap<>();
            DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
            DateFormat dateFormatMonth = new SimpleDateFormat("yyyy/MM ");
            map.put("年度", dateFormatYear.format(paper.getPaperTime()));
            map.put("发表时间", dateFormatMonth.format(paper.getPaperTime()));
            map.put("论文名称", paper.getPaperTitle());
            map.put("论文期刊", paper.getPaperType().getJournistName());
            map.put("第一作者", paper.getAuthorFirst());
            map.put("第一作者单位", paper.getPaperUnit());
            map.put("作者", paper.getAuthorAll());
            lists.add(map);
        }
        System.out.println(lists);


        //默认配置
        writer.write(lists,true);
        //设置content—type
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");

        //设置标题
        String fileName= URLEncoder.encode("用户信息","UTF-8");
        //Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream outputStream= response.getOutputStream();

        //将Writer刷新到OutPut
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
    }

    /**
     * 导出论文（英文）
     * @param response
     * @throws IOException
     */
    @GetMapping("/export/english")
    public void exportEnglish(HttpServletResponse response) throws IOException {
        //查询所有用户
        List<Paper> papers = paperService.findAll();
        List<Map<String, Object>> lists = new ArrayList<>();
        //在内存操作，写到浏览器
        ExcelWriter writer= ExcelUtil.getWriter(true);
        for(Paper paper : papers){
            if(paper.getPaperType().getJournistType().equals("中文")){
                continue;
            }
            Map<String, Object> map = new LinkedHashMap<>();
            DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
            DateFormat dateFormatMonth = new SimpleDateFormat("yyyy/MM ");
            map.put("年度", dateFormatYear.format(paper.getPaperTime()));
            map.put("发表时间", dateFormatMonth.format(paper.getPaperTime()));
            map.put("论文名称", paper.getPaperTitle());
            map.put("论文期刊", paper.getPaperType().getJournistName());
            map.put("第一作者", paper.getAuthorFirst());
            map.put("第一作者单位", paper.getPaperUnit());
            map.put("作者", paper.getAuthorAll());
            lists.add(map);
        }
        System.out.println(lists);


        //默认配置
        writer.write(lists,true);
        //设置content—type
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");

        //设置标题
        String fileName= URLEncoder.encode("用户信息","UTF-8");
        //Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream outputStream= response.getOutputStream();

        //将Writer刷新到OutPut
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
    }
}
