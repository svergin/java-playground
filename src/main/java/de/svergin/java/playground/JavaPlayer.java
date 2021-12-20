package de.svergin.java.playground;

import de.svergin.java.playground.samples.StreamApiSample;

public class JavaPlayer {

    public static void main(String[] args) {
        StreamApiSample apiSample = new StreamApiSample();
        apiSample.streamForEachSimple();
        apiSample.streamMatchers();
        apiSample.streamMap();
        apiSample.streamFlatMap();
    }
}
