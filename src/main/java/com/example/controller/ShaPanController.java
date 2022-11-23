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
import com.example.entity.ShaPan;
import com.example.service.ShaPanService;
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
@RequestMapping("/shapan")
public class ShaPanController {
    @Resource
    private ShaPanService shaPanService;
    @Resource
    private HttpServletRequest request;

    @PostMapping
    public Result<?> save(@RequestBody ShaPan shaPan) {
        shaPanService.save(shaPan);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody ShaPan shaPan) {
        shaPanService.updateById(shaPan);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        shaPanService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(shaPanService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        List<ShaPan> list = shaPanService.list();
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<ShaPan> query = Wrappers.<ShaPan>lambdaQuery().orderByDesc(ShaPan::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(ShaPan::getTeacherName, name);
        }
        IPage<ShaPan> page = shaPanService.page(new Page<>(pageNum, pageSize), query);
        return Result.success(page);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<ShaPan> all = shaPanService.list();
        for (ShaPan obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("", obj.getId());
            row.put("年度", obj.getYear());
            row.put("课程名称", obj.getClassName());
            row.put("指导教师", obj.getTeacherName());
            row.put("班级人数", obj.getClassNum());
            row.put("学分", obj.getScore());
            row.put("实际课时", obj.getTrueScore());
            row.put("工作量", obj.getBounce());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("沙盘模拟工作量信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }

    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> lists = reader.read(1);
        List<ShaPan> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            ShaPan obj = new ShaPan();
            obj.setYear((String) row.get(0));
            obj.setClassName((String) row.get(1));
            obj.setTeacherName((String) row.get(2));
            obj.setClassNum((String) row.get(3));
            obj.setScore(Integer.valueOf(row.get(4).toString()));
            obj.setTrueScore(Integer.valueOf(row.get(5).toString()));
            obj.setBounce(Double.valueOf(row.get(6).toString()));

            saveList.add(obj);
        }
        shaPanService.saveBatch(saveList);
        return Result.success();
    }

    @GetMapping("/count")
    public Result count(@RequestBody ShaPan shaPan)
    {
        String teacherName = shaPan.getTeacherName();
        return Result.success(shaPanService.countAll(teacherName));
    }
    @GetMapping("/countAll")
    public Result counta()
    {
        return Result.success(shaPanService.countA());
    }
    @PostMapping("/calculate")
    public Result calculate(@RequestBody ShaPan shaPan)
    {
        String courseName = shaPan.getClassName();
        Integer trueScore = shaPan.getTrueScore();
        double xishu = 0.0;
        if(courseName.equals("企业经营模拟")) xishu = 1.5;
        else xishu = 1.0;
        double bounce = trueScore * xishu;
        shaPan.setBounce(bounce);
        return Result.success("核算成功",shaPanService.updateById(shaPan));
    }
    @PostMapping("/calculateAll")
    public Result calculate(@RequestBody List<ShaPan> list)
    {
        for(int i = 0; i < list.size(); i ++)
        {
            ShaPan shaPan = list.get(i);
            String courseName = shaPan.getClassName();
            Integer trueScore = shaPan.getTrueScore();
            double xishu = 0.0;
            if(courseName.equals("企业经营模拟")) xishu = 1.5;
            else xishu = 1.0;
            double bounce = trueScore * xishu;
            shaPan.setBounce(bounce);
            shaPanService.updateById(shaPan);
        }
        return Result.success("一件核算成功");
    }
}
