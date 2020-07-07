package com.example.java.msgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liq
 * @date 2020/7/7
 */
public class Demo01 {

    public static void main(String[] args) throws IOException {

        List<String> src = new ArrayList<>();

        src.add("a");
        src.add("b");
        src.add("c");
        src.add("d");

        MessagePack messagePack = new MessagePack();
        byte[] write = messagePack.write(src);


        List<String> read = messagePack.read(write, Templates.tList(Templates.TString));
        read.forEach(System.out::println);
    }
}
