package com.example.java.json;

import lombok.Data;

/**
 * @author Liq
 * @date 2020/7/13
 */
@Data
public class Store {

    private String name;

    private Fruit fruit;

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public Fruit getFruit() {

        return fruit;

    }

    public void setFruit(Fruit fruit) {

        this.fruit = fruit;

    }
}
