package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.ComputerPractice;
import com.example.service.ComputerPracticeService;
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
@RequestMapping("/computerpractice")
public class ComputerPracticeController {
    @Resource
    private ComputerPracticeService computerPracticeService;
    @Resource
    private HttpServletRequest request;

    @PostMapping
    public Result save(@RequestBody ComputerPractice computerPractice) {
        computerPracticeService.save(computerPractice);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody ComputerPractice computerPractice) {
        computerPracticeService.updateById(computerPractice);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        computerPracticeService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        return Result.success("成功",computerPracticeService.getById(id));
    }

    @GetMapping
    public Result findAll() {
        List<ComputerPractice> list = computerPracticeService.list();
        return Result.success("yes",list);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<ComputerPractice> query = Wrappers.<ComputerPractice>lambdaQuery().orderByDesc(ComputerPractice::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(ComputerPractice::getClassName, name);
        }
        IPage<ComputerPractice> page = computerPracticeService.page(new Page<>(pageNum, pageSize), query);
        return Result.success("yes",page);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<ComputerPractice> all = computerPracticeService.list();
        for (ComputerPractice obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("", obj.getId());
            row.put("课程", obj.getCourseName());
            row.put("教师", obj.getTeacherName());
            row.put("课程属性", obj.getState());
            row.put("课程性质", obj.getType());
            row.put("学分", obj.getScore());
            row.put("班级名称", obj.getClassName());
            row.put("学期", obj.getYear());
            row.put("实际上机时间", obj.getComputerTime());
            row.put("工作量", obj.getComputerpraticeBounce());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("上机实践信息", "UTF-8");
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
        List<ComputerPractice> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            ComputerPractice obj = new ComputerPractice();
            obj.setCourseName((String) row.get(0));
            obj.setTeacherName((String) row.get(1));
            obj.setState((String) row.get(2));
            obj.setType((String) row.get(3));
            obj.setScore(Integer.valueOf(row.get(4).toString()));
            obj.setClassName((String) row.get(5));
            obj.setYear((String) row.get(6));
            obj.setComputerTime(Integer.valueOf(row.get(7).toString()));
            obj.setComputerpraticeBounce(Double.valueOf(row.get(8).toString()));
            saveList.add(obj);
        }
        computerPracticeService.saveBatch(saveList);
        return Result.success();
    }

    @GetMapping("/count")
    public Result count(@RequestBody ComputerPractice computerPractice)
    {
        String teacherName = computerPractice.getTeacherName();
        return Result.success(computerPracticeService.countA(teacherName));
    }
    @GetMapping("/countAll")
    public Result countAll()
    {
        return Result.success(computerPracticeService.countAll());
    }

    @PostMapping("/calculate")
    public Result calculate(@RequestBody ComputerPractice computerPractice)
    {
        String className = computerPractice.getClassName();
        String[] split = className.split(",");
        Integer computerTime = computerPractice.getComputerTime();
        double xishu;
        if(split.length == 1) xishu = 0.5;
        else if(split.length == 2) xishu = 0.7;
        else if(split.length == 3) xishu = 0.9;
        else xishu = 1.1;
        double bounce = computerTime * xishu;
        computerPractice.setComputerpraticeBounce(bounce);
        return Result.success("成功",computerPracticeService.updateById(computerPractice));
    }

    @PostMapping("/calculateAll")
    public Result calculate(@RequestBody List<ComputerPractice> list)
    {
        for (int i = 0; i < list.size(); i++) {
            ComputerPractice computerPractice = list.get(i);
            String className = computerPractice.getClassName();
            String[] split = className.split(",");
            Integer computerTime = computerPractice.getComputerTime();
            double xishu;
            if(split.length == 1) xishu = 0.5;
            else if(split.length == 2) xishu = 0.7;
            else if(split.length == 3) xishu = 0.9;
            else xishu = 1.1;
            double bounce = computerTime * xishu;
            computerPractice.setComputerpraticeBounce(bounce);
            computerPracticeService.updateById(computerPractice);
        }
        return Result.success("成功");
    }

}
