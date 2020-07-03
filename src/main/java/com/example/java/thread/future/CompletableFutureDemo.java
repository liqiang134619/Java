package com.example.java.thread.future;

import java.util.concurrent.*;

/**
 * @author Liq
 * @date 2020/6/9
 */
public class CompletableFutureDemo {


    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

////
//        CompletableFuture<String> future = CompletableFuture.supplyAsync((() -> {
//            try {
//                Thread.sleep(4000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "hello world";
//        }));
//
//        String s = future.get(5000L,TimeUnit.MILLISECONDS);
//
//        System.out.println(s);

        System.out.println("hello ");
        System.out.println(test2());
        System.out.println("hello 2");


        System.out.println("hello 4");


    }

    private static String test2() {
        test();
        return "x";
    }

    private static void test() {
        CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("xxxxxxxxxxxxx");

        });
    }
}
