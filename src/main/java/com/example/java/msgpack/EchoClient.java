package com.example.java.msgpack;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author Liq
 * @date 2020/7/3
 */
public class EchoClient {

    public void connect(int port,String host,int sendNumber) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("framDecoder",new LengthFieldBasedFrameDecoder(65535,0,2,0,2));

                        pipeline.addLast("msg-decoder",new MsgPackDecoder());
                        pipeline.addLast("framEncode",new LengthFieldPrepender(2));
                        pipeline.addLast("msg-encoder",new MsgPackEncoder());
                        socketChannel.pipeline().addLast(new EchoClientHander(sendNumber));
                    }
                });

        try {

            // 异步连接
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }


    public class EchoClientHander extends ChannelHandlerAdapter{
        private final int counter;
        public EchoClientHander(int counter) {
            this.counter = counter;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            UserInfo[] userInfos = UserInfos();
            for (UserInfo userInfo : userInfos) {
                ctx.write(userInfo);
            }
            ctx.flush();
        }

        private UserInfo[] UserInfos() {
            UserInfo[] userInfos = new UserInfo[counter];
            UserInfo  userInfo = null;
            for (int i = 0; i < counter; i++) {
                userInfo = new UserInfo();
                userInfo.setAge(i);
                userInfo.setName("ABCDEFG-->" + i);
                userInfos[i] = userInfo;
            }
            return userInfos;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(" client receive mes" + msg);
            ctx.write(msg);
        }



        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
    }

    public static void main(String[] args) {
        new EchoClient().connect(8899,"127.0.0.1",10);

    }
}
