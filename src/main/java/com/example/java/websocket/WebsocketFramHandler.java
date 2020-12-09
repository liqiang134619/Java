package com.example.java.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Locale;

/**
 * @author Liq
 * @date 2020/7/8
 */
public class WebsocketFramHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 所有的channel组
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);




    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {


            // 返回应答消息
            String request = ((TextWebSocketFrame) frame).text();
            System.out.println(ctx.channel().id().asShortText());
            ctx.channel().writeAndFlush(
                    new TextWebSocketFrame(request
                            + " , 欢迎使用Netty WebSocket服务，现在时刻："
                            + new java.util.Date().toString()));

    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        channels.writeAndFlush(new TextWebSocketFrame("sever:" + ctx.channel().id().asShortText() + "coming"));
        channels.add(ctx.channel());

    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(
                new TextWebSocketFrame(990
                        + " , 欢迎使用Netty WebSocket服务，现在时刻："
                        + new java.util.Date().toString()));

    }
}
