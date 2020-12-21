package com.example.java.filtertest;

import java.io.IOException;

/**
 * @author Liq
 * @date 2020-12-2
 */
public interface Filter {


    void doFilter(String s,FilterChain filterChain) throws IOException;
}
