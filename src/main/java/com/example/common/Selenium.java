package com.example.common;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.PaperChinese;
import com.example.entity.ScoreRule;
import com.example.mapper.PaperChineseMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Resource;
import java.util.Arrays;

public class Selenium {
    /**
     * get 论文信息
     * @param time01
     * @param time02
     * @return
     */
    @Resource
    private PaperChineseMapper paperChineseMapper;

    public void getData(String time01,String time02) throws Exception{

        System.setProperty("webdriver.chrome.driver", "D:\\综合考核系统\\end_base\\chromedriver.exe");
        ChromeOptions options=new ChromeOptions();//这两步和java的无界面化浏览有关系
        //options.addArguments("headless");
        ChromeDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.get("https://kns.cnki.net/kns8/AdvSearch");
        chromeDriver.manage().window().maximize();
        WebElement button01 = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[4]"));
        button01.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement input01 = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/textarea"));
        input01.sendKeys("AF=中南大学商学院");
        input01.click();
//
        WebElement input02 = chromeDriver.findElement(By.xpath("//*[@id='datebox0']"));
        WebElement input03 = chromeDriver.findElement(By.xpath("//*[@id='datebox1']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) chromeDriver;
        jsExecutor.executeScript("document.getElementById('datebox0').removeAttribute('readonly')");
        input02.sendKeys(time01);
        jsExecutor.executeScript("document.getElementById('datebox1').removeAttribute('readonly')");
        input03.sendKeys(time02);
        WebElement button02 = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div[2]/input"));
        button02.click();
        //显示等待
//        Duration time01 =Duration.ofSeconds(10);
//        WebDriverWait wait =new WebDriverWait(chromeDriver,time01);
//        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='countPageDiv']/span[1]/em")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement num01 = chromeDriver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/form/div/div[1]/div[1]/span[1]/em"));//num01为element形式的总条数
        String num02 = num01.getText();//num02为string形式
        Integer num04 = Integer.parseInt(num02);//num04为int类型
        System.out.println("结果展示");
        float num03 = Float.valueOf(num02).floatValue();//num3为float形式，因为在java中向上取整数的话需要小数点后有数字,要转化为float计算

        Integer page = (int) Math.ceil(num03 / 20);
        System.out.println(page);
        if (page == 1) {
            chromeDriver.findElement(By.xpath("//*[@id='selectCheckAll1']")).click();
        } else {
            for (int n = 2; n <= page; n++) {
                WebElement allSelect = chromeDriver.findElement(By.xpath("//*[@id='selectCheckAll1']"));
                allSelect.click();
                String xpath = "//*[@id='page" + String.valueOf(n) + "']";
                WebElement pageButton = chromeDriver.findElement(By.xpath(xpath));
                pageButton.click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (n == page) {
                    chromeDriver.findElement(By.xpath("//*[@id='selectCheckAll1']")).click();
                }

            }
        }
        chromeDriver.findElement(By.xpath("//*[@id='batchOpsBox']/li[2]/a")).click();
        chromeDriver.findElement(By.xpath("//*[@id='batchOpsBox']/li[2]/ul/li[1]")).click();
        chromeDriver.findElement(By.xpath("//*[@id='batchOpsBox']/li[2]/ul/li[1]/ul/li[12]/a")).click();
        String winHandle = chromeDriver.getWindowHandle();
        for (String winHandle01 : chromeDriver.getWindowHandles()) {
            if (winHandle01.equals(winHandle)) {
                continue;
            }
            chromeDriver.switchTo().window(winHandle01);
            break;
        }//79-86行都是转换窗口的代码
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        chromeDriver.findElement(By.xpath("//*[@id='result']/div/label[16]/input")).click();
        chromeDriver.findElement(By.xpath("//*[@id='result']/div/label[18]/input")).click();
        chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div/label[8]/input")).click();
        chromeDriver.findElement(By.xpath("//*[@id='aPreview']")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //先用document获取整个页面的html代码
        //然后找到需要循环遍历的那一行
        ///
        Document document = Jsoup.parse(chromeDriver.getPageSource());
        Elements lis = document.select("ul.literature-list>li");//由于jsoup中不支持xpath，所以这个使用selector来定位元素的
        for (int m = 0; m < num03; m++) {

            String li = lis.get(m).text();//lis.get(m)就是获取到dim个元素

            String[] data = li.split(" ");
            // 在这里导入数据库
            String title = data[3];
            String author = data[5];
            author = author.replace(";", ",");
            String unit = data[7];
            String journal = data[9];
            //Integer counts = paperChineseMapper.selectCount(new QueryWrapper<PaperChinese>().eq("score_type", title));
            System.out.println(Arrays.toString(data));
        }
        chromeDriver.quit();
    }
}

