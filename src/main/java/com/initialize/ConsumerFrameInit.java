package com.initialize;

import com.example.softwaretraining.MainController;
import com.objects.Consumer;
import com.objects.Resource;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class ConsumerFrameInit {
    private ResourceList sourcePossessList,sourceNeedList,sourceTimeList;
    private ObservableList<ResourceList> sourcePossessData= FXCollections.observableArrayList();
    private ObservableList<ResourceList> sourceNeedData= FXCollections.observableArrayList();
    private ObservableList<ResourceList> sourceTimeData= FXCollections.observableArrayList();
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
    private void initPossess(){
        //初始化客户拥有资源表
        sourcePossessList = new ResourceList(consumer.getResourcePossessTable());
        sourcePossessData.add(sourcePossessList);
        TableColumn<ResourceList,String> tc;
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
    private void initNeed(){
        //初始化客户需要资源表
        sourceNeedList = new ResourceList(consumer.getResourceNeedTable());
        sourceNeedData.add(sourceNeedList);
        TableColumn<ResourceList,String> tc;
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
    private void initTime(){
        //初始化客户资源时间表
        sourceTimeList = new ResourceList(consumer.getResourceTimeTable());
        sourceTimeData.add(sourceTimeList);
        TableColumn<ResourceList,String> tc;
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
