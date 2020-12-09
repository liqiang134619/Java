package com.example.java.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author Liq
 * @date 2020/7/13
 */
public class TestMain {

    public static void main(String[] args) throws IOException {

        Store store = new Store();

        store.setName("Hollis");

        Apple apple = new Apple();

        apple.setPrice(new BigDecimal(0.5));

        store.setFruit(apple);

        String jsonString = JSON.toJSONString(store);

        System.out.println("toJSONString : " + jsonString);

        Store store1 = JSON.parseObject(jsonString, Store.class);
        System.out.println(store1);
        System.out.println("===============");
        System.out.println(store1.getFruit());
        System.out.println("**************jackson*********************");
        String writeValueAsString = JackSonUt.getInstance().writeValueAsString(store);
        System.out.println("jackSon to JsonString：" + writeValueAsString);
        Store store2 = JackSonUt.getInstance().readValue(writeValueAsString, store.getClass());
        System.out.println(store2);

    }
}
