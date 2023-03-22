package com.generate;

import com.example.softwaretraining.MainController;
import com.initialize.MainInitializer;
import com.objects.Bank;
import com.objects.Consumer;
import com.objects.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SafeOrderGenerate {
    private MainController mainController;
    private Bank bank;
    private ArrayList<Consumer> consumers;
    private HashSet<Consumer> unDoMap =new HashSet<>();
    private HashSet<Consumer> didMap =new HashSet<>();
    private HashMap<Resource,Integer> resourceMap=new HashMap<>();
    private int cnt;    //客户个数
    private int time;   //某条成功搜索路径所消耗时间
    public SafeOrderGenerate(MainController mainController){
        this.mainController=mainController;
    }
    public void set(MainInitializer initializer){
        this.bank=initializer.getBank();
    }
    public void start(){

        resourceMap.clear();
        cnt=bank.consumerTable.size();
        for(Resource res:bank.sourceTable.keySet()){
            resourceMap.put(res,bank.sourceTable.get(res));
        }
        for(Consumer c:consumers){
            unDoMap.clear();
            unDoMap.add(c);

        }
    }
    public void DFS(){
        boolean flag=true;
        if(unDoMap.size()==cnt){
            return;
        }
        for(Consumer c:consumers){
            if(!unDoMap.contains(c)){
                if(tryMalloc(c)){
                    //若当前资源足够分配则继续寻找下一个能够分配的
                    unDoMap.add(c);
                    DFS();
                    flag=false;
                }
            }
        }
        if(flag){
            //若flag为true则表示当前资源以及无法为任何一个客户分配资源
            if(unDoMap.size()==0){
                //且资源已足够的客户列表为空，则表示当前序列失败

                return;
            }
            //若当前资源足够的客户列表不为空，则先让当前所有客户完成
        }
    }
    public boolean tryMalloc(Consumer c){
        //尝试分配，若能分配则返回true并分配，否则返回false并不予分配
        resourceMap.replaceAll((r, v) -> resourceMap.get(r) - c.sourceNeedTable.get(r));
        for(Resource res:resourceMap.keySet()){
            if(resourceMap.get(res)<0){
                resourceMap.replaceAll((r, v) -> resourceMap.get(r) + c.sourceNeedTable.get(r));
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){

    }
}
