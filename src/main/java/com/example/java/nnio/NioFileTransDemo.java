package com.example.java.nnio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author liq
 * @date 2020/7/1
 */
public class NioFileTransDemo {

    public static void main(String[] args) throws IOException {

        RandomAccessFile fromFile = new RandomAccessFile("test.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count  = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);


    }

    private static void fileTrans() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }
}
