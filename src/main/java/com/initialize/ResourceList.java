package com.initialize;

import com.objects.Resource;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;
import java.util.HashMap;

public class ResourceList {
    //显示资源表，用于在表中显示资源的临时资源列表
    Resource[] resourceList;
    public ResourceList(HashMap<Resource,Integer> sourceTable){

        resourceList =new Resource[sourceTable.size()];
        int i=0;
        for(Resource s:sourceTable.keySet()){
            resourceList[i] = s.clone();
            resourceList[i].setCnt(sourceTable.get(s));
            resourceList[i++].setTime(sourceTable.get(s));
        }
        Arrays.sort(resourceList);
    }
    public SimpleStringProperty getCntProperty(int i){
        return resourceList[i].getCntProperty();
    }

    public SimpleStringProperty getTimeProperty(int i){
        return resourceList[i].getTimeProperty();
    }
}
