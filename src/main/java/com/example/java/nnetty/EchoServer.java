package com.example.java.nnetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;

/**
 * @author Liq
 * @date 2020/7/3
 */
public class EchoServer {

   public void bind(int port) {
       // 两个线程组
       EventLoopGroup bossGroup = new NioEventLoopGroup();
       EventLoopGroup workGrop = new NioEventLoopGroup();


       // 启动器
       ServerBootstrap b = new ServerBootstrap();
       b.group(bossGroup,workGrop)
               .channel(NioServerSocketChannel.class)
               .option(ChannelOption.SO_BACKLOG,1024)
               .handler(new LoggingHandler(LogLevel.INFO))
               .childHandler(new ChannelInitializer<SocketChannel>() {
                   @Override
                   protected void initChannel(SocketChannel socketChannel) throws Exception {

//                       ByteBuf deLimiter = Unpooled.copiedBuffer("$_".getBytes());
//                       socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,deLimiter));
                       // 定长解码器
//                       socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(20));
                       socketChannel.pipeline().addLast(new StringDecoder());

                       ByteBuf deLimiter = Unpooled.copiedBuffer("$_".getBytes());
                       socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,deLimiter));
//                        定长解码器
//                       socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(20));
//                       socketChannel.pipeline().addLast(new StringDecoder());

                       socketChannel.pipeline().addLast(new EchoServerHandler());

                   }
               });
       // 绑定端口 同步等待成功
       try {
           ChannelFuture sync = b.bind(port).sync();
           // 服务端端口关闭
           sync.channel().closeFuture().sync();
       } catch (InterruptedException e) {
           e.printStackTrace();
       } finally {
            bossGroup.shutdownGracefully();
            workGrop.shutdownGracefully();
       }

   }

   private class  EchoServerHandler extends ChannelInboundHandlerAdapter {
       int counter = 0;


       public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
           InetSocketAddress is  = (InetSocketAddress) remoteAddress;
           System.out.println(is.getAddress() + ":"+ is.getPort());
       }


       @Override
       public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
           String body = (String)msg;
           System.out.println("this is " + ++counter + "times receice client:[" + body + "]");
           body += "$_";
           ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
           ctx.writeAndFlush(echo);

           // 定长解码
           System.out.println("receive client:" + msg);
       }

       @Override
       public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
           ctx.close();
       }


   }

    public static void main(String[] args) {
        new EchoServer().bind(8899);
    }


}
