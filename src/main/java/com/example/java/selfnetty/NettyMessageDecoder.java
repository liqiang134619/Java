package com.example.java.selfnetty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.IOException;

/**
 * 解码
 * @author liq
 * @date 2020/12/17
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {



    private MarshallingDecoder marshallingDecoder;


    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        this.marshallingDecoder = new MarshallingDecoder();

    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf decode = (ByteBuf)super.decode(ctx, in);

        if(decode == null) {
            return null;
        }

        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionId(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());

        if(in.readableBytes() > 4) {
            nettyMessage.setBody(marshallingDecoder.decode(in));
        }
        nettyMessage.setHeader(header);
        return nettyMessage;
    }
}
