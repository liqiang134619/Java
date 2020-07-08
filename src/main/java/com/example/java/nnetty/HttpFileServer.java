package com.example.java.nnetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Liq
 * @date 2020/7/3
 */
public class HttpFileServer {

    private static  final String DEFAULT_URL = "D:/netty";

    public void run(final int port, final String url) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("http-decoder",
                                    new HttpRequestDecoder());
                            socketChannel.pipeline().addLast("http-aggregator",
                                    new HttpObjectAggregator(65536));
                            socketChannel.pipeline().addLast("http-encoder",
                                    new HttpResponseEncoder());
                            socketChannel.pipeline().addLast("http-chunked",
                                    new ChunkedWriteHandler());
//                            socketChannel.pipeline().addLast("fileServerHander",
//                                    new HttpFileServerHander(url));
                        }
                    });

            ChannelFuture sync = b.bind().sync();
            sync.channel().closeFuture().sync();


        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }


    public class HttpFileServerHander extends SimpleChannelInboundHandler<FullHttpRequest> {

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {
            if(!request.getDecoderResult().isSuccess()) {

            }

            String requestUri = request.getUri();

        }
    }
    public static void main(String[] args) throws InterruptedException {


        new HttpFileServer().run(3344,DEFAULT_URL);
    }
}
