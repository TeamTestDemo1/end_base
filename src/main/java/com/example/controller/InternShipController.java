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
import com.example.entity.InternShip;
import com.example.service.InternShipService;
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
@RequestMapping("/internship")
public class InternShipController {
    @Resource
    private InternShipService internShipService;
    @Resource
    private HttpServletRequest request;

    @PostMapping
    public Result save(@RequestBody InternShip internShip) {
        internShipService.save(internShip);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody InternShip internShip) {
        internShipService.updateById(internShip);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        internShipService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        return Result.success("成功",internShipService.getById(id));
    }

    @GetMapping
    public Result findAll() {
        List<InternShip> list = internShipService.list();
        return Result.success("成功",list);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<InternShip> query = Wrappers.<InternShip>lambdaQuery().orderByDesc(InternShip::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(InternShip::getTeacherName, name);
        }
        IPage<InternShip> page = internShipService.page(new Page<>(pageNum, pageSize), query);
        return Result.success("成功",page);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<InternShip> all = internShipService.list();
        for (InternShip obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("", obj.getId());
            row.put("年度", obj.getYear());
            row.put("专业班级", obj.getClassName());
            row.put("学生人数", obj.getStudentNum());
            row.put("教师姓名", obj.getTeacherName());
            row.put("职称", obj.getPosition());
            row.put("是否队长", obj.getIsMonitor());
            row.put("起止日期", obj.getTime());
            row.put("持续周数", obj.getWeekNum());
            row.put("实习类别", obj.getType());
            row.put("实习单位",obj.getOrgnazition());
            row.put("工作量", obj.getInternshipBounce());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("实习信息", "UTF-8");
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
        List<InternShip> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            InternShip obj = new InternShip();
            obj.setYear(row.get(0).toString());
            obj.setClassName((String) row.get(1));
            obj.setStudentNum(Integer.valueOf(row.get(2).toString()));
            obj.setTeacherName((String) row.get(3));
            obj.setPosition((String) row.get(4));
            obj.setIsMonitor((String) row.get(5));
            obj.setTime((String) row.get(6));
            obj.setWeekNum(Integer.valueOf(row.get(7).toString()));
            obj.setType((String) row.get(8));
            obj.setOrgnazition((String) row.get(9));
            obj.setInternshipBounce(Double.valueOf(row.get(10).toString()));
            saveList.add(obj);
        }
        internShipService.saveBatch(saveList);
        return Result.success();
    }

    @GetMapping("/count")
    public Result countAll(@RequestBody InternShip internShip)
    {
        String teacherName = internShip.getTeacherName();
        return Result.success(internShipService.countAll(teacherName));
    }
    @GetMapping("/countall")
    public Result count()
    {
        return Result.success(internShipService.countA());
    }

}
