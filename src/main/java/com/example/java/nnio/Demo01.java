package com.example.java.nnio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * @author Liq
 * @date 2020/7/1
 */
public class Demo01 {

    public static void main(String[] args) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("java".getBytes());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println(buffer.position());
        System.out.println(buffer.mark());

        System.out.println("=======================");
        buffer.flip();
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println(buffer.position());
        System.out.println(buffer.mark());

        byte[] bytes = new byte[buffer.limit()];

        buffer.get(bytes);
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println("=====================================");
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println(buffer.position());
        System.out.println(buffer.mark());

        System.out.println("========================");
        buffer.clear();
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println(buffer.position());
        System.out.println(buffer.mark());
    }
}
