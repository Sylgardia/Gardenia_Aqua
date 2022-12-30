package com.zy.generator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
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

    private static final String host = "47.100.30.174";
    private static final String port = "3306";
    private static final String database = "wuliu_ed";

    private static final String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "EDTeam01!";

    private static final String LOCALPATH = "D:\\Gardenia_ZY\\Spring\\Gardenia_admin\\infrastructure\\boot-codeGenerator";

    @Test
    public void DaoGenerator(){
        // 添加 生成 数据库表
        List<String> tables = new CopyOnWriteArrayList<>();
        tables.add("sp_ship");
        tables.add("sp_ship_ref");

        FastAutoGenerator.create(url, username, password)
                // Global Configuration
                .globalConfig(builder -> {
                    builder.author("zy-栀")       // Emplace Author
                            .enableSwagger()     // open swagger Mode
                            .fileOverride()      // 多次生成文件，覆盖已生成文件
//                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    // Appoint 输出目录
                            .outputDir(LOCALPATH + "\\src\\main\\java")    // Appoint 输出目录
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd");
                })
                // Package Configuration
                .packageConfig(builder -> {
                    builder.parent("com.zy.generator")      // Emplace 父包名
                            .moduleName("generator")        // Emplace 父包模块名  (以上两个记得修改)
                            .entity("entity")               // Emplace 实体包名
                            .service("service")             // Emplace  service 包名
                            .serviceImpl("service.impl")    // Emplace  serviceImpl 包名
                            .mapper("mapper")               // Emplace  DAO 层包名
                            .xml("mappers")                 // Emplace  mapper 映射文件名， 由于映射文件在resources中，所以需额外配置
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, LOCALPATH + "\\src\\main\\java\\com\\zy\\generator\\generator\\mapper\\xml")) // 设置 mapperXml 生成路径;
                            .controller("controller")       // Emplace  controller 层包名
                            .other("other");
                })
                // Tactics Configuration
                .strategyConfig(builder -> {
                    builder.addInclude(tables)      // Emplace 需要生成的表名
//                          .addTablePrefix("p_")   // 过滤表前缀

                            // entity Configuration Tactics
                            .entityBuilder()
                            .enableLombok()         // open lombok explain
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()   // Attribute explain

                            // mapper Configuration Strategy
                            .mapperBuilder()
                            .superClass(BaseMapper.class)       // extend Class
                            .formatMapperFileName("%sMapper")   // dao name
                            .enableMapperAnnotation()           // open @mapper Annotation
                            .formatXmlFileName("%sMapper")      // xml name

                            // mapper Configuration Strategy
                            .mapperBuilder()
                            .superClass(BaseMapper.class)       // extend Class
                            .formatMapperFileName("%sMapper")   // dao name
                            .enableMapperAnnotation()           // open @mapper Annotation
                            .formatXmlFileName("%sMapper")      // xml name

                            // service Configuration Strategy
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")

                            // controller Configuration Strategy
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle();                 // open RestController
                })
                // Template Configuration
//                .templateConfig(builder -> {
//                    builder
//                            .disable(TemplateType.ENTITY)      // stop use config
//                            .entity("/templates/entity.java")
//                            .service("/templates/service.java")
//                            .serviceImpl("/templates/serviceImpl.java")
//                            .mapper("/templates/mapper.java")
//                            .mapperXml("/templates/mapper.xml")
//                            .controller("/templates/controller.java");
//                })
                // 引擎配置
                .templateEngine(new VelocityTemplateEngine()) // Use Freemarker 引擎模板，默认的是 Velocity 引擎模板
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
