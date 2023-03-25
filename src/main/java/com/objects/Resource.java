package com.objects;

import javafx.beans.property.SimpleStringProperty;

public class Resource implements Comparable<Resource>{
    public String sourceName;   //资源名

    public int sourceCount; //资源数量

    public int sourceTime;  //资源时间

    public Resource(String name) {
        this.sourceName = name;
    }

    @Override
    public String toString(){
        return sourceName;
    }

    @Override
    public int compareTo(Resource o) {
        return sourceName.compareTo(o.sourceName);
    }

    public void setCnt(int x){
        sourceCount=x;
    }

    public int getCnt(){
        return sourceCount;
    }

    public SimpleStringProperty getCntProperty(){
        return new SimpleStringProperty(String.valueOf(sourceCount));
    }

    public void setTime(int x){
        sourceTime=x;
    }

    public int getTime(){
        return sourceTime;
    }

    public SimpleStringProperty getTimeProperty(){
        return new SimpleStringProperty(String.valueOf(sourceTime));
    }

    public Resource clone(){
        Resource sc = new Resource(sourceName);
        return sc;
    }
}
