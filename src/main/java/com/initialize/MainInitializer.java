package com.initialize;

import com.objects.*;
import com.rand.Randomer;

import java.util.HashSet;


public class MainInitializer {
    //初始化器，用于进行初始化，需要客户数和资源数，返回一个银行对象。

    private int consumerCnt,sourceCnt;  //客户数和资源种数
    private Bank theBank;   //银行对象
    private HashSet<Consumer> consumerTable;    //客户列表
    private HashSet<Source> sourceTable;    //资源列表
    private Randomer rand;
    private int sourceLimit;//客户初始能够拥有资源或需求资源上限,应当大于0
    private int timeLimit;//客户所需占用资源的时间上限，应当大于0
    final private int MAX=10000;//限制随机数，以免值溢出
    public MainInitializer(int consumerCnt, int sourceCnt, int seed){
        //初始化构造
        this.consumerCnt=consumerCnt;
        this.sourceCnt=sourceCnt;
        rand=new Randomer();
        rand.setSeed(seed);
        sourceLimit = rand.nextInt()%MAX+1;
        timeLimit = rand.nextInt()%MAX+1;
    }
    public MainInitializer(int ConsumerCnt,int SourceCnt){
        this(ConsumerCnt,SourceCnt,(int)System.currentTimeMillis());
    }
    private void sourceInit(){
        //初始化所有资源，并放入资源列表中
        sourceTable = new HashSet<>();
        for(int i=0;i<sourceCnt;i++){
            sourceTable.add(new Source(String.format("资源%02d",i)));
        }
    }
    private void ConsumerInit(){
        //初始化所有客户，并放入客户列表中
        consumerTable=new HashSet<>();
        for(int i=0;i<consumerCnt;i++){
            Consumer con =new Consumer(String.format("客户%02d",i));
            for(Source s:sourceTable){
                con.sourcePossessTable.put(s,rand.nextInt(sourceLimit));
                con.sourceNeedTable.put(s,rand.nextInt(sourceLimit));
                con.sourceTimeTable.put(s,rand.nextInt(timeLimit));
            }
            consumerTable.add(con);
        }
        //初始化所需资源表
    }
    private void BankInit(){
        //初始化银行
        theBank=new Bank("银行");
        for(Consumer c:consumerTable){
            theBank.consumerTable.add(c);
        }
        for(Source s:sourceTable){
            theBank.sourceTable.put(s,rand.nextInt(sourceLimit*consumerCnt));
        }
    }

    public Bank getBank() {
        return theBank;
    }

    public HashSet<Consumer> getConsumerTable() {
        return consumerTable;
    }

    public HashSet<Source> getSourceTable() {
        return sourceTable;
    }
}
