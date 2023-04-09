package com.generate;

import com.objects.Consumer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.LinkedList;

public class ConsumerList implements Comparable<ConsumerList>{
    private LinkedList<Consumer> consumerList;
    public int time;
    public ConsumerList(ArrayList<Consumer> stack,int time){
        consumerList=new LinkedList<>();
        for(int i=0;i<stack.size();i++){
            Consumer con = stack.get(i);
            consumerList.add(con);
        }
        this.time=time;
    }
    public SimpleStringProperty getNameProperty(int i){
        return consumerList.get(i).getNameProperty();
    }
    public String toString(){
        return consumerList.toString();
    }
    public SimpleStringProperty getTimeProperty(){
        return new SimpleStringProperty(String.valueOf(time));
    }
    @Override
    public int compareTo(ConsumerList o) {
        return time-o.time;
    }
}
