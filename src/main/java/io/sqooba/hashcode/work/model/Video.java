package io.sqooba.hashcode.work.model;

/**
 * Created by VGazzola on 23/02/17.
 */
public class Video implements Comparable<Video>{
    public int size;
    public int id;
    public int requests;
    public int latency = 1;

    public Video(int size, int id) {
        this.size = size;
        this.id = id;
    }

    public int compareTo(Video o) {
        return getValue() == o.getValue() ? 0: getValue() > o.getValue() ?1 :-1; //invert (-1, 1) if needed
    }


    //the smaller, the better...
//    private double getValue(){
//        return (double)size;
//    }
    private double getValue(){
        return  ((double)size / requests )* latency;
    }

    @Override
    public String toString() {
        return "Video{" +
                "size=" + size +
                ", id=" + id +
                ", requests=" + requests +
                '}'+"\n";
    }
}
