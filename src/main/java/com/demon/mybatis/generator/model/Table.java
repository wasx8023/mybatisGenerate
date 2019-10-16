package com.demon.mybatis.generator.model;

import java.util.ArrayList;
import java.util.List;

import com.demon.mybatis.generator.mybatis.MybatisCodeGenerator;
import com.demon.mybatis.generator.StringUtils;

public class Table {

    // 数据库
    private String tableSchem;

    // 表名
    private String tableName;
    // 表注释
    private String tableComment;

    // 列
    private List<Column> columnList = new ArrayList<Column>();

    // 包名
    private String packageName;
    // 包路径
    private String packagePath;
    // 类名
    private String className;
    // 类名首字母小写
    private String classNameFirstLower;
    // 主键字段名
    private String primaryFieldName;

    /**
     * 获得第一个主键
     */
    public Column getFirstPrimaryKey() {

        for (Column tempCul : this.getColumnList()) {
            if (tempCul.isPrimary()) {
                return tempCul;
            }
        }
        return null;

    }

    /**
     * 获得类小写头名字
     */
    public String getLowerDomainClassName() {
        return StringUtils.getClassLower(this.getClassName());
    }

    /************************* get set *************************/
    public String getPackagePath() {
        this.packagePath = this.getPackageName().replace(".", "/");
        return packagePath;
    }
    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    /**
     * 获取类名
     * 
     * @return
     */
    public String getClassName() {
        if (MybatisCodeGenerator.prefix.equals("")) {
            // 直接输出
            this.className = StringUtils.getClassUpper(StringUtils
                    .getDomainColumnName(this.tableName));
        } else {
            // 去除前缀
            if (this.tableName.startsWith(MybatisCodeGenerator.prefix)) {
                int pos = this.tableName.indexOf(MybatisCodeGenerator.prefix) + MybatisCodeGenerator.prefix.length();
                this.className = StringUtils.getClassUpper(StringUtils.getDomainColumnName(this.tableName.substring(pos, this.tableName.length())));
            } else if (this.tableName.contains("_")) {
                this.className = StringUtils.getClassUpper(StringUtils
                        .getDomainColumnName(this.tableName.substring( this.tableName.indexOf("_") + 1, this.tableName.length())));
            }
        }

        return this.className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getTableComment() {
        return tableComment;
    }
    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public List<Column> getColumnList() {
        return columnList;
    }
    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }
    public String getTableSchem() {
        return tableSchem;
    }
    public void setTableSchem(String tableSchem) {
        this.tableSchem = tableSchem;
    }
    public String getClassNameFirstLower() {
        this.classNameFirstLower = org.apache.commons.lang.StringUtils.uncapitalize(this.getClassName());
        return classNameFirstLower;
    }
    public void setClassNameFirstLower(String classNameFirstLower) {
        this.classNameFirstLower = classNameFirstLower;
    }
}
