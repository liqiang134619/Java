package com.example.java.File;

import com.example.java.sockets.SocketTest;

import java.io.*;

/**
 * @author Liq
 * @date 2020/7/14
 */
public class FileTest {


    public static void main(String[] args) throws IOException {


        File file = new File("D:/MonItems");

        File[] files = file.listFiles();
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/火龙神.txt")));
        for (File file1 : files) {
            if (!file1.isDirectory()) {
                bufferedReader = new BufferedReader
                        (new InputStreamReader(new FileInputStream(file1), "GB2312"));

                String line;
                while ((line = bufferedReader.readLine()) != null) {

                    if(!"".equals(line)) {

                        line=line.replaceAll("\\s+"," ");
                        line = file1.getName() + ":" + line;
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                    }

                }

            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();





    }
}
