package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Project_score;
import com.example.entity.Project;
import com.example.entity.Project_score;
import com.example.exception.CustomException;
import com.example.service.LogService;
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
@RequestMapping("/api/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;
    @Resource
    private LogService logService;
    @PostMapping
    public Result<?> save(@RequestBody Project project) {
//        Project one = projectService.getOne((Wrappers.<Project>lambdaQuery().eq(Project::getProjectName, project.getProjectName())));
//        if (one != null) {
//            throw new CustomException("-1", "项目已存在");
//        }
        if (project.getProjectName()== null) {
            throw new CustomException("-1", "项目名称不可为空");
        }
        logService.log(StrUtil.format("新增科研项目：{}",project.getProjectName()));
//        return Result.success(permissionService.save(permission));
        return Result.success(projectService.save(project));
    }

    @PutMapping
    public Result<?> update(@RequestBody Project Project) {
        return Result.success(projectService.updateById(Project));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        projectService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(projectService.getById(id));
    }
//    @GetMapping("/{id}")
//    public Result<?> findByprojectLeader(@PathVariable String projectLeader) {
//        return Result.success(projectService.getById(projectLeader));
//    }
    @GetMapping
    public Result<?> findAll() {
        return Result.success(projectService.list());
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                              @RequestParam String leaderName) {
        LambdaQueryWrapper<Project> query = Wrappers.<Project>lambdaQuery().like(Project::getProjectName, name).like(Project::getProjectLeader,leaderName).orderByDesc(Project::getId);

        return Result.success(projectService.page(new Page<>(pageNum, pageSize), query));
    }



    /**
     * 导入excel文件接口
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception{
        InputStream inputStream =file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //忽略表头中文，直接读取表的内容
        List<List<Object>> list =reader.read(1);
        List<Project> projects = CollUtil.newArrayList();
        for(List<Object> row: list)
        {
            Project project = new Project();
            project.setYear(Integer.valueOf(row.get(0).toString()));
            project.setProjectId(row.get(1).toString());
            project.setProjectType(row.get(2).toString());
            project.setProjectName(row.get(3).toString());
            project.setProjectGrade(row.get(4).toString());
            project.setProjectLeader(row.get(5).toString());
            project.setFundingDirect(Float.valueOf(row.get(6).toString()));
            project.setFundingIndirect(Float.valueOf(row.get(7).toString()));
            project.setStartTime(row.get(8).toString());
            project.setEndTime(row.get(9).toString());
            project.setConcludeTime(row.get(10).toString());
            project.setProjectJoin(row.get(11).toString());
            project.setProjectEnter(row.get(12).toString());

            projects.add(project);
        }
        projectService.saveBatch(projects);
        return true;

    }
    /**
     * 导出接口
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        //查询出所有的数据
        List<Project> list = projectService.list();
        //通过工具类创建writer写出到磁盘路径
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //设置excel文件的表头标题
        writer.addHeaderAlias("year","年度");
        writer.addHeaderAlias("projectId","项目编号");
        writer.addHeaderAlias("projectType","项目类型");
        writer.addHeaderAlias("projectName","项目名称");
        writer.addHeaderAlias("projectGrade","项目等级");
        writer.addHeaderAlias("projectLeader","项目主持人");
        writer.addHeaderAlias("fundingDirect","直接经费");
        writer.addHeaderAlias("fundingIndirect","间接经费");
        writer.addHeaderAlias("startTime","开始时间");
        writer.addHeaderAlias("endTime","结束时间");
        writer.addHeaderAlias("concludeTime","结题时间");
        writer.addHeaderAlias("projectJoin","项目参与人");
        writer.addHeaderAlias("projectEnter;","项目录入人");
        writer.addHeaderAlias("projectScore;","项目分数");
        writer.addHeaderAlias("state;","审核状态");
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
//    @PostMapping("/calculate/all")
//    public Result calculate(@RequestBody List<Project> list)
//    {
//        for(int i = 0; i < list.size(); i ++)
//        {
//            Integer time = list.get(i).getTime();
//            Integer num = list.get(i).getNum();
//            double xishu;
//            if(num <= 30) xishu = 1.0;
//            else if(num > 30 && num <= 45) xishu = 1.1;
//            else if(num > 45 && num <= 60) xishu = 1.2;
//            else if(num > 60 && num <= 90) xishu = 1.3;
//            else if(num > 90 && num <= 120) xishu = 1.4;
//            else xishu = 1.5;
//            list.get(i).setBounce(time * xishu);
//            tclassService.updateById(list.get(i));
//        }
//        return Result.success("一键核算成功",null);
//    }

    @PostMapping("/calculation")
    public Result calculation(@RequestBody Project project){
        String type = project.getProjectType();
        String grade = project.getProjectGrade();
        Long id = project.getId();
        Project_score selectscore = projectService.selectscore(type, grade,id);
        project.setProjectScore(selectscore.getScore());
        return Result.success(projectService.updateById(project));
    }

    @PostMapping("/all")
    public Result calculationAll(@RequestBody List<Project> list){
        for (Project project:list) {
            String type = project.getProjectType();
            String grade = project.getProjectGrade();
            Long id = project.getId();
            Project_score selectscore = projectService.selectscore(type, grade,id);
            project.setProjectScore(selectscore.getScore());
            projectService.updateById(project);
        }
        return Result.success();
    }
}




