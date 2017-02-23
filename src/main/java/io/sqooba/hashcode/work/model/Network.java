package io.sqooba.hashcode.work.model;

import io.sqooba.hashcode.common.Parsable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by VGazzola on 23/02/17.
 */
public class Network implements Parsable<Network> {
    public int numberOfVideo, numberOfEndpoints, numberOfRequestsDescr, numberOfcacheServers, cacheCapacity;

    public int[] videoPopularity; //id is video id

    public Video[] videos;
    public Network(String filePath) {
        parse(filePath);
    }

    @Override
    public String toString() {
        return "Network{" +
                "numberOfVideo=" + numberOfVideo +
                ", numberOfEndpoints=" + numberOfEndpoints +
                ", numberOfRequestsDescr=" + numberOfRequestsDescr +
                ", numberOfcacheServers=" + numberOfcacheServers +
                ", cacheCapacity=" + cacheCapacity +"\n"+
                ", videoPopularity=" + Arrays.toString(videoPopularity) +"\n"+
                ", videos=" + Arrays.toString(videos) +
                '}';
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
                videoPopularity = new int[numberOfVideo];
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
            //next (number of endpoint) lines, containing [<latency> <nb of cache srv>]
            for (int i = 0; i < numberOfEndpoints; i++) {
                s = br.readLine(); //Todo: use that...
                String[] firstLine = s.split("\\s+");
                int K = Integer.parseInt(firstLine[1]);
                for (int j = 0; j < K; j++) {
                    s = br.readLine(); // // TODO: 23/02/17 do something with that too ...
                }
            }
            // next, requests

            while ((s = br.readLine()) != null){
                String[] firstLine = s.split("\\s+");
                int videoId = Integer.parseInt(firstLine[0]);
                int endpointId = Integer.parseInt(firstLine[1]);
                int requestNb = Integer.parseInt(firstLine[2]);
                videoPopularity[videoId] += requestNb;
            }


            for (int i = 0; i < numberOfVideo; i++) {
                videos[i].requests = videoPopularity[videos[i].id];
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
