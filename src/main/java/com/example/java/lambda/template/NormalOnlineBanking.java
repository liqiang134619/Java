package com.example.java.lambda.template;


/**
 * 模板实现
 * @author liq
 * @date 2020/5/31
 */
public class NormalOnlineBanking extends BaseOnlineBanking {


    // 子类中重写方法，实现特别的操作
    @Override
    void makeCustomerHappy(Customer c) {
        System.out.println( c.getName() + " normal happy");
    }
}
