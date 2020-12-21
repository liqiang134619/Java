package com.example.java.zip;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ZipUtil;

import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

/**
 * @author Liq
 * @date 2020-10-10
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {
//
//        long l = System.currentTimeMillis();
//        File file1 = new File("D:/111.jpg");
//        File file2 = new File("D:/123.jpg");
//        File zip = ZipUtil.zip(FileUtil.file("d:/cc.zip"), false, file1, file2);
//        System.out.println("xx");
//        System.out.println(System.currentTimeMillis() - l);

//
//        ZipUtil.zip()
//        InputStream is = new FileInputStream(file1);
//        byte[] gzip = ZipUtil.gzip(is);
//
////        FileOutputStream fileOutputStream = new FileOutputStream();
//        File file = FileUtil.writeBytes(gzip, FileUtil.file("d:/xx.zip"));
//        System.out.println(file.getName());
//        IoUtil.close(is);
//
//
//        ZipUtil.zip("D:/12333","D:/c.zip",false);
//
//        BufferedOutputStream outputStream = FileUtil.getOutputStream();

//
//
//        long times = 1566316800000L;
//
//        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(times / 1000, 0, ZoneOffset.ofHours(8));
//        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        String substring = format.substring(0, 10);
//        System.out.println(substring);

        List<String> list  = new ArrayList<>();
        list.add("xx");
        list.add("2");
        list.add("x3x");
        list.add("4");
        list.add("5");
        list.add("6");

        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if(i%2 != 0) {
                list.set(index++,list.get(i));
            }
        }
        if(list.size() > index) {
            list.subList(index,list.size()).clear();
        }

        list.forEach(System.out::println);
    }
}
