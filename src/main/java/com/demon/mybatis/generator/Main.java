package com.demon.mybatis.generator;

import com.demon.mybatis.generator.mybatis.MybatisCodeGenerator;

/**
 * Created by Demon-Coffee on 2017/7/22 0022.
 */
public class Main {

    public static void main(String[] args) {
        // 生成 mybatis
        MybatisCodeGenerator mybatisCode = new MybatisCodeGenerator();
        mybatisCode.generator(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://rm-wz93c8s4anq404oc9.mysql.rds.aliyuncs.com:3306/xubei?useUnicode=true&amp;characterEncoding=UTF-8&amp;allowMultiQueries=true",
//		        "jdbc:mysql://localhost:3306/jeesite?useUnicode=true&amp;characterEncoding=UTF-8&amp;allowMultiQueries=true",
//                "root",
		        "orders_api",
//                "123456",
		        "@GMchkkQsS8v",
                "com.xubei.channel",
                "songx",
                "t_video_recharge_log",
                "G:/xubei/code"
        );

//        // 生成 jdbc
//        // 设置模本路径
//        TemplateOption.templatePath = "template_jdbc";
//        JdbcCodeGenerator jdbcCode = new JdbcCodeGenerator();
//        jdbcCode.generator(
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://127.0.0.1:3306/demon?characterEncoding=utf-8&useUnicode=true&zeroDateTimeBehavior=convertToNull",
//                "demon",
//                "P@ssw0rd",
//                "org.demon",
//                "Demon-Coffee",
//                "",
//                "f:/code/generator"
//                );

    }
}
