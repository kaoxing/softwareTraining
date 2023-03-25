package com.generate;

import com.example.softwaretraining.MainController;
import com.initialize.MainInitializer;
import com.objects.Bank;
import com.objects.Consumer;
import com.objects.Resource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class SafeOrderGenerate {
    private MainController mainController;
    ObservableList<ConsumerList> showListData= FXCollections.observableArrayList();
    private Bank bank;
    private ArrayList<Consumer> consumers;//所有客户列表

    private HashMap<Resource,Integer> resourceMap=new HashMap<>();//可分配资源表
    private Set<Consumer> unsatisfiedConsumerSet= new CopyOnWriteArraySet<>();//未满足的客户，这里使用的是线程安全的集合，否则会多线程异常
    private PriorityQueue<ResourcePack> resourcesList=new PriorityQueue<>(new Comparator<ResourcePack>() {
        @Override
        public int compare(ResourcePack o1, ResourcePack o2) {
            return o1.getTime()-o2.getTime();
        }
    });
    private ArrayList<Consumer> stack = new ArrayList<>();
    public SafeOrderGenerate(MainController mainController){
        this.mainController=mainController;
    }

    public void set(MainInitializer initializer){
        this.bank=initializer.getBank();
        mainController.safeOrderTable.getColumns().clear();
        consumers= bank.getConsumerTable();
        TableColumn<ConsumerList,String> tc;
        for(int i=0;i<consumers.size();i++){
//            System.out.println(i);
            tc = new TableColumn<>(String.format("第%d个",i));
            int finalI1 = i;
            tc.setCellValueFactory(cellData-> cellData.getValue().getNameProperty(finalI1));
            mainController.safeOrderTable.getColumns().add(tc);
        }
        tc = new TableColumn<>("时间");
        tc.setCellValueFactory(cellData-> cellData.getValue().getTimeProperty());
        mainController.safeOrderTable.getColumns().add(tc);
        mainController.safeOrderTable.setItems(showListData);
    }

    public void start(){
        //定义一个函数find(Consumer c,LinkedList)
        //先拆解c
        //若目前客户已经是最后一个未满足的客户
        //（1）然后在寻找看目前有没有可以满足的客户con
        //若有则find(con)
        //若没有则在资源列中完成一个需要时间最小的
        //继续返回1，查找有没有可以满足的客户
        //若发现有可以完成的客户，则跳过2,3,4
        //(2)若资源列为空，判断未满足的客户是不是已经空
        //(3)若不为空则说明目前序列失败，无法完成所有客户
        //(4)若为空则目前序列成功，输出安全序列
        //将当前c拆解的资源取回，并将c加入unsatisfiedConsumerSet中
        resourceMap= bank.getSourceTable();
        for(Consumer c:consumers){
            unsatisfiedConsumerSet.add(c);
        }
        for(Consumer c:consumers){
            if(tryMalloc(c,false)) {
//                System.out.println("START!");
                search(c, 0, 0);
            }
        }
        Collections.sort(showListData);
    }

    public void search(Consumer c, int timeCost, int count){
        //拆解
        int ALLTIME=0;
        unsatisfiedConsumerSet.remove(c);
        breakDownTo(c,resourcesList,timeCost);
        HashSet<Consumer> visited=new HashSet<>();
        stack.add(c);
        boolean mark=false;
        if(unsatisfiedConsumerSet.size()==0){
            //若当前已经是最后一个未满足的客户
            mark=true;
        }
        boolean flag=false;
        while(resourcesList.size()!=0||flag) {
            //若资源列表不为空则继续模拟
            flag = false;
            for (Consumer con : unsatisfiedConsumerSet) {
                if(visited.contains(con))continue;//若已经尝试过该路线，则不去
                if (tryMalloc(con, false)) {//尝试分配资源
                    //若成功
                    flag = true;
                    search(con,timeCost,count);
                    visited.add(con);
                }
            }
            //寻找
            if(!flag&&resourcesList.size()!=0){
                //若当前没找到任何可以满足的用户
                ResourcePack tempPack=resourcesList.poll();//取出第一个资源
                int time=tempPack.getTime();
                ALLTIME = time;
                returnTo(tempPack,resourceMap);//将其释放
                while(resourcesList.size()!=0){
                    if(resourcesList.peek().getTime()==time){
                        tempPack=resourcesList.poll();
                        returnTo(tempPack,resourceMap);
                    }else break;
                }
                timeCost+=time;
                flag=true;
            }
        }
        if(mark){
            //若已经是最后一个客户，则生成序列
            int finalCount = count;
            ConsumerList showList = new ConsumerList(stack,ALLTIME);
            if(!showListData.contains(showList)){
                showListData.add(showList);
            }

        }
        returnBack(c,resourceMap);
        unsatisfiedConsumerSet.add(c);
        stack.remove(c);
    }

    private void push(ArrayList<Consumer> stack,ResourcePack pack,int count){
        if(stack.size()<=count){
            stack.add(pack.getConsumer());
        }else
            stack.set(count,pack.getConsumer());//入栈并记录当前在栈第几个位置
    }

    private boolean tryMalloc(Consumer c,boolean tryIt){
        //若tryIt为true则表示只是试试，不需要真的分配
        //尝试分配，若能分配则返回true并分配，否则返回false并不予分配
        resourceMap.replaceAll((r, v) -> v - c.getResourceNeedTable().get(r));
        for(Resource res:resourceMap.keySet()){
            if(resourceMap.get(res)<0){
                resourceMap.replaceAll((r,v)->v+ c.getResourceNeedTable().get(r));
                return false;
            }
        }
        //若只是试试，则应当返还资源
        if(tryIt){
            resourceMap.replaceAll((r,v)->v+ c.getResourceNeedTable().get(r));
            return true;
        }
        return true;
    }

    private void breakDownTo(Consumer consumer,PriorityQueue<ResourcePack> runningResourceList,int base){
        //拆解某个客户，将其拥有的资源送入runningResourceList中
        for(Resource res: consumer.getResourcePossessTable().keySet()){
            ResourcePack pack = new ResourcePack(consumer,
                    res,
                    consumer.getResourcePossessTable().get(res),
                    consumer.getResourceTimeTable().get(res)+base);
            pack.addCount(consumer.getResourceNeedTable().get(res));
            runningResourceList.add(pack);
        }
    }

    private void returnTo(ResourcePack pack,HashMap<Resource,Integer> resources){
        //将资源返回resources
        resources.put(pack.getResource(),resources.getOrDefault(pack.getResource(),0)+pack.getCount());
    }

    private void returnBack(Consumer con,HashMap<Resource,Integer> resources){
        HashMap<Resource, Integer> finalTemp = con.getResourcePossessTable();
        resources.replaceAll((r, v)->v- finalTemp.get(r));
    }

}
