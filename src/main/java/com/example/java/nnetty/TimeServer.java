package com.example.java.nnetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author Liq
 * @date 2020/7/2
 */

public class TimeServer {

    public void bind(int port) {

        // 配置服务端的nio线程组
        EventLoopGroup bossGroup  = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        // netty启动辅助器
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                // 连接时创建通道
                .childHandler(new ChildChannelHandler());



        try {
            // 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture sync = bootstrap.bind(port).sync();
            // 对关闭通道进行监听
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            // 处理逻辑
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    /**
     * 继承channelhandleradapter 用于处理网络时间的读写操作
     */
    public class TimeServerHandler extends  ChannelInboundHandlerAdapter  {


        // tcp粘包拆包
        private int counter;

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
           ctx.flush();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            // 对象转换
            ByteBuf buf = (ByteBuf) msg;

            // readableBytes获取缓冲区的字节数
            byte[] req = new byte[buf.readableBytes()];
            // 读入字节数组
            buf.readBytes(req);


            String body = new String(req, StandardCharsets.UTF_8).substring(0,req.length - System.getProperty("line.separator").length());
            System.out.println("server receive:" + body + "counter is" + ++counter);

            String currentTime = "QUERY TIME ORDER".equals(body) ? new Date(System.currentTimeMillis()).toString() : "bad order";
            currentTime = currentTime + System.getProperty("line.separator");
            byte[] sBytes = currentTime.getBytes();
            ByteBuf resp = Unpooled.copiedBuffer(sBytes);
            ctx.write(resp);
        }
    }

    public static void main(String[] args) {
        new TimeServer().bind(9999);
    }

}
