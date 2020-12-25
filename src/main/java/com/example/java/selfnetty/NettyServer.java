package com.example.java.selfnetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import sun.plugin2.gluegen.runtime.CPU;

import java.nio.channels.Channel;

/**
 * @author Liq
 * @date 2020/12/25
 */
public class NettyServer {


    private void bind() throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast("readTimeoutHandler",
                                    new ReadTimeoutHandler(50));
                            ch.pipeline().addLast(new LoginAuthRespHandler());
                            ch.pipeline().addLast(new HeartBeatRespHandler());
                        }
                    });


            b.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();

            System.out.println("Web socket server started at port " + NettyConstant.PORT
                    + '.');
            System.out
                    .println("Open your browser and navigate to http://localhost:"
                            + NettyConstant.PORT + '/');
        } finally {

            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();

        }

    }


    public static void main(String[] args) throws InterruptedException {
        new NettyServer().bind();
    }

}
