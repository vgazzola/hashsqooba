package io.sqooba.hashcode.practice;

import io.sqooba.hashcode.practice.model.Pizza;

/**
 * Created by VGazzola on 21/02/17.
 */
public class Main {
    public static void main(String[] args) {
        Pizza p = new Pizza("src/main/resources/problems/practice/input_set_big");
        System.out.println(p);
    }
}
