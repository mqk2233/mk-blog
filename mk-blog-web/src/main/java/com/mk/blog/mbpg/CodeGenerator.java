package com.mk.blog.mbpg;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sun.javafx.PlatformUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MK
 * @date 2020/12/21
 * see https://www.cnblogs.com/miaoying/p/12625920.html & https://baomidou.com/guide/generator.html
 */
public class CodeGenerator {

    /**
     * modular 名字
     */
    public static final String MODULAR_NAME = "";

    /**
     * 需要去除的表前缀
     */
    public static final String COMMON_PRE_FIX = "";

    /**
     * 基本路径
     */
    private static final String SRC_MAIN_JAVA = "/src/main/java/";

    private static final String PARENT_DIR = System.getProperty("user.dir");

    private static final String ENTITY_PATH = PARENT_DIR + "/mk-blog-model/src/main/java/com/mk/blog/entity";

    private static final String MAPPER_PATH = PARENT_DIR + "/mk-blog-dao/src/main/java/com/mk/blog//mapper";

    private static final String SERVICE_PATH = PARENT_DIR + "/mk-blog-service/src/main/java/com/mk/blog/";

    private static final String SERVICE_IMPL_PATH = PARENT_DIR + "/mk-blog-service/src/main/java/com/mk/blog/impl/";

    private static final String CONTROLLER_PATH = PARENT_DIR + "/mk-blog-web/src/main/java/com/mk/blog/controller";

    /**
     * 作者
     */
    public static final String AUTHOR = "MK";

    public static final String JDBC_MYSQL_URL = "jdbc:mysql://193.112.147.216:3306/blog?serverTimezone=UTC&characterEncoding=utf8";

    public static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    public static final String JDBC_USERNAME = "root";

    public static final String JDBC_PASSWORD = "39397457c1e2e3a3";

    public static void main(String[] args) {
        autoGenerator("article");
    }

    public static void autoGenerator(String tableName) {
        new AutoGenerator()
                .setGlobalConfig(getGlobalConfig())
                .setDataSource(getDataSourceConfig())
                .setPackageInfo(getPackageConfig())
                .setStrategy(getStrategyConfig(tableName))
                .setCfg(getInjectionConfig())
                .setTemplate(getTemplateConfig())
                .execute();
    }

    /**
     * 自定义属性注入配置
     *
     * @return {@link InjectionConfig }
     * @author MK
     * @date 2020/12/23 15:40
     */
    private static InjectionConfig getInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("dateTime", getDateTime());
                setMap(map);
                List<FileOutConfig> fileOutConfigList = new ArrayList<>();
                // 自定义配置会被优先输出
                fileOutConfigList.add(new FileOutConfig("/templates/generator/mapper.xml.vm") {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名，如果entity设置了前后缀，此次注意xml的名称也会跟着发生变化
                        return PARENT_DIR + "/mk-blog-dao/src/main/resources/mappers" + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                    }
                });
                setFileOutConfigList(fileOutConfigList);
            }
        };
    }

    /**
     * 方策略配置法描述
     *
     * @param tableName 表名
     * @return {@link StrategyConfig }
     * @author MK
     * @date 2020/12/23 15:39
     */
    private static StrategyConfig getStrategyConfig(String tableName) {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableName)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
                .setVersionFieldName("")
                .setLogicDeleteFieldName("")
                .setTablePrefix(COMMON_PRE_FIX)
                .setEntityLombokModel(true);
    }

    /**
     * 包配置
     *
     * @return {@link PackageConfig }
     * @author MK
     * @date 2020/12/23 15:39
     */
    private static PackageConfig getPackageConfig() {
        Map<String, String> pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.ENTITY_PATH, ENTITY_PATH);
        pathInfo.put(ConstVal.MAPPER_PATH, MAPPER_PATH);
        pathInfo.put(ConstVal.SERVICE_PATH, SERVICE_PATH);
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, SERVICE_IMPL_PATH);
        pathInfo.put(ConstVal.CONTROLLER_PATH, CONTROLLER_PATH);
        return new PackageConfig()
                .setParent("")
                .setEntity("com.mk.blog.entity")
                .setMapper("com.mk.blog.mapper")
                .setService("com.mk.blog")
                .setServiceImpl("com.mk.blog.impl")
                .setController("com.mk.blog.controller")
                .setPathInfo(pathInfo);
    }

    /**
     * 数据源配置
     *w
     * @return {@link DataSourceConfig }
     * @author MK
     * @date 2020/12/23 15:38
     */
    private static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                .setUrl(JDBC_MYSQL_URL)
                .setDriverName(JDBC_DRIVER_NAME)
                .setUsername(JDBC_USERNAME)
                .setPassword(JDBC_PASSWORD);
    }

    /**
     * 全局配置
     *
     * @return {@link GlobalConfig }
     * @author MK
     * @date 2020/12/23 15:41
     */
    private static GlobalConfig getGlobalConfig() {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/" + MODULAR_NAME + SRC_MAIN_JAVA;
        if (PlatformUtil.isWindows()) {
            filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
        } else {
            filePath = filePath.replaceAll("/+|\\\\+", "/");
        }
        return new GlobalConfig()
                .setOutputDir(filePath)
                .setDateType(DateType.ONLY_DATE)
                .setIdType(IdType.ASSIGN_UUID)
                .setAuthor(AUTHOR)
                .setFileOverride(true)
                .setBaseColumnList(true)
                .setSwagger2(true)
                .setBaseResultMap(true)
                .setOpen(false);
    }

    /**
     * 模板配置
     *
     * @return {@link TemplateConfig }
     * @author MK
     * @date 2020/12/23 15:42
     */
    private static TemplateConfig getTemplateConfig() {
        return new TemplateConfig()
                .setController("/templates/generator/controller.java.vm")
                .setService("/templates/generator/service.java.vm")
                .setServiceImpl("/templates/generator/serviceImpl.java.vm")
                .setEntity("/templates/generator/entity.java.vm")
                .setMapper("/templates/generator/mapper.java.vm")
                .setXml("/templates/generator/mapper.xml.vm");
    }

    /**
     * 获取当前时间
     *
     * @return {@link String }
     * @author MK
     * @date 2020/12/23 15:41
     */
    private static String getDateTime() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDate.format(formatter);
    }
}