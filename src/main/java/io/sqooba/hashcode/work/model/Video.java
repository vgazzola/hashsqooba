package io.sqooba.hashcode.work.model;

/**
 * Created by VGazzola on 23/02/17.
 */
public class Video implements Comparable<Video>{
    public int size;
    public int id;

    public Video(int size, int id) {
        this.size = size;
        this.id = id;
    }

    public int compareTo(Video o) {
        return size == o.size ? 0: size > o.size ?-1 :1; //invert (-1, 1) if needed
    }

    @Override
    public String toString() {
        return "Video{" +
                "size=" + size +
                ", id=" + id +
                '}';
    }
}
