package com.objects;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;

public class Consumer {
    private String consumerName; //客户名
    private HashMap<Resource,Integer> resourceNeedTable;     //客户所需资源表
    private HashMap<Resource,Integer> resourcePossessTable;  //客户已有资源表
    private HashMap<Resource,Integer> resourceTimeTable;     //客户需要占用资源时间表
    public Consumer(String name){
        this.consumerName=name;
        resourceNeedTable =new HashMap<>();
        resourcePossessTable =new HashMap<>();
        resourceTimeTable =new HashMap<>();
    }

    public String getConsumerName() {
        return consumerName;
    }

    public HashMap<Resource, Integer> getResourceNeedTable() {
        return resourceNeedTable;
    }

    public HashMap<Resource, Integer> getResourcePossessTable() {
        return resourcePossessTable;
    }

    public HashMap<Resource, Integer> getResourceTimeTable() {
        return resourceTimeTable;
    }

    public SimpleStringProperty getNameProperty(){
        return new SimpleStringProperty(consumerName);
    }
    @Override
    public String toString(){
        return consumerName;
    }
}
