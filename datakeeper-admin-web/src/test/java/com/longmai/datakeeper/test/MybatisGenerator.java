package com.longmai.datakeeper.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MybatisGenerator {

    private final String jdbcUrl = "jdbc:sqlite:/home/laiyz/projects/DataKeeperAdmin/datakeeper-admin-web/src/main/resources/datakeeper-db.sqlite";
    private final String username = "";
    private final String password = "";
    private final String outputJavaDir = "/home/laiyz/桌面/java";
    private final String parentPackageName = "com.longmai.datakeeper.dao";
    private final String outputMapperXmlDir = "/home/laiyz/桌面/xml";

    @Test
    public void gen(){

        FastAutoGenerator.create(jdbcUrl, username, password)
                .globalConfig(builder -> {
                    builder.author("longmai") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputJavaDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackageName) // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputMapperXmlDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("dk_masking_column","dk_db_user_masking_column","dk_db_masking_user","dk_masking_algorithm") // 设置需要生成的表名
                            .addTablePrefix("dk_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


}
