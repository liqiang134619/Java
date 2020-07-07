package com.example.java.msgpack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

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
                       ChannelPipeline pipeline = socketChannel.pipeline();
                       pipeline.addLast("framDecoder",new LengthFieldBasedFrameDecoder(65535,0,2,0,2));

                       pipeline.addLast("msg-decoder",new MsgPackDecoder());
                       pipeline.addLast("framEncode",new LengthFieldPrepender(2));
                       pipeline.addLast("msg-encoder",new MsgPackEncoder());
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

   private class  EchoServerHandler extends ChannelHandlerAdapter{


       @Override
       public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

           System.out.println("server receive :" + msg);
           ctx.writeAndFlush(msg);
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
