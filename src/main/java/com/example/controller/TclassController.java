package com.example.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Tclass;
import com.example.service.TclassService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tclass")
@RestController
public class TclassController {

    @Resource
    private TclassService tclassService;

    /**
     * 导出接口
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        List<Tclass> list = tclassService.list();

        //查询出所有的数据
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //设置excel文件的表头标题
        writer.addHeaderAlias("year","学年学期");
        writer.addHeaderAlias("courseName","课程");
        writer.addHeaderAlias("teacherName","教师");
        writer.addHeaderAlias("teacherTitle","职称");
        writer.addHeaderAlias("isSpecial","是否特殊教师");
        writer.addHeaderAlias("classState","课程属性");
        writer.addHeaderAlias("classXinZhi","课程性质");
        writer.addHeaderAlias("score","学分");
        writer.addHeaderAlias("time","总学时");
        writer.addHeaderAlias("lTime","理论学时");
        writer.addHeaderAlias("sTime","实验学时");
        writer.addHeaderAlias("cTime","上机学时");
        writer.addHeaderAlias("pTime","实践学时");
        writer.addHeaderAlias("together","集中实践");
        writer.addHeaderAlias("classNum","班级数");
        writer.addHeaderAlias("className","班级名称");
        writer.addHeaderAlias("classNumber","课堂名称");
        writer.addHeaderAlias("num","选课人数");
        writer.addHeaderAlias("orginazition","承担单位");
        writer.addHeaderAlias("bounce","工作量");

        //将结果写入excel
        writer.write(list,true);

        //设置网页请求返回结果
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("课程信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        //输出流
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();



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
        List<Tclass> tclasses = new ArrayList<>();
        //将excel表中的数据存入到对象中
        for(List<Object> row : read)
        {
            Tclass tclass = new Tclass();
            tclass.setYear(row.get(0).toString());
            tclass.setCourseName(row.get(1).toString());
            tclass.setTeacherName(row.get(2).toString());
            tclass.setTeacherTitle(row.get(3).toString());
            tclass.setIsSpecial(row.get(4).toString());
            tclass.setClassState(row.get(5).toString());
            tclass.setClassXinZhi(row.get(6).toString());
            tclass.setScore(Double.valueOf(row.get(7).toString()));
            tclass.setTime(Integer.valueOf(row.get(8).toString()));
            tclass.setLTime(Integer.valueOf(row.get(9).toString()));
            tclass.setSTime(Integer.valueOf(row.get(10).toString()));
            tclass.setCTime(Integer.valueOf(row.get(11).toString()));
            tclass.setPTime(Integer.valueOf(row.get(12).toString()));
            tclass.setTogether(row.get(13).toString());
            tclass.setClassNum(Integer.valueOf(row.get(14).toString()));
            tclass.setClassName(row.get(15).toString());
            tclass.setClassNumber(row.get(16).toString());
            tclass.setNum(Integer.valueOf(row.get(17).toString()));
            tclass.setOrginazition(row.get(18).toString());
//            tclass.setBounce(row.get(19).toString());
            tclasses.add(tclass);
        }
        tclassService.saveBatch(tclasses);
        return true;
    }

    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @param teacherName
     * @param className
     * @return
     */
    @GetMapping("/page")
//    @Cacheable(value = "teacher")
    public Result findByPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam String teacherName,
                                @RequestParam String className)
    {
        QueryWrapper<Tclass> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("teacher_name",teacherName).like("course_name",className);
        IPage<Tclass> page = tclassService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success("分页查询成功",page);
    }

    /**
     * 新增和更新接口
     * @param tclass
     * @return
     */
    @PostMapping
//    @CachePut(value = "teacher")
    public Result save(@RequestBody Tclass tclass)
    {
        return tclassService.saveorUpdate(tclass);
    }

    /**
     * 删除接口，根据id进行删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
//    @CacheEvict(value = "teacher", key = "#id")
    public Result deleteById(@PathVariable Integer id)
    {
        return Result.success("删除成功",tclassService.removeById(id));
    }

    /**
     * 计算分数接口
     * @param tclass
     * @return
     */
    @PostMapping("/calculate")
    public Result calculate(@RequestBody Tclass tclass)
    {
        double time = tclass.getTime();
        double num = tclass.getNum();
        double xishu;
        if(num <= 30) xishu = 1.0;
        else if(num > 30 && num <= 45) xishu = 1.1;
        else if(num > 45 && num <= 60) xishu = 1.2;
        else if(num > 60 && num <= 90) xishu = 1.3;
        else if(num > 90 && num <= 120) xishu = 1.4;
        else xishu = 1.5;
        tclass.setBounce(time * xishu);
        return Result.success("核算工作量成功",tclassService.updateById(tclass));
    }

    /**
     * 计算指定老师的工作量之和
     * @param teachername
     * @return
     */
    @GetMapping("/{teachername}")
//    @Cacheable(value = "bounce")
    public Result count(@PathVariable String teachername)
    {
        return Result.success("老师的工作量统计成功",tclassService.countwork(teachername));
    }

    /**
     * 查询所有老师的工作量之和
     * @return
     */
    @GetMapping("/count")
//    @Cacheable(value = "bounce")
    public Result count()
    {
        return Result.success("所有老师的工作量统计成功",tclassService.countAll());
    }


    /**
     * 一键核算功能
     * @param list
     * @return
     */
    @PostMapping("/calculate/all")
    public Result calculate(@RequestBody List<Tclass> list)
    {
        for(int i = 0; i < list.size(); i ++)
        {
            Integer time = list.get(i).getTime();
            Integer num = list.get(i).getNum();
            double xishu;
            if(num <= 30) xishu = 1.0;
            else if(num > 30 && num <= 45) xishu = 1.1;
            else if(num > 45 && num <= 60) xishu = 1.2;
            else if(num > 60 && num <= 90) xishu = 1.3;
            else if(num > 90 && num <= 120) xishu = 1.4;
            else xishu = 1.5;
            list.get(i).setBounce(time * xishu);
            tclassService.updateById(list.get(i));
        }
        return Result.success("一键核算成功",null);
    }
}
