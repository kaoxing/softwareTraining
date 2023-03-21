package com.objects;

public class Source {
    public String sourceName;   //资源名
    public Source(String name) {
        this.sourceName = name;
    }
    @Override
    public String toString(){
        return sourceName;
    }
}
