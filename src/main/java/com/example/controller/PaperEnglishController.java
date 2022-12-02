package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;


import com.example.entity.PaperEnglish;
import com.example.service.LogService;

import com.example.service.PaperEnglishScoreService;
import com.example.service.PaperEnglishService;
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
public class PaperEnglishController {
    @Resource
    PaperEnglishService paperEnglishService;

    @Resource
    LogService logService;

    @Resource
    PaperEnglishScoreService paperScoreService;

    /**
     * 新增paper
     * @param paperEnglish
     * @return
     */

    @PostMapping
    public Result<?> save(@RequestBody PaperEnglish paperEnglish) {
        int ans = paperEnglishService.addPaper(paperEnglish);

        logService.log(StrUtil.format("新增paper：{}", paperEnglish.getPaperTitle()));

        return ans >=0 ? Result.success() : Result.error("-1", "添加失败");
    }

    /**
     * 修改审核状态
     * @param state, advice.,
     * @return
     */
    @GetMapping
    public Result<?> Status(@RequestParam int id, @RequestParam String state){
        return this.paperEnglishService.passPaper(id, state) ? Result.success() : Result.error("-1", "保存失败");
    }

    /**
     * 查看paper
     * @return
     */
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize, @RequestParam(required = false,defaultValue = "") Integer status,
                              @RequestParam(required = false, defaultValue = "") String time, @RequestParam(required = false, defaultValue = "") String title) {

        LambdaQueryWrapper<PaperEnglish> query = Wrappers.<PaperEnglish>lambdaQuery().like(time != null,PaperEnglish::getPaperTime, time).
                like(title != null, PaperEnglish::getPaperTitle, title).orderByDesc(PaperEnglish::getId);
        return Result.success(paperEnglishService.page(new Page<>(pageNum, pageSize), query));
    }

    /**
     * 计算score
     * @param id
     * @return
     */
    @GetMapping("/score/{id}")
    public Float score(@PathVariable Long id){
        PaperEnglish paper = paperEnglishService.selectByPaperId(id);
        return paperScoreService.paperEnglishScore(paper);
    }

    /**
     * 导出表格（英文）
     * @param response
     * @throws IOException
     */

    //表格导出接口
    @GetMapping("/export/english")
    public void export(HttpServletResponse response) throws IOException {
        //查询所有用户
        List<PaperEnglish> papers = paperEnglishService.findAll();
        List<Map<String, Object>> lists = new ArrayList<>();
        //在内存操作，写到浏览器
        ExcelWriter writer= ExcelUtil.getWriter(true);
        for(PaperEnglish paper : papers){
            Map<String, Object> map = new LinkedHashMap<>();
            DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
            DateFormat dateFormatMonth = new SimpleDateFormat("yyyy/MM ");
            map.put("年度", dateFormatYear.format(paper.getPaperTime()));
            map.put("发表时间", dateFormatMonth.format(paper.getPaperTime()));
            map.put("论文名称", paper.getPaperTitle());
            map.put("论文期刊", paper.getJournistName());
            map.put("第一作者", paper.getAuthorFirst());
            map.put("第一作者单位", paper.getPaperFirstUnit());
            map.put("通讯作者",paper.getCorrespondingAuthor());
            map.put("通讯作者单位",paper.getCorrespondingAuthorUnit());
            map.put("作者", paper.getAuthorAll());
            lists.add(map);
        }
        System.out.println(lists);


        //默认配置
        writer.write(lists,true);
        //设置content—type
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");

        //设置标题
        String fileName= URLEncoder.encode("英文论文","UTF-8");
        //Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream outputStream= response.getOutputStream();

        //将Writer刷新到OutPut
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
    }
}
