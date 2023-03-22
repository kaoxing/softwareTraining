package com.initialize;

import com.example.softwaretraining.MainController;
import com.objects.Consumer;
import com.objects.Resource;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class ConsumerFrameInit {
    private SourceList sourcePossessList,sourceNeedList,sourceTimeList;
    private ObservableList<SourceList> sourcePossessData= FXCollections.observableArrayList();
    private ObservableList<SourceList> sourceNeedData= FXCollections.observableArrayList();
    private ObservableList<SourceList> sourceTimeData= FXCollections.observableArrayList();
    private MainController mainController;
    private Consumer consumer;
    public ConsumerFrameInit(MainController mainController){
        //初始化客户界面
        this.mainController = mainController;

    }
    public void set(Consumer consumer){
        this.consumer=consumer;
    }
    public void start(){
        Platform.runLater(()->{
            //清空用户所有表
            clear();
            //初始化客户拥有资源表
            initPossess();
            //初始化客户需要资源表
            initNeed();
            //初始化客户资源时间表
            initTime();

        });
    }
    public void initPossess(){
        //初始化客户拥有资源表
        sourcePossessList = new SourceList(consumer.sourcePossessTable);
        sourcePossessData.add(sourcePossessList);
        TableColumn<SourceList,String> tc;
        int i=0;
        for(Resource s:sourcePossessList.resourceList){
            tc = new TableColumn<>(s.toString());
            int finalI1 = i;
            tc.setCellValueFactory(cellData-> cellData.getValue().getCntProperty(finalI1));
            i++;
            mainController.consumerPossessSourceTable.getColumns().add(tc);
        }
        mainController.consumerPossessSourceTable.setItems(sourcePossessData);
    }
    public void initNeed(){
        //初始化客户需要资源表
        sourceNeedList = new SourceList(consumer.sourceNeedTable);
        sourceNeedData.add(sourceNeedList);
        TableColumn<SourceList,String> tc;
        int i=0;
        for(Resource s:sourceNeedList.resourceList){
            tc = new TableColumn<>(s.toString());
            int finalI1 = i;
            tc.setCellValueFactory(cellData-> cellData.getValue().getCntProperty(finalI1));
            i++;
            mainController.consumerNeedSourceTable.getColumns().add(tc);
        }
        mainController.consumerNeedSourceTable.setItems(sourceNeedData);
    }
    public void initTime(){
        //初始化客户资源时间表
        sourceTimeList = new SourceList(consumer.sourceTimeTable);
        sourceTimeData.add(sourceTimeList);
        TableColumn<SourceList,String> tc;
        int i=0;
        for(Resource s:sourceTimeList.resourceList){
            tc = new TableColumn<>(s.toString());
            int finalI1 = i;
            tc.setCellValueFactory(cellData-> cellData.getValue().getTimeProperty(finalI1));
            i++;
            mainController.consumerSourceTimeTable.getColumns().add(tc);
        }
        mainController.consumerSourceTimeTable.setItems(sourceTimeData);
    }
    public void clear(){
        mainController.consumerPossessSourceTable.getColumns().clear();
        sourcePossessData.clear();
        mainController.consumerNeedSourceTable.getColumns().clear();
        sourceNeedData.clear();
        mainController.consumerSourceTimeTable.getColumns().clear();
        sourceTimeData.clear();
    }
}
