package com.objects;

import java.util.HashMap;
import java.util.HashSet;

public class Bank {
    public String bankName; //银行名
    public HashSet<Consumer> consumerTable; //银行客户表
    public HashMap<Source,Integer> sourceTable; //银行资源表
    public Bank(String name) {
        this.bankName = name;
        consumerTable = new HashSet<>();
        sourceTable = new HashMap<>();
    }
    @Override
    public String toString(){
        return bankName;
    }
}
