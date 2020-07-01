package com.example.java.nnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Liq
 * @date 2020/7/1
 */
public class BlockClient {

    public static void main(String[] args) throws IOException {

        // 1.获取socket通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));

        // 2.获取一个图片通道
        FileChannel fileChannel = FileChannel.open(Paths.get("D:\\123456.jpg"), StandardOpenOption.READ);

        // 3. 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 4.读取本地文件(图片)，发送到服务器
        while (fileChannel.read(buffer) != -1) {

            // 在读之前都要切换成读模式
            buffer.flip();

            socketChannel.write(buffer);

            // 读完切换成写模式，能让管道继续读取文件的数据
            buffer.clear();
        }

        // 需要显示告诉服务器写完了
        socketChannel.shutdownOutput();

        // 接收服务端的消息
        int len = 0;
        while ((len = socketChannel.read(buffer)) != -1) {
            // 切换到读模式
            buffer.flip();
            System.out.println(new String(buffer.array(),0,len));
            // 切换写模式
            buffer.clear();
        }




        // 5. 关闭流
        fileChannel.close();
        socketChannel.close();


    }
}
