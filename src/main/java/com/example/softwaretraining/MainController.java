package com.example.softwaretraining;

import com.generate.ConsumerList;
import com.generate.SafeOrderGenerate;
import com.initialize.ConsumerFrameInit;
import com.initialize.MainInitializer;
import com.initialize.SourceList;
import com.objects.Bank;
import com.objects.Consumer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {


    @FXML
    public ComboBox<Consumer> consumerComboBox;

    @FXML
    public TableView<ConsumerList> safeOrderTable;

    @FXML
    public TableView<SourceList> consumerPossessSourceTable;

    @FXML
    public Button generateButton;

    @FXML
    public TextField consumerInput;

    @FXML
    public Label safeOrderLabel;

    @FXML
    public TableView<SourceList> consumerNeedSourceTable;

    @FXML
    public TableView<SourceList> consumerSourceTimeTable;

    @FXML
    public Button clearButton;

    @FXML
    public TextField sourceInput;

    @FXML
    public TableView<SourceList> bankSourceTable;

    @FXML
    public Button initButton;

    @FXML
    public TextField seedInput;


    public Bank bank;

    private MainInitializer initializer=new MainInitializer(this);
    private ConsumerFrameInit consumerFrameInit =new ConsumerFrameInit(this);//初始化并显示用户表
    private SafeOrderGenerate safeOrderGenerate;//序列生成器
    @FXML
    void consumerInputChanged() {
        //限制输入个数最多为999
        if (consumerInput.getText().length() > 2) {
            String s = consumerInput.getText().substring(0, 2);
            consumerInput.setText(s);
        }
    }

    @FXML
    void sourceInputChanged() {
        //限制输入个数最多为999
        if (sourceInput.getText().length() > 2) {
            String s = sourceInput.getText().substring(0, 2);
            sourceInput.setText(s);
        }
    }

    @FXML
    void onInitButton() {
        //初始化按钮点击函数
//        addToCombo(new Consumer("kaoxing"+System.currentTimeMillis()));
        //检查输入有效性
        if(!(Checker.isNumber(consumerInput.getText())&&Checker.isNumber(sourceInput.getText()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("输入不是有效数字！");
            alert.show();
            return;
        }
        initializer.clear();
        consumerFrameInit.clear();
        int seed;
        //检查是否有种子，并初始化初始化器
        if(!Checker.isNumber(seedInput.getText())){
            seed = initializer.set(
                    Integer.parseInt(consumerInput.getText()),
                    Integer.parseInt(sourceInput.getText())
            );
        }else{
            seed = initializer.set(
                    Integer.parseInt(consumerInput.getText()),
                    Integer.parseInt(sourceInput.getText()),
                    Integer.parseInt(seedInput.getText())
            );
        }
        //将初始化结果送入界面
        initializer.startInit();
        initializer.initFrame();
        seedInput.setText(String.valueOf(seed));
        consumerInput.setDisable(true);//禁用输入框
        sourceInput.setDisable(true);
        seedInput.setEditable(false);//种子不可输入
        generateButton.setDisable(false);//启用生成按钮
        clearButton.setDisable(false);//启用清空按钮
        initButton.setDisable(true);//禁用初始化按钮
    }

    @FXML
    void onClearButton() {
        //界面回到初始状态，完全清空
        initializer.clear();
        consumerFrameInit.clear();
        consumerInput.clear();//清空输入和种子
        sourceInput.clear();
        seedInput.clear();
        consumerInput.setDisable(false);//启用输入框
        sourceInput.setDisable(false);
        seedInput.setEditable(true);//种子可输入
        generateButton.setDisable(true);//禁用生成按钮
        clearButton.setDisable(true);//禁用清空按钮
        initButton.setDisable(false);//启用初始化按钮
    }

    @FXML
    void onGenerateButton() {
        //生成安全序列
//        System.out.println("here");
        Platform.runLater(()->{
            safeOrderGenerate = new SafeOrderGenerate(this);
            safeOrderGenerate.set(initializer);
            safeOrderGenerate.start();
        });

    }


    @FXML
    void consumerBoxSelect() {
        //选择了某个客户
//        System.out.println("selected");
        Consumer selectedConsumer = consumerComboBox.getValue();
        if(selectedConsumer == null)return;
        consumerFrameInit.set(selectedConsumer);
        consumerFrameInit.start();
    }

    public void initialize(){
        //窗口初始化设置函数

        onClearButton();
    }

    public void addToCombo(Consumer consumer){
        //向Combobox内加入客户
        Platform.runLater(()->{
            consumerComboBox.getItems().add(consumer);
        });
    }
}
