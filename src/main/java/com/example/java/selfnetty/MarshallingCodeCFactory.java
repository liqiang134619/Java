package com.example.java.selfnetty;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * @author Liq
 * @date 2020/12/21
 */
public class MarshallingCodeCFactory {


   /* *//**
     * JBoss Marshalling 解码器
     * @return
     *//*
    public static MarshallingDecoder buildMarshallingDecoder() {

        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory,configuration);
        return new MarshallingDecoder(provider,1024);

    }


    *//**
     * JBoss marshalling 编码器
     * @return
     *//*
    public  static MarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");

        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        MarshallerProvider provider = new DefaultMarshallerProvider(factory,configuration);

        return new MarshallingEncoder(provider);


    }*/


    /**
     * 创建Jboss Marshaller
     *
     * @return
     * @throws IOException
     */
    protected static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return marshallerFactory
                .createMarshaller(configuration);
    }


    /**
     * 创建Jboss Unmarshaller
     *
     * @return
     * @throws IOException
     */
    protected static Unmarshaller buildUnMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return marshallerFactory
                .createUnmarshaller(configuration);
    }


}
