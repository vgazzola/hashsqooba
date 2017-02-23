package io.sqooba.hashcode.work;

import io.sqooba.hashcode.work.model.Endpoint;
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
    static String separator = " ";
    public static void main(String[] args) throws IOException {

        List<Network> networkList = new ArrayList<Network>();

        //ascending input size
        networkList.add(new Network("src/main/resources/problems/work/me_at_the_zoo.in"));
        networkList.add(new Network("src/main/resources/problems/work/videos_worth_spreading.in"));
        networkList.add(new Network("src/main/resources/problems/work/trending_today.in"));
        networkList.add(new Network("src/main/resources/problems/work/kittens.in"));

        for (Network n: networkList) {
//            System.out.println(n);
            System.out.println(n.inFile.toString());
            String[] videoIds = new String[n.numberOfcacheServers];
            for (int i = 0; i < n.numberOfcacheServers; i++) {
                videoIds[i] = "";
                List<Integer> currentSelectedVids = new ArrayList<Integer>();
                List<Endpoint> currentCache = n.caches[i];
                int size = 0;

                for (int j = 0; j < currentCache.size(); j++) {
                    Endpoint currentEndpoint = currentCache.get(j);

                    List<Video> currentList = n.endpointVids[currentEndpoint.id];
//                    for (int k = 0; k < currentList.size(); k++) {
//                        List<Video> currentList = n.endpointVids[k];
                        for (int l = 0; l < currentList.size(); l++) {
                            Video currentVid = currentList.get(l);

                            if (size + currentVid.size > n.cacheCapacity)
                                continue;

                            size += currentVid.size;

                           if(currentSelectedVids.contains(currentVid.id))
                               continue;

                            videoIds[i] += currentVid.id + separator;
                            currentSelectedVids.add(currentVid.id);
                        }


//                    }

                }


            }

            BufferedWriter writer = null;
            try  {
                writer = new BufferedWriter(new FileWriter(n.getOutFile()));
                writer.write(n.numberOfcacheServers + "\n");

                for (int i2 = 0; i2 < n.numberOfcacheServers; i2++) {
                    writer.write(i2 + separator + videoIds[i2] + "\n");
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }

        }

    }
    private void firstTry(){
        //            Arrays.sort(n.videos);

//            int size = 0;
//            String videoIds = "", separator = " ";
//
//            for (int i = 0; i < n.numberOfVideo; i++) {
//                Video currentVid = n.videos[i];
//
//                if (size + currentVid.size > n.cacheCapacity)
//                    continue;
//
//                size += currentVid.size;
//                videoIds += currentVid.id + separator;
//
//            }
//            System.out.println("size tot = " + size);

    }

}
