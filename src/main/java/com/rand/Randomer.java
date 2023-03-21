package com.rand;

import java.util.Random;

public class Randomer{
    //用于产生随机数,nextInt方法返回一个随机数
    private int limit;  //限制随机数的大小
    private int seed;   //用于初始化的种子
    private Random rand=new Random(0);

    public void setSeed(int seed) {
        this.seed = seed;
        rand.setSeed(seed);
    }
    public int getSeed(){
        return seed;
    }
    public void setLimit(int limit){
        this.limit = limit;
    }
    public int nextInt(int limit){
        return rand.nextInt()%limit;
    }
    public int nextInt(){
        return nextInt(limit);
    }
}