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
import com.example.entity.Practice;
import com.example.service.PracticeService;
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
@RequestMapping("practice")
public class PracticeController {
    @Resource
    private PracticeService practiceService;
    @Resource
    private HttpServletRequest request;;

    @PostMapping
    public Result save(@RequestBody Practice practice) {
        practiceService.save(practice);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Practice practice) {
        practiceService.updateById(practice);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        practiceService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        return Result.success("成功",practiceService.getById(id));
    }

    @GetMapping
    public Result findAll() {
        List<Practice> list = practiceService.list();
        return Result.success("成功",list);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Practice> query = Wrappers.<Practice>lambdaQuery().orderByDesc(Practice::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(Practice::getCourseName, name);
        }
        IPage<Practice> page = practiceService.page(new Page<>(pageNum, pageSize), query);
        return Result.success("成功",page);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<Practice> all = practiceService.list();
        for (Practice obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("", obj.getId());
            row.put("年度", obj.getYear());
            row.put("课程名称", obj.getCourseName());
            row.put("指导教师", obj.getTeacherName());
            row.put("指导班级", obj.getClassName());
            row.put("工作量", obj.getBounce());
            row.put("备注", obj.getNote());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("社会实践信息", "UTF-8");
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
        List<Practice> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            Practice obj = new Practice();
            obj.setYear((String) row.get(0));
            obj.setCourseName((String) row.get(1));
            obj.setTeacherName((String) row.get(2));
            obj.setClassName((String) row.get(3));
            obj.setBounce(Double.valueOf(row.get(4).toString()));
            obj.setNote((String) row.get(5));

            saveList.add(obj);
        }
        practiceService.saveBatch(saveList);
        return Result.success();
    }

    @GetMapping("/count")
    public Result countAll(@RequestBody Practice practice)
    {
        String teacherName = practice.getTeacherName();
        return Result.success(practiceService.countAll (teacherName));
    }
    @GetMapping("/countall")
    public Result count()
    {
        return Result.success(practiceService.countA());
    }

    @PostMapping("/calculate")
    //前端传数据，每个实践赋值为8个工作量
    public Result calculate(@RequestBody Practice practice)
    {
        practice.setBounce(8.00);
        return Result.success("计算成功",practiceService.updateById(practice));
    }

    @PostMapping("/calculateAll")
    //前端传数组，一件核算工作量
    public Result calculateAll(@RequestBody List<Practice> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            Practice practice = list.get(i);
            practice.setBounce(8.00);
            practiceService.updateById(practice);
        }
        return Result.success("一件核算成功");
    }
}
