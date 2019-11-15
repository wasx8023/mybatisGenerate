package com.demon.mybatis.generator.mybatis;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.demon.mybatis.generator.TemplateOption;
import com.demon.mybatis.generator.common.DatabaseOption;
import com.demon.mybatis.generator.model.Database;
import com.demon.mybatis.generator.model.Table;

/**
 * 代码生成类
 */
public class MybatisCodeGenerator {

    private Logger logger = Logger.getLogger(MybatisCodeGenerator.class);

    //是否去掉模块前面的编号
    public static String prefix = "t_";

    /**
     * 从页面获取到的参数
     * @param classDriver 数据库版本
     * @param url 数据库地址
     * @param username 数据库用户名
     * @param password 数据库密码
     * @param classPackage 要生成的文件的包名前缀
     * @param author 作者
     * @param tableNames 指定要生成的数据库表名，不指定则会生成所有表
     * @param codePath 代码保存路径
     * @return
     */
    public boolean generator(final String classDriver, final  String url, final String username, final String password,
                             final String classPackage, final String author, final String tableNames, final String codePath) {

        String sourcePath = codePath + File.separator + "src/";
        Long start = System.currentTimeMillis();
        try {
            // 获取数据库名
            String schema = url.substring(url.lastIndexOf("/") + 1);

            // 获取数据库信息
            Database databaseBean = new DatabaseOption(classDriver, url, username, password, schema).getDbInfo(tableNames);

            // 获取该库所有表
            List<Table> tableList = databaseBean.getTableList();

            logger.info("---------------start---------------");
            // 要生成的表名
            List<String> tableNameList = null;

            if(StringUtils.isNotBlank(tableNames)) {
                String[] tablesArr = tableNames.split(",");
                if(tablesArr.length>0){
                    tableNameList = Arrays.asList(tablesArr);
                }
            }
            /**
             * 遍历生成代码
             */
            for (Table table : tableList) {
                //只需要手动设置的表
                if(tableNameList != null){
                    if(!tableNameList.contains(table.getTableName())){
                        continue;
                    }
                }

                table.setPackageName(classPackage);

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("table", table);
                map.put("author", author);
	            map.put("datetime",	LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

//                TemplateOption.generatorCode(
//                        "httpapi.vm", map, sourcePath + table.getPackagePath() + "/httpapi",
//                        table.getClassName() + "HttpApi.java");
                TemplateOption.generatorCode(
                        "api.vm", map, sourcePath + table.getPackagePath() + "/api",
                        table.getClassName() + "Api.java");
	            TemplateOption.generatorCode(
			            "apiImpl.vm", map, sourcePath + table.getPackagePath() + "/api/impl",
			            table.getClassName() + "ApiImpl.java");
	            TemplateOption.generatorCode(
			            "service.vm", map, sourcePath + table.getPackagePath() + "/service",
			            table.getClassName() + "Service.java");
	            TemplateOption.generatorCode(
			            "serviceImpl.vm", map, sourcePath + table.getPackagePath() + "/service/impl",
			            table.getClassName() + "ServiceImpl.java");
                TemplateOption.generatorCode(
			            "entity.vm", map, sourcePath + table.getPackagePath() + "/entity",
			            table.getClassName() + ".java");
                TemplateOption.generatorCode(
                        "entityQuery.vm", map, sourcePath + table.getPackagePath() + "/query",
                        table.getClassName() + "QueryDTO.java");
	            TemplateOption.generatorCode(
			            "mapper.vm", map, sourcePath + table.getPackagePath() + "/mapper",
			            table.getClassName() + "Mapper.java");
	            TemplateOption.generatorCode(
			            "sqlmapper.vm", map, sourcePath + table.getPackagePath() + "/mapper",
			            table.getClassName() + "Mapper.xml");

                logger.info("生成表：" + table.getTableName() + "成功");
            }

            logger.info("--------------- end time：" + (System.currentTimeMillis() - start) + "ms-----");
            logger.info("代码路径：" + codePath);
            logger.info("包：" + classPackage);
            logger.info("作者：" + author);
            logger.info("生成表：" + tableNames);
            logger.info("------------------------------------");
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }

}
