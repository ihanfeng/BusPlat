package com.quadtree.clustering;

import lombok.Data;

/**
 * Created by hg on 2016/10/16.
 */
@Data
public class CounterGeoPoint implements IGeoPoint{

    private int size;

    @Override
    public int getLat() {
        return 0;
    }

    @Override
    public int getLng() {
        return 0;
    }
}
