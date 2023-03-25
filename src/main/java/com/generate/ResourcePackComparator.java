package com.generate;

import java.util.Comparator;

public class ResourcePackComparator implements Comparator<ResourcePack> {
    @Override
    public int compare(ResourcePack o1, ResourcePack o2) {
        return o1.getTime()-o2.getTime();
    }
}
