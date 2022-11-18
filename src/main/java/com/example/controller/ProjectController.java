package com.example.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Project;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @PostMapping
    public Result<?> save(@RequestBody Project project) {
        if (project.getProjectName()== null) {
            throw new CustomException("-1", "项目名称不可为空");
        }
//        Project one = projectService.getOne((Wrappers.<Project>lambdaQuery().eq(Project::getProjectName,project.getProjectName())));
//        if (one != null) {
//            throw new CustomException("-1", "项目已存在");
//        }
        //projectService.log(StrUtil.format("新增用户：{} ", user.getUsername()));
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

    @GetMapping
    public Result<?> findAll() {
        return Result.success(projectService.list());
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Project> query = Wrappers.<Project>lambdaQuery().like(Project::getProjectName, name).orderByDesc(Project::getId);
        return Result.success(projectService.page(new Page<>(pageNum, pageSize), query));
    }

}

