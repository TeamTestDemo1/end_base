package com.example.controller;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.PaperChinese;
import com.example.service.LogService;
import com.example.service.PaperChineseScoreService;
import com.example.service.PaperChineseService;
import org.springframework.web.bind.annotation.*;
import sun.font.TrueTypeFont;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Paper;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/paper/chinese")
public class PaperChineseController {
    @Resource
    PaperChineseService paperChineseService;

    @Resource
    LogService logService;

    @Resource
    PaperChineseScoreService paperScoreService;

    /**
     * 新增paper
     * @param paperChinese
     * @return
     */

    @PostMapping
    public Result<?> save(@RequestBody PaperChinese paperChinese) {
        int ans = paperChineseService.addPaper(paperChinese);

        logService.log(StrUtil.format("新增paper：{}", paperChinese.getPaperTitle()));

        return ans >=0 ? Result.success() : Result.error("-1", "添加失败");
    }

    /**
     * 修改审核状态
     * @param
     * @return
     */
    @GetMapping("/modify/{id}/{state}")
    public Result<?> Status(@PathVariable String state, @PathVariable Integer id){
        return this.paperChineseService.passPaper(id, state) ? Result.success() : Result.error("-1", "保存失败");
    }

    /**
     * 删除paper
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<?> deletePaper(@PathVariable Integer id){
        return this.paperChineseService.deletePaper(id) ? Result.success() : Result.error("-1", "保存失败");

    }
    /**
     * 修改paper
     */
    @PutMapping("/modify")
    public Result<?> modifyPaper(@RequestBody PaperChinese paper){
        return this.paperChineseService.updatePaper(paper) ? Result.success() : Result.error("-1", "保存失败");
    }
    /**
     * 查看paper
     * @return
     */
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                              @RequestParam(required = false, defaultValue = "") String state,
                              @RequestParam(required = false, defaultValue = "") String time, @RequestParam(required = false, defaultValue = "") String title) {

        LambdaQueryWrapper<PaperChinese> query = Wrappers.<PaperChinese>lambdaQuery().like(!time.equals(""),PaperChinese::getPaperTime, time).
                like(!title.equals(""), PaperChinese::getPaperTitle, title).eq(!state.equals(""), PaperChinese::getPaperState, state).orderByDesc(PaperChinese::getId);
        IPage<PaperChinese> ans = paperChineseService.page(new Page<>(pageNum, pageSize), query);
        return Result.success(ans);
    }

    /**
     * 计算score
     * @param id
     * @return
     */
    @GetMapping("/score/{id}")
    public Float score(@PathVariable Long id){
        PaperChinese paper = paperChineseService.selectByPaperId(id);
        return paperScoreService.paperChineseScore(paper);
    }
    @GetMapping("/score/all")
    public Result<?> scoreAll(){
        paperScoreService.paperChineseScoreAll();
        return this.paperScoreService.paperChineseScoreAll() ? Result.success() : Result.error("-1", "保存失败");
    }
    @GetMapping("/test")
    public List<PaperChinese> test(){
        paperScoreService.paperChineseScoreAll();
        return null;
    }

    /**
     * 导出表格（中文）
     * @param response
     * @throws IOException
     */

    //表格导出接口
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        //查询所有用户
        List<PaperChinese> papers = paperChineseService.findAll();
        List<Map<String, Object>> lists = new ArrayList<>();
        //在内存操作，写到浏览器
        ExcelWriter writer= ExcelUtil.getWriter(true);
        for(PaperChinese paper : papers){
            Map<String, Object> map = new LinkedHashMap<>();
            DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
            DateFormat dateFormatMonth = new SimpleDateFormat("yyyy/MM ");
            map.put("年度", dateFormatYear.format(paper.getPaperTime()));
            map.put("发表时间", dateFormatMonth.format(paper.getPaperTime()));
            map.put("论文名称", paper.getPaperTitle());
            map.put("论文期刊", paper.getJournistName());
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
        String fileName= URLEncoder.encode("中文论文","UTF-8");
        //Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream outputStream= response.getOutputStream();

        //将Writer刷新到OutPut
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
    }
}
