package io.sqooba.hashcode.work.model;

/**
 * Created by VGazzola on 23/02/17.
 */
public class Endpoint implements Comparable<Endpoint>{
    public int latency;
    public int id;



    public int compareTo(Endpoint o) {
        return getValue() == o.getValue() ? 0: getValue() > o.getValue() ?1 :-1; //invert (-1, 1) if needed
    }


    //the smaller, the better...
    private double getValue(){
        return (double)latency;
    }
//    private double getValue(){
//        return (double)size / requests;
//    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "latency=" + latency +
                ", id=" + id +
                '}';
    }

    public Endpoint(int latency, int id) {
        this.latency = latency;
        this.id = id;
    }
}
