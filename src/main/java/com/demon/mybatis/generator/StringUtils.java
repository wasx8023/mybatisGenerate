package com.demon.mybatis.generator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Types;
import java.util.Objects;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 字符串处理工具类
 */
public class StringUtils {

    /**
     * 获得类首字母小写
     */
    public static String getClassLower(String className) {
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    /**
     * 获得类首字母大写
     */
    public static String getClassUpper(String className) {
        return className.substring(0, 1).toUpperCase() + className.substring(1);
    }

    /**
     * 获得set 方法
     */
    public static String getSetMethod(String field) {
        String methodStr = "";

        methodStr = "set";

        if ((field == null) || (field == "")) {
            return "";
        } else {
            field = methodStr + field.substring(0, 1).toUpperCase()
                    + field.substring(1);

            return field;
        }
    }

    /**
     * 获得get 方法
     */
    public static String getGetMethod(String field) {
        String methodStr;

        methodStr = "get";

        if ((field == null) || (Objects.equals(field, ""))) {
            return "";
        } else {
            field = methodStr + field.substring(0, 1).toUpperCase() + field.substring(1);

            return field;
        }
    }

    /**
     * 去掉下划线，将下划线后首字母大写
     */
    public static String getDomainColumnName(String databaseColumn) {
        if ((databaseColumn == null) || (Objects.equals(databaseColumn, ""))) {
            return "";
        } else {
            int _postion = -1;
            while (databaseColumn.indexOf("_") > 0) {
                _postion = databaseColumn.indexOf("_");
                if (_postion < databaseColumn.length() - 1) {
                    databaseColumn = databaseColumn.substring(0, _postion)
                            + databaseColumn.substring(_postion + 1,
                            _postion + 2).toUpperCase()
                            + databaseColumn.substring(_postion + 2,
                            databaseColumn.length());
                } else {
                    databaseColumn = databaseColumn.replace("_", "");
                }
            }
        }

        return databaseColumn;
    }

    public static String getFieldType(int databaseType) {
        String colType = "";

        switch (databaseType) {
            case Types.DECIMAL:
            case Types.REAL:
                colType = "BigDecimal";
                break;
            case Types.BIGINT:
                colType = "Long";
                break;
            case Types.INTEGER:
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.BIT:
                colType = "Integer";
                break;
            case Types.VARCHAR:
            case Types.CHAR:
            case Types.LONGVARBINARY:
            case Types.LONGVARCHAR:
                colType = "String";
                break;
            case Types.DATE:
            case Types.TIMESTAMP:
                colType="Date";
                break;
            default:
                System.out.println("找不到数据类型："+databaseType);
                break;
        }

        return colType;
    }

    public static String getColumnType(int databaseType) {
        switch (databaseType) {
            case Types.DECIMAL:
                return "DECIMAL";
            case Types.REAL:
                return "REAL";
            case Types.INTEGER:
                return "INTEGER";
            case Types.BIGINT:
                return "BIGINT";
            case Types.TINYINT:
                return "TINYINT";
            case Types.SMALLINT:
                return "SMALLINT";
            case Types.VARCHAR:
                return "VARCHAR";
            case Types.CHAR:
                return "CHAR";
            case Types.LONGVARBINARY:
                return "LONGVARBINARY";
            case Types.LONGVARCHAR:
                return "LONGVARCHAR";
            case Types.DATE:
                return "DATE";
            case Types.TIMESTAMP:
                return "TIMESTAMP";
            case Types.BIT:
                return "INTEGER";
            default:
                System.out.println("找不到数据类型："+databaseType);
                return "VARCHAR";
        }
    }

    /**
     * 从属性文件获取配置
     */
    public synchronized static String getPropertyFromFile(String filename, String key) {
        String paodingAnalysisPath = System.getProperty("user.dir") + "\\"+filename;

        InputStream in1;
        ResourceBundle rb=null;
        try {
            in1 = new BufferedInputStream(new FileInputStream(paodingAnalysisPath));
            rb = new PropertyResourceBundle(in1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert rb != null;
        return rb.getString(key).trim();
    }
}
