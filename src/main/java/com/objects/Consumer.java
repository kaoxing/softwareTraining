package com.objects;

import java.util.HashMap;

public class Consumer {
    public String consumerName; //客户名
    public HashMap<Source,Integer> sourceNeedTable;     //客户所需资源表
    public HashMap<Source,Integer> sourcePossessTable;  //客户已有资源表
    public HashMap<Source,Integer> sourceTimeTable;     //客户需要占用资源时间表
    public Consumer(String name){
        this.consumerName=name;
        sourceNeedTable =new HashMap<>();
        sourcePossessTable =new HashMap<>();
        sourceTimeTable =new HashMap<>();
    }
    @Override
    public String toString(){
        return consumerName;
    }
}
