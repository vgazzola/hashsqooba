package io.sqooba.hashcode.work.model;

import io.sqooba.hashcode.common.Parsable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by VGazzola on 23/02/17.
 */
public class Network implements Parsable<Network> {
    public int numberOfVideo, numberOfEndpoints, numberOfRequestsDescr, numberOfcacheServers, cacheCapacity;

    public int[] videoPopularity; //id is video id

    public Video[] videos;

    public File inFile;

    public List<Video>[] endpointVids;
    public List<Endpoint>[] caches;
    public Network(String filePath) {
        inFile = new File(filePath);
        parse(filePath);
    }

    @Override
    public String toString() {
        return "Network{" +
                "numberOfVideo=" + numberOfVideo + "\n"+
                ", numberOfEndpoints=" + numberOfEndpoints + "\n"+
                ", numberOfRequestsDescr=" + numberOfRequestsDescr + "\n"+
                ", numberOfcacheServers=" + numberOfcacheServers + "\n"+
                ", cacheCapacity=" + cacheCapacity + "\n"+
                ", videoPopularity=" + Arrays.toString(videoPopularity) + "\n"+
                ", videos=" + Arrays.toString(videos) + "\n"+
                ", inFile=" + inFile + "\n"+
                ", endpointVids=" + Arrays.toString(endpointVids) + "\n"+
                ", caches=" + Arrays.toString(caches) + "\n"+
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
                endpointVids = new List[numberOfEndpoints];
                for (int i = 0; i < numberOfEndpoints; i++) {
                    endpointVids[i] = new ArrayList<Video>();
                }
                numberOfRequestsDescr= Integer.parseInt(firstLine[2]);
                numberOfcacheServers= Integer.parseInt(firstLine[3]);
                caches = new List[numberOfcacheServers];
                for (int i = 0; i < numberOfcacheServers; i++) {
                    caches[i] = new ArrayList<Endpoint>();
                }

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
                int numberOfCacheServ = Integer.parseInt(firstLine[1]);
                for (int j = 0; j < numberOfCacheServ; j++) {
                    s = br.readLine(); // // TODO: 23/02/17 do something with that too ...
                    String[] secondLine = s.split("\\s+");
                    int cacheId = Integer.parseInt(secondLine[0]);
                    int latency = Integer.parseInt(secondLine[1]);

                    caches[cacheId].add(new Endpoint(latency, i));

                }

            }
            // next, requests


            while ((s = br.readLine()) != null){
                String[] firstLine = s.split("\\s+");
                int videoId = Integer.parseInt(firstLine[0]);
                int endpointId = Integer.parseInt(firstLine[1]);
                int requestNb = Integer.parseInt(firstLine[2]);
                videoPopularity[videoId] += requestNb;

                endpointVids[endpointId].add(videos[videoId]);
            }
            for (int i = 0; i < numberOfEndpoints; i++) {
                Collections.sort(endpointVids[i]);
            }

            for (int i = 0; i < numberOfVideo; i++) {
                videos[i].requests = videoPopularity[videos[i].id];
            }

            for (int i = 0; i < numberOfcacheServers; i++) {
                Collections.sort(caches[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getOutFile() {
        return new File("src/main/resources/solutions", inFile.getName().replace(".in", "") + ".out");
    }
}
