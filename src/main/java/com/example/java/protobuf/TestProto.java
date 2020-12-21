package com.example.java.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author Liq
 * @date 2020/7/9
 */
public class TestProto {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(1)
                .setProductName("netty book")
                .setAddress("suzhou")
                .setUserName("liqiang");
        SubscribeReqProto.SubscribeReq subscribeReq = builder.build();

        System.out.println("before encode \n" + subscribeReq.toString());
        // encode
        byte[] bytes = subscribeReq.toByteArray();
        // decode
        SubscribeReqProto.SubscribeReq subscribeReq1 = SubscribeReqProto.SubscribeReq.parseFrom(bytes);
        System.out.println("after decode \n" + subscribeReq1.toString());

    }
}
