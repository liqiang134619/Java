package com.example.java.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Liq
 * @date 2020/7/9
 */
public class SubscribeReqHanler extends SimpleChannelInboundHandler<SubscribeReqProto.SubscribeReq> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SubscribeReqProto.SubscribeReq msg) throws Exception {
        System.out.println("receive from client:" + msg.toString());
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        ctx.channel().closeFuture().addListener(future -> {
//            System.out.println("连接关闭");
//        });

        ctx.close();
    }
}
