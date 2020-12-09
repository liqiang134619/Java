package com.example.java.filtertest;

import java.io.IOException;

/**
 * @author Liq
 * @date 2020-12-2
 */
public interface FilterChain {

    void doFilter(String s) throws IOException;

}
