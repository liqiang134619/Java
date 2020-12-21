package com.example.java.selfnetty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 解码
 * @author liq
 * @date 2020/12/17
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {



    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);

    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf decode = (ByteBuf)super.decode(ctx, in);

        if(decode == null) {
            return null;
        }

        NettyMessage nettyMessage = new NettyMessage();


        return nettyMessage;
    }
}
