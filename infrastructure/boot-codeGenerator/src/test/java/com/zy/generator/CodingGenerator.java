package com.zy.generator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 张雨 - 栀
 * @version 1.0
 * @create 2022/3/2 12:13
 */
public class CodingGenerator {

    private static final String url = "jdbc:mysql://localhost:3306/study?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "151613";

    @Test
    public void DaoGenerator(){
        // 添加 生成 数据库表
        List<String> tables = new CopyOnWriteArrayList<>();
        tables.add("users");

        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("zy-栀")      // 设置作者
                            .enableSwagger()    // 开启 swagger 模式
                            .fileOverride()     // 多次生成文件，覆盖已生成文件
//                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    // 指定输出目录
                            .outputDir("D:\\IT\\studyProjects\\IDEA Files\\Spring\\spring-boot-template\\boot-codeGenerator\\src\\main\\java")    // 指定输出目录
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd");
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.zy")          // 设置父包名
                            .moduleName("generator")  // 设置父包模块名
                            .entity("entity")         // 设置实体包名
                            .service("service")       // 设置 service 包名
                            .serviceImpl("service.impl")  // 设置 serviceImpl 包名
                            .mapper("mapper")         // 设置 DAO 层包名
                            .xml("mappers")           // 设置 mapper 映射文件名， 由于映射文件在resources中，所以需额外配置
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\IT\\studyProjects\\IDEA Files\\Spring\\spring-boot-template\\boot-codeGenerator\\src\\main\\resources\\mappers")) // 设置 mapperXml 生成路径;
                            .controller("controller") // 设置 controller 层包名
                            .other("other");
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables)    // 设置需要生成的表名
//                          .addTablePrefix("p_")   // 过滤表前缀
                            // service 配置策略
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            // entity 配置策略
                            .entityBuilder()
//                            .enableLombok()    // 开启 lombok 注解
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()   // 属性上加说明
                            // controller 配置策略
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()   // 开启 RestController
                            // mapper 配置策略
                            .mapperBuilder()
                            .superClass(BaseMapper.class)  // 继承于哪个类？
                            .formatMapperFileName("%sMapper")   // dao 名
                            .enableMapperAnnotation()  // 开启 @mapper 注解
                            .formatXmlFileName("%sMapper");    // xml 名
                })
                // 引擎配置
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
