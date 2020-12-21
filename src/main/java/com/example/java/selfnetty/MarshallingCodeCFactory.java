package com.example.java.selfnetty;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * @author Liq
 * @date 2020/12/21
 */
public class MarshallingCodeCFactory {


    /**
     * JBoss Marshalling 解码器
     * @return
     */
    public static MarshallingDecoder buildMarshallingDecoder() {

        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory,configuration);
        return new MarshallingDecoder(provider,1024);

    }


    /**
     * JBoss marshalling 编码器
     * @return
     */
    public  static MarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");

        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        MarshallerProvider provider = new DefaultMarshallerProvider(factory,configuration);

        return new MarshallingEncoder(provider);


    }
}
