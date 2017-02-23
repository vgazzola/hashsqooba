package io.sqooba.hashcode.work.model;

import io.sqooba.hashcode.common.Parsable;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by VGazzola on 23/02/17.
 */
public class Network implements Parsable<Network> {
    int numberOfVideo, numberOfEndpoints, numberOfRequestsDescr, numberOfcacheServers, cacheCapacity;
    
    public Network(String filePath) {
        parse(filePath);
    }

    public Network parse(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String s = null;
            if ((s = br.readLine()) != null){
                String[] firstLine = s.split("\\s+");
                numberOfVideo= Integer.parseInt(firstLine[0]);
                numberOfEndpoints= Integer.parseInt(firstLine[1]);
                numberOfRequestsDescr= Integer.parseInt(firstLine[2]);
                numberOfcacheServers= Integer.parseInt(firstLine[3]);
                cacheCapacity= Integer.parseInt(firstLine[4]);
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
}
