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
import com.example.entity.Writing;
import com.example.service.AwardService;
import com.example.service.WritingService;
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
@RequestMapping("/writing" )
public class WritingController {

    @Resource
    private WritingService writingService;

    //新增和修改
    @PostMapping
    public Result<?> save(@RequestBody Writing writing) {
        return Result.success(writingService.save(writing));
    }

    @PutMapping
    public Result<?> update(@RequestBody Writing writing) {
        return Result.success(writingService.updateById(writing));
    }

    //查询数据
    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Integer id) {
        return Result.success(writingService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(writingService.list());
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        writingService.removeById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Writing> query = Wrappers.<Writing>lambdaQuery().orderByDesc(Writing::getId);
        return Result.success(writingService.page(new Page<>(pageNum, pageSize), query));
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
        List<Writing> writings = CollUtil.newArrayList();
        for(List<Object> row: list)
        {
            Writing writing= new Writing();
            writing.setWriting_year(row.get(0).toString());
            writing.setWriting_type(row.get(1).toString());
            writing.setPress_time(row.get(2).toString());
            writing.setWriting_name(row.get(3).toString());
            writing.setIs_national(Integer.valueOf(row.get(4).toString()));
            writing.setPress_name(row.get(5).toString());
            writing.setWriting_unit(row.get(6).toString());
            writing.setWriting_editor(row.get(7).toString());
            writing.setWriting_number(row.get(8).toString());
            writing.setWriting_author(row.get(9).toString());
            writing.setWriting_enter(row.get(10).toString());
            writing.setConfirm_status(Integer.valueOf(row.get(11).toString()));
            writing.setISBN(row.get(12).toString());
            writing.setIs_second(Integer.valueOf(row.get(13).toString()));
            writing.setIs_foreign(Integer.valueOf(row.get(14).toString()));
            writings.add(writing);
        }
        writingService.saveBatch(writings);
        return true;

    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        //查询出所有的数据
        List<Writing> list = writingService.list();
        //通过工具类创建writer写出到磁盘路径
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //设置excel文件的表头标题
        writer.addHeaderAlias("writing_year","年度");
        writer.addHeaderAlias("writing_type","著作类型");
        writer.addHeaderAlias("press_time","出版时间");
        writer.addHeaderAlias("writing_name","著作姓名");
        writer.addHeaderAlias("is_national","是否国家规划");
        writer.addHeaderAlias("press_name","出版社名称");
        writer.addHeaderAlias("writing_unit","署名单位");
        writer.addHeaderAlias("writing_editor","出版主编");
        writer.addHeaderAlias("writing_number","总字数");
        writer.addHeaderAlias("writing_author","作者");
        writer.addHeaderAlias("writing_enter","录入人");
        writer.addHeaderAlias("confirm_status","核实状态");
        writer.addHeaderAlias("ISBN","ISBN");
        writer.addHeaderAlias("is_second","是否再版");
        writer.addHeaderAlias("is_foreign","是否国外");


        //将结果写入excel
        writer.write(list,true);

        //设置网页请求返回结果
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("著作信息", "UTF-8");
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
