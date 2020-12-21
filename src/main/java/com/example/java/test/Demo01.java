package com.example.java.test;

import com.example.java.jackson.Student;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Liq
 * @date 2020/8/4
 */
public class Demo01 {

    public static void main(String[] args) {


        String regex ="([1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx])|([1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3})";
//
        String content1 = "李强(4113219960320311X)";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content1);
//        System.out.println(m.find());0.
//        System.out.println(m.group());


        System.out.println("xxxxxxxxxxxxxxxxx");


    }
}
