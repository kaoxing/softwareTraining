package com.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
    public String bankName; //银行名
    public ArrayList<Consumer> consumerTable; //银行客户表
    public HashMap<Resource,Integer> sourceTable; //银行资源表
    public Bank(String name) {
        this.bankName = name;
        consumerTable = new ArrayList<>();
        sourceTable = new HashMap<>();
    }
    @Override
    public String toString(){
        return bankName;
    }
}
