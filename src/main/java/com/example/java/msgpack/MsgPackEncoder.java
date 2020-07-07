package com.example.java.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @author Liq
 * @date 2020/7/7
 */
public class MsgPackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {

        // 序列化
        MessagePack messagePack = new MessagePack();
        byte[] write = messagePack.write(o);
        byteBuf.writeBytes(write);

    }
}
