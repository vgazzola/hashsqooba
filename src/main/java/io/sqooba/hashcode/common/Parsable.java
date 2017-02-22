package io.sqooba.hashcode.common;

/**
 * Created by VGazzola on 22/02/17.
 */
public interface Parsable<T> {

    public T parse(String filePath);
}
