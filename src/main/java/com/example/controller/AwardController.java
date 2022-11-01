package com.example.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Award;
import com.example.entity.Project;
import com.example.entity.Project_score;
import com.example.service.AwardService;
import com.example.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/award")
public class AwardController {
    @Resource
    private AwardService awardService;

    //新增和修改
    @PostMapping
    public Result<?> save(@RequestBody Award award) {
        return Result.success(awardService.save(award));
    }

    @PutMapping
    public Result<?> update(@RequestBody Award award) {
        return Result.success(awardService.updateById(award));
    }

    //查询数据
    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Integer id) {
        return Result.success(awardService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(awardService.list());
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        awardService.removeById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Award> query = Wrappers.<Award>lambdaQuery().orderByDesc(Award::getId);
        return Result.success(awardService.page(new Page<>(pageNum, pageSize), query));
    }

    /**
     * 导入excel文件接口
     * @param file
     * @return
     * @throws IOException
     */

//    @PostMapping("/import")
//    public Boolean imp(MultipartFile file) throws Exception{
//        InputStream inputStream =file.getInputStream();
//        ExcelReader reader = ExcelUtil.getReader(inputStream);
//    //通过javabean的方式读取Excel对象，要求表头必须英文，跟javabean属性对应
//        //List<Project> list =reader.readAll(Project.class);
//        List<Project> list = reader.read(1,1,Project.class);
//        projectService.saveBatch(list);
//        return true;
//    }

    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception{
        InputStream inputStream =file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //忽略表头中文，直接读取表的内容
        List<List<Object>> list =reader.read(1);
        List<Award> awards = CollUtil.newArrayList();
        for(List<Object> row: list)
        {
            Award award= new Award();
            award.setAward_year(Integer.valueOf(row.get(0).toString()));
            award.setAward_type(row.get(1).toString());
            award.setAward_grade(row.get(2).toString());
            award.setAward_name(row.get(3).toString());
            award.setResult_name(row.get(4).toString());
            award.setUnit_first(row.get(5).toString());
            award.setAward_first(row.get(6).toString());
            award.setAward_all(row.get(7).toString());
            award.setAward_enter(row.get(8).toString());

            awards.add(award);
        }
        awardService.saveBatch(awards);
        return true;

    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        //查询出所有的数据
        List<Award> list = awardService.list();
        //通过工具类创建writer写出到磁盘路径
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //设置excel文件的表头标题
        writer.addHeaderAlias("award_year","年度");
        writer.addHeaderAlias("award_type","奖项类型");
        writer.addHeaderAlias("award_grade","项目级别");
        writer.addHeaderAlias("award_name","获奖名称");
        writer.addHeaderAlias("result_name","成果名称");
        writer.addHeaderAlias("unit_first","第一完成单位");
        writer.addHeaderAlias("award_first","第一获奖人");
        writer.addHeaderAlias("award_all","全体获奖人姓名");
        writer.addHeaderAlias("award_score","获奖计分");
        writer.addHeaderAlias("award_enter;","奖励录入人");


        //将结果写入excel
        writer.write(list,true);

        //设置网页请求返回结果
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("奖励信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        //输出流
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();
    }

//    /**
//     * 计算分数接口
//     * @param project
//     * @return
//     */
//    @PostMapping("/calculation")
//    public Result calculation(@RequestBody Project project) {
//        String type = project.getProject_type();
//        String grade = project.getProject_grade();
//        Integer id = project.getId();
//        Project_score selectscore =projectService.selectscore(type,grade,id);
//        project.setProject_score(selectscore.getScore());
//        System.out.println(selectscore.getScore());
//        return Result.success(projectService.updateById(project));
//    }
//
//    @PostMapping("/all")
//    public Result calculationAll(@RequestBody List<Project> list){
//        for (Project project:list){
//            String type = project.getProject_type();
//            String grade = project.getProject_grade();
//            Integer id = project.getId();
//            Project_score selectscore =projectService.selectscore(type,grade,id);
//            project.setProject_score(selectscore.getScore());
//            projectService.updateById(project);
//        }
//        return Result.success();
//    }

}
