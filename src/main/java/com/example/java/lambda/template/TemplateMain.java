package com.example.java.lambda.template;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author liq
 * @date 2020/5/31
 */
public class TemplateMain {


    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        list.add("weixuyang");
        list.add("weixuyang1");
        list.add("weixuyang2");
        list.add("weixuyang3");

        list.stream().parallel().forEach(s->{
            System.out.println(s + ": is dog ");
        });


        NormalOnlineBanking normalOnlineBanking = new NormalOnlineBanking();
        normalOnlineBanking.processCustomer();


        new LamdaOnlineBanking().processCustomer(customer -> System.out.println(customer.getName() + " labmda happy"));
    }

}
