package com.zy.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.zy.generator.template.controller.BaseController;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 张雨 - 栀
 * @version 1.0
 * @create 2022/3/2 12:13
 */
public class CodingGenerator {

    private static final String host = "127.0.0.1";
    private static final String port = "3306";
    private static final String database = "cjw_sb";

    private static final String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "12345678990";

    private static final String LOCALPATH = "D:\\Gardenia_ZY\\Spring\\Gardenia_admin\\infrastructure\\boot-codeGenerator\\";
    private static final String PARENT_NAME = "org.zy.modules";
    private static final String MODULE_NAME = "transReq";
    private static final String MAPPER_PATH = "src\\main\\java\\org\\zy\\modules\\transReq\\mapper\\xml";

    public static void main(String[] args) {
        new CodingGenerator().daoGenerator();
    }

    public void daoGenerator() {
        // 添加 生成 数据库表
        List<String> tables = new CopyOnWriteArrayList<>();
        tables.add("sys_log_01");

        FastAutoGenerator.create(url, username, password)
                // Global Configuration
                .globalConfig(builder -> {
                    builder.author("zy-栀")       // Emplace Author
                            .enableSwagger()     // open swagger Mode
                            .fileOverride()      // 多次生成文件，覆盖已生成文件
//                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    // Appoint Output Dir
                            .outputDir(LOCALPATH + "\\src\\main\\java")    // Appoint 输出目录
                            .dateType(DateType.ONLY_DATE)         // ONLY_DATE:java.util.Date    TIME_PACK:LocalDateTime
                            .commentDate("YYYY-MM-DD HH:mm:ss");
                })
                // Package Configuration
                .packageConfig(builder -> {
                    builder.parent(PARENT_NAME)             // Emplace 父包名
                            .moduleName(MODULE_NAME)        // Emplace 父包模块名  (以上两个记得修改)
                            .entity("entity")               // Emplace 实体包名
                            .service("service")             // Emplace  service 包名
                            .serviceImpl("service.impl")    // Emplace  serviceImpl 包名
                            .mapper("mapper")               // Emplace  DAO 层包名
                            .xml("mappers")                 // Emplace  mapper 映射文件名， 由于映射文件在resources中，所以需额外配置
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, LOCALPATH + MAPPER_PATH)) // intercalate mapperXml 生成路径
                            .controller("controller")       // Emplace  controller 层包名
                            .other("other");
                })
                // Tactics Configuration
                .strategyConfig(builder -> {
                    builder.addInclude(tables)      // Emplace 需要生成的表名
//                          .addTablePrefix("p_")   // 过滤表前缀

                            // entity Configuration Tactics
                            .entityBuilder()
                            .enableLombok()                     // open lombok explain
                            .enableChainModel()
                            .idType(IdType.ASSIGN_ID)           // Different Id Generation Type (AUTO、NONE、INPUT、ASSIGN_UUID、ASSIGN_ID)
                            .logicDeleteColumnName("del_flag")  // Default deleted attribute
                            .enableTableFieldAnnotation()       // Attribute explain

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
                            .superServiceClass(IService.class)
                            .superServiceImplClass(ServiceImpl.class)

                            // controller Configuration Strategy
                            .controllerBuilder()
                            .formatFileName("%sController")
//                            .superClass(BaseController.class)
                            .enableRestStyle();                 // open RestController
                })
                // Template Configuration
                .templateConfig(builder -> {
                    builder
                            .disable(TemplateType.CONTROLLER)      // stop use config
                            .entity("/moulds/entity.java")
                            .mapper("/moulds/mapper.java")
                            .mapperXml("/moulds/mapper.xml")
                            .service("/moulds/service.java")
                            .serviceImpl("/moulds/serviceImpl.java")
                            .controller("/moulds/controller.java")
//                            .controller("/templates/controller.java.vm")
                            .build();
                })
                // Injection Configuration
//                .injectionConfig(builder -> builder
//                        .beforeOutputFile((tableInfo, objectMap) -> {
//                            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
//                        }) // 输出文件之前消费者
//                        .customMap(Collections.singletonMap("my_field", "自定义配置 Map 对象")) //自定义配置 Map 对象
//                        .customFile(Collections.singletonMap("query.java", "/templates/query.java.vm")) //自定义配置模板文件
//                        .build()// 加入构建队列
//                )
                // Engine Configuration
                .templateEngine(getTemplateEngine()) // Use Freemarker Engine   Default Velocity Engine
                .execute();
    }

    /**
     * 获取 模板引擎
     *
     * @return AbstractTemplateEngine
     */
    private AbstractTemplateEngine getTemplateEngine() {
        return new VelocityTemplateEngine();
    }

    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
//            p.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
//            p.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            p.setProperty(Velocity.INPUT_ENCODING, "classpath");

            // 初始化 Velocity 引擎，指定配置 Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
