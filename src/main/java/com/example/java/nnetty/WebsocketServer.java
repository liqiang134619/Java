package com.example.java.nnetty;

import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author Liq
 * @date 2020/7/3
 */
public class WebsocketServer {

    public void run(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("http-codec",
                                    new HttpServerCodec());
                            pipeline.addLast("aggregator",
                                    new HttpObjectAggregator(65536));
                            pipeline.addLast("http-chunked",
                                    new ChunkedWriteHandler());
                            pipeline.addLast("hander",
                                    new WebSocketServerHandler());
                        }
                    });


            Channel ch = b.bind(port).sync().channel();
            System.out.println("Web socket server started at port " + port
                    + '.');
            System.out
                    .println("Open your browser and navigate to http://localhost:"
                            + port + '/');

            ch.closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws InterruptedException {

        new WebsocketServer().run(9989);
    }
}
