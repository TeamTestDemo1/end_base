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
import com.example.entity.ZhiDao;
import com.example.service.ZhiDaoService;
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
@RequestMapping("/zhidao")
public class ZhiDaoController {
    @Resource
    private ZhiDaoService zhiDaoService;
    @Resource
    private HttpServletRequest request;

    @PostMapping
    public Result save(@RequestBody ZhiDao zhiDao) {
        zhiDaoService.save(zhiDao);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody ZhiDao zhiDao) {
        zhiDaoService.updateById(zhiDao);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        zhiDaoService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        return Result.success("success",zhiDaoService.getById(id));
    }

    @GetMapping
    public Result findAll() {
        List<ZhiDao> list = zhiDaoService.list();
        return Result.success("success",list);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<ZhiDao> query = Wrappers.<ZhiDao>lambdaQuery().orderByDesc(ZhiDao::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(ZhiDao::getTeacherName, name);
        }
        IPage<ZhiDao> page = zhiDaoService.page(new Page<>(pageNum, pageSize), query);
        return Result.success("success",page);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<ZhiDao> all = zhiDaoService.list();
        for (ZhiDao obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("", obj.getId());
            row.put("年度", obj.getYear());
            row.put("指导人数", obj.getNum());
            row.put("学生姓名", obj.getStudentName());
            row.put("学生类型", obj.getStudentType());
            row.put("是否答辩秘书", obj.getIsSecretor());
            row.put("担任答辩秘书班级名称", obj.getSecretorClassName());
            row.put("答辩秘书工作量", obj.getSecretorBounce());
            row.put("总工作量", obj.getZhidaoBounce());
            row.put("教师姓名",obj.getTeacherName());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("本科生指导教学工作量信息", "UTF-8");
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
        List<ZhiDao> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            ZhiDao obj = new ZhiDao();
            obj.setYear((String) row.get(0));
            obj.setNum(Integer.valueOf((String) row.get(1)));
            obj.setStudentName((String) row.get(2));
            obj.setStudentType((String) row.get(3));
            obj.setIsSecretor((String) row.get(4));
            obj.setSecretorClassName((String) row.get(5));
//            obj.setSecretorBounce(Integer.valueOf(row.get(6).toString()));
//            obj.setZhidaoBounce(Integer.valueOf(row.get(7).toString()));
            obj.setTeacherName((String) row.get(8));

            saveList.add(obj);
        }
        zhiDaoService.saveBatch(saveList);
        return Result.success();
    }

    @GetMapping("/count")
    public Result count(@RequestBody ZhiDao zhiDao)
    {
        String teacherName = zhiDao.getTeacherName();
        return Result.success(zhiDaoService.countAll(teacherName));
    }
    @GetMapping("/countall")
    public Result countAll()
    {
        return Result.success(zhiDaoService.countA());
    }
}
