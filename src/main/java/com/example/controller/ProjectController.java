package com.example.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Project;
import com.example.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    //新增和修改
    @PostMapping
    public Result<?> save(@RequestBody Project project) {
        return Result.success(projectService.save(project));
    }

    @PutMapping
    public Result<?> update(@RequestBody Project project) {
        return Result.success(projectService.updateById(project));
    }

    //查询数据
    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Integer id) {
        return Result.success(projectService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(projectService.list());
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        projectService.removeById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Project> query = Wrappers.<Project>lambdaQuery().orderByDesc(Project::getId);
        return Result.success(projectService.page(new Page<>(pageNum, pageSize), query));
    }

    /**
     * 导入excel文件接口
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws IOException {
        //获取输入流
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> read = reader.read(1);
        List<Project> projects = new ArrayList<>();
        //将excel表中的数据存入到对象中
        for(List<Object> row : read)
        {
            Project project = new Project();
            project.setProject_id(row.get(1).toString());
            project.setProject_name(row.get(2).toString());
            project.setStart_time(row.get(3).toString());
            project.setEnd_time(row.get(4).toString());
            project.setFunding_direct(Float.valueOf(row.get(5).toString()));
            project.setFunding_indirect(Float.valueOf(row.get(6).toString()));
            project.setProject_grade(row.get(7).toString());
            project.setProject_leader(row.get(8).toString());
            project.setProject_type(row.get(9).toString());
            project.setConclude_time(row.get(10).toString());
            project.setProject_join(row.get(11).toString());
            project.setProject_enter(row.get(12).toString());
            project.setYear(Integer.valueOf(row.get(13).toString()));
            projects.add(project);
        }
        return true;
    }

    /**
     * 导出接口
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        List<Project> list = projectService.list();

        //查询出所有的数据
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //设置excel文件的表头标题
        writer.addHeaderAlias("project_id","项目编号");
        writer.addHeaderAlias("project_name","项目名称");
        writer.addHeaderAlias("start_time","开始时间");
        writer.addHeaderAlias("end_time","结束时间");
        writer.addHeaderAlias("funding_direct","直接经费");
        writer.addHeaderAlias("funding_indirect","间接经费");
        writer.addHeaderAlias("project_grade","项目等级");
        writer.addHeaderAlias("project_leader","项目主持人");
        writer.addHeaderAlias("project_type","项目类型");
        writer.addHeaderAlias("conclude_time","结题时间");
        writer.addHeaderAlias("project_join","项目参与人");
        writer.addHeaderAlias("project_enter;","项目录入人");
        writer.addHeaderAlias("year","年度");

        //将结果写入excel
        writer.write(list,true);

        //设置网页请求返回结果
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("项目信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        //输出流
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();



    }
}
