package io.sqooba.hashcode.work;

import io.sqooba.hashcode.work.model.Network;
import io.sqooba.hashcode.work.model.Video;

import java.util.Arrays;

/**
 * Created by VGazzola on 21/02/17.
 */
public class Main {
    public static void main(String[] args) {
        //ascending input size
//        Network n = new Network("src/main/resources/problems/work/me_at_the_zoo.in");
//        Network n = new Network("src/main/resources/problems/work/videos_worth_spreading.in");
//        Network n = new Network("src/main/resources/problems/work/trending_today.in");
        Network n = new Network("src/main/resources/problems/work/kittens.in");

        Arrays.sort(n.videos);

        int size = 0;
        String videoIds = "", separator = " ";

        for (int i = 0; i < n.numberOfVideo; i++) {
            Video currentVid = n.videos[i];

            if(size + currentVid.size > n.cacheCapacity)
                continue;

            size+=currentVid.size;
            videoIds += currentVid.id+separator;

        }
        System.out.println("size tot = "+ size);

        System.out.println(n.numberOfcacheServers);
        for (int i = 0; i < n.numberOfcacheServers; i++) {
            System.out.println(i+ separator +videoIds);
        }
//        System.out.println(n);
    }

}
