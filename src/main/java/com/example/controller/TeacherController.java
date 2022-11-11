package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Teacher;
import com.example.service.TeacherService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private HttpServletRequest request;


    @PostMapping
    public Result save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        teacherService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        return Result.success("查询成功",teacherService.getById(id));
    }

    @GetMapping
    public Result findAll() {
        List<Teacher> list = teacherService.list();
        return Result.success("查询所有老师信息成功",list);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Teacher> query = Wrappers.<Teacher>lambdaQuery().orderByDesc(Teacher::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(Teacher::getTeacherName, name);
        }
        IPage<Teacher> page = teacherService.page(new Page<>(pageNum, pageSize), query);
        return Result.success("分页查询成功",page);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<Teacher> all = teacherService.list();
        for (Teacher obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("", obj.getId());
            row.put("", obj.getTeacherId());
            row.put("", obj.getTeacherName());
            row.put("", obj.getMajor());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("教师信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> lists = reader.read(1);
        List<Teacher> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            Teacher obj = new Teacher();
            obj.setTeacherId((String) row.get(0));
            obj.setTeacherName((String) row.get(1));
            obj.setMajor((String) row.get(2));

            saveList.add(obj);
        }
        teacherService.saveBatch(saveList);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody Teacher teacher){
        String username = teacher.getTeacherId();
        String password = teacher.getPassword();
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper();
        queryWrapper.eq("teacher_id",username);
        queryWrapper.eq("password",password);
        List<Teacher> one = teacherService.list(queryWrapper);
        if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error("404","请输入用户名或密码");
        }else if(one.size() == 0){
            return Result.error("405","用户名或密码错误");
        }else if(one.size()>1){
            return Result.error("406","用户名重复");
        }else{
            return Result.success("登录成功",one);
        }
    }

}
