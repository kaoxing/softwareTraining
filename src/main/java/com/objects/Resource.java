package com.objects;

import javafx.beans.property.SimpleStringProperty;

public class Resource implements Comparable<Resource>{
    public String sourceName;   //资源名
    public SimpleStringProperty sourceCount=new SimpleStringProperty();   //存储资源数量,以便后续放入表中

    public SimpleStringProperty sourceTime=new SimpleStringProperty();    //存储资源时间,以便后续放入表中
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
        sourceCount.set(String.valueOf(x));
    }

    public int getCnt(){
        return Integer.parseInt(sourceCount.get());
    }
    public SimpleStringProperty getCntProperty(){
        return sourceCount;
    }
    public void setTime(int x){
        sourceTime.set(String.valueOf(x));
    }

    public int getTime(){
        return Integer.parseInt(sourceTime.get());
    }

    public SimpleStringProperty getTimeProperty(){
        return sourceTime;
    }

    public Resource clone(){
        Resource sc = new Resource(sourceName);
        return sc;
    }
}
