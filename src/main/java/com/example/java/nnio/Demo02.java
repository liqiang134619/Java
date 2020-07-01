package com.example.java.nnio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * filechannel
 * @author Liq
 * @date 2020/7/1
 */
public class Demo02 {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("D:\\20180609073400448.jpg");
        FileOutputStream fos = new FileOutputStream("D:\\123456.jpg");

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        while(fisChannel.read(buffer) != -1) {
            // 反正buffer为写操作
            buffer.flip();


            // 写入管道中
            fosChannel.write(buffer);

            // 清空缓冲区
            buffer.clear();
        }
    }
}
