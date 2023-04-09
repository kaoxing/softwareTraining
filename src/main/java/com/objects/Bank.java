package com.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
    private String bankName; //银行名
    private ArrayList<Consumer> consumerTable; //银行客户表
    private HashMap<Resource,Integer> sourceTable; //银行资源表
    public Bank(String name) {
        this.bankName = name;
        consumerTable = new ArrayList<>();
        sourceTable = new HashMap<>();
    }

    public ArrayList<Consumer> getConsumerTable() {
        return consumerTable;
    }

    public HashMap<Resource, Integer> getResourceTable() {
        return sourceTable;
    }


    @Override
    public String toString(){
        return bankName;
    }
}
