package com.generate;

import com.objects.Consumer;
import com.objects.Resource;

public class ResourcePack{
    //拆解客户类后分成很多个resourcePack以便在时间到达后返还总资源表
    private Consumer consumer;//用于标记当前资源是谁的，以便确定客户完成需求的先后次序
    private Resource resource;

    private int count;
    private int time;
    public ResourcePack(Consumer c,Resource r,int cnt,int t){
        consumer=c;
        resource=r;
        count=cnt;
        time=t;
    }
    public Consumer getConsumer(){
        return consumer;
    }
    public Resource getResource(){
        return resource;
    }
    public int getCount(){
        return count;
    }
    public int getTime(){
        return time;
    }
    public void addCount(int cnt){
        count+=cnt;
    }

    public void minorTime(int time){
        this.time-=time;
    }

    @Override
    public String toString(){
        StringBuilder str=new StringBuilder();
        str.append("[Consumer=");
        str.append(consumer);
        str.append("Resource=");
        str.append(resource);
        str.append("Count=");
        str.append(count);
        str.append("Time=");
        str.append(time);
        str.append("]\n");
        return str.toString();
    }
}

