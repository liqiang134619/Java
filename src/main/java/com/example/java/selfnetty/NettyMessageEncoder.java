package com.example.java.selfnetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 编码器
 * @author liq
 * @date 2020/12/17
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          NettyMessage msg, List<Object> list) throws Exception {

        if(msg == null || msg.getHeader() == null) {
            throw  new Exception("mes is null");
        }

        ByteBuf sendBuf = Unpooled.buffer();

        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionId());
        sendBuf.writeByte(msg.getHeader().getPriority());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeInt(msg.getHeader().getAttachment().size());

        if(msg.getBody() != null) {
            //
        } else  {
            sendBuf.writeInt(0);
            sendBuf.setInt(4,sendBuf.readableBytes());
        }

    }
}