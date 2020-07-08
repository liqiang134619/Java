package com.example.java.websocket;

import com.example.java.nnetty.WebSocketServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.awt.*;
import java.io.IOException;

/**
 * @author Liq
 * @date 2020/7/3
 */
public class WebsocketServer {

    public void run(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new LoggingHandler());
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new HttpObjectAggregator(65535));
                        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                        pipeline.addLast(new WebsocketFramHandler());
                    }
                });


        ChannelFuture channelFuture = b.bind(port).syncUninterruptibly();
        channelFuture.channel().newSucceededFuture().addListener(future -> {
            System.out.println("App Socket Server started on port "+port +".");
        });

        channelFuture.channel().closeFuture().addListener(future -> {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        });

    }


    public static void main(String[] args) throws InterruptedException {

        new WebsocketServer().run(9989);
    }
}
