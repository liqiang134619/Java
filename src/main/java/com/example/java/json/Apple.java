package com.example.java.json;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Liq
 * @date 2020/7/13
 */
@Data
public class Apple implements Fruit {

    private BigDecimal price;

    //省略 setter/getter、toString等
}
