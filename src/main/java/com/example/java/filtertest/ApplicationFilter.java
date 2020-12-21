package com.example.java.filtertest;

import java.io.IOException;

/**
 * @author Liq
 * @date 2020-12-2
 */
public class ApplicationFilter implements FilterChain {
    @Override
    public void doFilter(String s) throws IOException {
        System.out.println(s);
    }
}
