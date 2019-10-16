package com.demon.mybatis.generator.jdbc;

import com.demon.mybatis.generator.TemplateOption;
import com.demon.mybatis.generator.common.DatabaseOption;
import com.demon.mybatis.generator.model.Database;
import com.demon.mybatis.generator.model.Table;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成类
 */
public class JdbcCodeGenerator {

    private Logger logger = Logger.getLogger(JdbcCodeGenerator.class);

    //是否去掉模块前面的编号
    public static String prefix = "";

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
            Database databaseBean = new DatabaseOption(classDriver, url, username, password, schema).getDbInfo();

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
            // 遍历生成代码
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

                TemplateOption.generatorCode(
                        "api.vm", map, sourcePath + table.getPackagePath() + "/api",
                        "I" + table.getClassName() + "Api.java");
	            TemplateOption.generatorCode(
			            "apiImpl.vm", map, sourcePath + table.getPackagePath() + "/api/impl",
			            table.getClassName() + "Api.java");
//	            TemplateOption.generatorCode(
//			            "service.vm", map, sourcePath + table.getPackagePath() + "/dao",
//			            table.getClassName() + "Dao.java");
	            TemplateOption.generatorCode(
			            "serviceImpl.vm", map, sourcePath + table.getPackagePath() + "/dao/impl",
			            table.getClassName() + "DaoImpl.java");
	            TemplateOption.generatorCode(
			            "entity.vm", map, sourcePath + table.getPackagePath() + "/entity",
			            table.getClassName() + ".java");
//	            TemplateOption.generatorCode(
//			            "httpapi.vm", map, sourcePath + table.getPackagePath() + "/httpapi",
//			            table.getClassName() + "HttpApi.java");

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
