package io.sqooba.hashcode.work;

import io.sqooba.hashcode.work.model.Network;
import io.sqooba.hashcode.work.model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by VGazzola on 21/02/17.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        List<Network> networkList = new ArrayList<Network>();

        //ascending input size
        networkList.add(new Network("src/main/resources/problems/work/me_at_the_zoo.in"));
        networkList.add(new Network("src/main/resources/problems/work/videos_worth_spreading.in"));
        networkList.add(new Network("src/main/resources/problems/work/trending_today.in"));
        networkList.add(new Network("src/main/resources/problems/work/kittens.in"));

        for (Network n: networkList) {
            Arrays.sort(n.videos);

            int size = 0;
            String videoIds = "", separator = " ";

            for (int i = 0; i < n.numberOfVideo; i++) {
                Video currentVid = n.videos[i];

                if (size + currentVid.size > n.cacheCapacity)
                    continue;

                size += currentVid.size;
                videoIds += currentVid.id + separator;

            }
            System.out.println("size tot = " + size);

            System.out.println(n.numberOfcacheServers);
            BufferedWriter writer = null;
            try  {
                writer = new BufferedWriter(new FileWriter(n.getOutFile()));
                for (int i = 0; i < n.numberOfcacheServers; i++) {
                    writer.write(i + separator + videoIds + "\n");
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }

        }
    }

}
