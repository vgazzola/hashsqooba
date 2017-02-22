package io.sqooba.hashcode.practice.model;

import io.sqooba.hashcode.common.Parsable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Created by VGazzola on 22/02/17.
 */
public class Pizza implements Parsable<Pizza>{
    private int rows;
    private int columns;
    private int minIngredients;
    private int maxCasesPerSlice;

    private char[][] pizza;

    public Pizza(String filePath) {
        parse(filePath);
    }

    public Pizza parse(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String s = null;
            if ((s = br.readLine()) != null){
                String[] firstLine = s.split("\\s+");
                rows= Integer.parseInt(firstLine[0]);
                columns= Integer.parseInt(firstLine[1]);
                minIngredients= Integer.parseInt(firstLine[2]);
                maxCasesPerSlice= Integer.parseInt(firstLine[3]);
            }else{
                return null;
            }
            pizza = new char[rows][columns];
            int i = 0;
            while ((s = br.readLine()) != null){


                for (int j =0;j<columns;j++){
                    pizza[i][j] = s.charAt(j);
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "rows=" + rows +
                ", columns=" + columns +
                ", minIngredients=" + minIngredients +
                ", maxCasesPerSlice=" + maxCasesPerSlice +
                ", pizza=" + printPizza() +
                '}';
    }
    private String printPizza(){
        String s="";
        for (int i = 0; i < rows; i++){
            s+=Arrays.toString(pizza[i]);
            if (i != rows-1)
                s+= "\n";
        }
        return s;
    }
}
