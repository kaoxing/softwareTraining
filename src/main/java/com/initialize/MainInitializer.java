package com.initialize;

import com.example.softwaretraining.MainController;
import com.objects.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class MainInitializer {
    //初始化器，用于进行初始化，需要客户数和资源数，返回一个银行对象。

    private int consumerCnt,sourceCnt;  //客户数和资源种数
    private Bank theBank;   //银行对象
    private ArrayList<Consumer> consumerTable;    //客户列表
    private ArrayList<Resource> resourceTable;    //资源列表
    private Random rand;
    private int sourceLimit;//客户初始能够拥有资源或需求资源上限,应当大于0
    private int timeLimit;//客户所需占用资源的时间上限，应当大于0
    final private int MAX=10;//限制随机数，以免值溢出

    private ObservableList<ResourceList> data=FXCollections.observableArrayList();    //银行初始化资源列表
    private MainController mainController;
    public MainInitializer(MainController mainController){
        this.mainController=mainController;
    }

    public int set(int consumerCnt, int sourceCnt, int seed){
        //初始化构造
//        System.out.println(consumerCnt);
//        System.out.println(sourceCnt);
//        System.out.println(seed);
        this.consumerCnt=consumerCnt;
        this.sourceCnt=sourceCnt;
        rand=new Random();
        rand.setSeed(seed);
        System.out.println(seed);
        sourceLimit = rand.nextInt(MAX)+2;
        timeLimit = rand.nextInt(MAX)+2;
        return seed;
    }

    public int set(int ConsumerCnt,int SourceCnt){
        return this.set(ConsumerCnt,SourceCnt,(int)System.currentTimeMillis());
    }

    private void resourceInit(){
        //初始化所有资源，并放入资源列表中
        resourceTable = new ArrayList<>();
        for(int i=0;i<sourceCnt;i++){
            resourceTable.add(new Resource(String.format("资源%03d",i)));
        }
    }
    private void consumerInit(){
        //初始化所有客户，并放入客户列表中
        consumerTable=new ArrayList<>();
        for(int i=0;i<consumerCnt;i++){
            Consumer con =new Consumer(String.format("客户%03d",i));
            for(Resource s: resourceTable){
                con.getResourcePossessTable().put(s,rand.nextInt(sourceLimit));
                con.getResourceNeedTable().put(s,rand.nextInt(sourceLimit));
                con.getResourceTimeTable().put(s,rand.nextInt(timeLimit));
            }
            consumerTable.add(con);
        }
        //初始化所需资源表
    }
    private void bankInit(){
        //初始化银行
        theBank=new Bank("银行");
        for(Consumer c:consumerTable){
            theBank.getConsumerTable().add(c);
        }
        for(Resource s: resourceTable){
            theBank.getResourceTable().put(s,rand.nextInt(sourceLimit*consumerCnt));
        }
    }

    public Bank getBank() {
        return theBank;
    }

    public ArrayList<Consumer> getConsumerTable() {
        return consumerTable;
    }

    public ArrayList<Resource> getResourceTable() {
        return resourceTable;
    }

    public void startInit(){
        //执行初始化操作
        resourceInit();
        consumerInit();
        bankInit();
    }

    public void initFrame(){
        //初始化界面

        Platform.runLater(()->{

            //清空全部
            clear();
            for(Consumer c:consumerTable){
                //向复选框中加入客户
                mainController.consumerComboBox.getItems().add(c);
            }
            mainController.consumerComboBox.getItems().sort(new Comparator<Consumer>() {
                //加入客户后排序
                @Override
                public int compare(Consumer o1, Consumer o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
            //初始化银行资源表
            ResourceList sourceList = new ResourceList(theBank.getResourceTable());
            data.add(sourceList);

            TableColumn<ResourceList,String> tc;
            int i=0;
            for(Resource s:sourceList.resourceList){

//                System.out.print(i+":");
//                System.out.println(sourceList.resourceList[i]);
                tc = new TableColumn<>(s.toString());
                int finalI1 = i;
                tc.setCellValueFactory(cellData-> cellData.getValue().getCntProperty(finalI1));
                i++;
                mainController.bankSourceTable.getColumns().add(tc);
            }
            mainController.bankSourceTable.setItems(data);


        });
    }
    public void clear(){
        mainController.bankSourceTable.getColumns().clear();
        mainController.consumerComboBox.getItems().clear();
        data.clear();
    }
}
