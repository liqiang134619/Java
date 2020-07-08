package com.example.java.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @author Liq
 * @date 2020/7/7
 */
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
       // 可读数据大小啊
        int i = byteBuf.readableBytes();
        // 新建数组
        final byte[] array;
        array = new byte[i];
        System.out.println(i);
        // 写入
        byteBuf.getBytes(byteBuf.readerIndex(),array,0,i);
        MessagePack messagePack = new MessagePack();
        list.add(messagePack.read(array));
    }
}
