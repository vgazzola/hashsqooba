package io.sqooba.hashcode.work.model;

import io.sqooba.hashcode.common.Parsable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by VGazzola on 23/02/17.
 */
public class Network implements Parsable<Network> {
    int numberOfVideo, numberOfEndpoints, numberOfRequestsDescr, numberOfcacheServers, cacheCapacity;

    Video[] videos;
    public Network(String filePath) {
        parse(filePath);
    }

    public Network parse(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String s = null;
            //first line, general info
            if ((s = br.readLine()) != null){
                String[] firstLine = s.split("\\s+");
                numberOfVideo= Integer.parseInt(firstLine[0]);
                videos = new Video[numberOfVideo];
                numberOfEndpoints= Integer.parseInt(firstLine[1]);
                numberOfRequestsDescr= Integer.parseInt(firstLine[2]);
                numberOfcacheServers= Integer.parseInt(firstLine[3]);
                cacheCapacity= Integer.parseInt(firstLine[4]);
            }else{
                return null;
            }
            s=null;
            //second line, videos
            if ((s = br.readLine()) != null){
                String[] firstLine = s.split("\\s+");
                for (int i = 0; i < numberOfVideo; i++) {
                    videos[i] = new Video(Integer.parseInt(firstLine[i]),i);
                }
            }else{
                return null;
            }

            Arrays.sort(videos);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
