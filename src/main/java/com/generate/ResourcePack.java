package com.generate;

import com.objects.Consumer;
import com.objects.Resource;

public class ResourcePack{
    //拆解客户类后分成很多个resourcePack以便在时间到达后返还总资源表
    private Resource resource;

    private int count;
    private int time;
    public ResourcePack(Resource r,int cnt,int t){
        resource=r;
        count=cnt;
        time=t;
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


    @Override
    public String toString(){
        StringBuilder str=new StringBuilder();
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

