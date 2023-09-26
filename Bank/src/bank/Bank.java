/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author WiZ14
 */
public class Bank extends Application {
   Stage stage;int[][] logininfo ;TextField accountpassword;boolean bool;
    TextField accountnumber; //static int creditcard;
    //secondstage
    TextField deposit; Label depositl,withdrawall;Scene moneys;RadioButton r1,r2,r3,r4;
    ToggleGroup togglegroup;Button withdrawalb,depositb;/*thirdstage->*/Button logb;
    
    
    Button back;
    
    @Override
    public void start(Stage primaryStage) {
      stage=primaryStage;logininfo= new int[][]{ {11110000,1234}, {11110001,5678} , {11110002, 2345}};
        Label accountnumberl=new Label("account number");Label accountpasswordl=new Label("account password");accountnumber=new TextField("11110001");accountpassword=new TextField("5678");/*accountnumber "11110001" password "5678"*/
        Button accountcheck=new Button("AccountCheck");
        accountcheck.setOnAction(e-> {if(!(accountnumber.getText().isEmpty()||accountpassword.getText().isEmpty())){AccountCheck(Integer.valueOf(accountnumber.getText()),Integer.valueOf(accountpassword.getText()));}else{System.out.println("Field empty");}   });//stage1 buttoncall
       // StackPane root = new StackPane();
       // root.getChildren().add(btn);
        VBox root = new VBox(accountnumberl,accountnumber,accountpasswordl,accountpassword,accountcheck);
        Scene scene = new Scene(root, 300, 250);
root.getStylesheets().add("sheets/forms.css");
        primaryStage.setTitle("ATM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**Long.parseLong()
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
   /*System.out.println("Date:"+java.time.LocalDate.now()+" Time:"+java.time.LocalTime.now());   */launch(args);
    }
public void Test(String accountnumber,String transaction,String amount){try {
//accountnumber.substring(0,4)
        String content = "\n"+"creditcardnumber:"+accountnumber.replace(accountnumber.substring(4,8), "xxxx")+"\n"+"Transaction:"+transaction+"\n"+"Amount:"+amount+"\n Date:"+LocalTime.now()+"\n Time:"+LocalDate.now();
    System.out.println(content);
        File file = new File(/*insert path here*/"C:\\Users\\User\\Desktop\\testjava\\"+accountnumber+".txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();

       // System.out.println("Done");

    } catch (IOException e) {
        e.printStackTrace();
    }
}
    private void AccountCheck(int accountnumber,int accountpassword) {
  //if (checkLuhn(creditcardnumber)==true){
  //   /* System.out.println(creditcardnumber);Test(creditcardnumber)*/;Gaschoice();}else{System.out.println(creditcardnumber + "is an invalid credit card number");}
bool=false;for(int i=0;i<3;i++){if(logininfo[i][0]==accountnumber&&logininfo[i][1]==accountpassword){bool=true;}}//if(accountnumber==logininfo[i][0]){for(int j=i;j<3;j++){if(logininfo[j][i]==accountpassword){bool=true;break;}}}
       if(bool==true){Gaschoice();}else{System.out.println("Invalid login info");}
        
    }
 //first stage
    public void Gaschoice(){
r1=new RadioButton("100");r2=new RadioButton("200");r3=new RadioButton("300");r4=new RadioButton("400");//1
togglegroup=new ToggleGroup();r1.setToggleGroup(togglegroup);r2.setToggleGroup(togglegroup);r3.setToggleGroup(togglegroup); r1.setSelected(true);r4.setToggleGroup(togglegroup);
         withdrawall=new Label("WithdrawalAmount");withdrawalb=new Button("Withdraw");withdrawalb.setOnAction(e->Test(accountnumber.getText(),"Withdrawal",((RadioButton)togglegroup.getSelectedToggle()).getText()));//form 1
         depositl=new Label("DepositAmount");deposit=new TextField("0.0");depositb=new Button("Deposit");VBox form2=new VBox(depositl,deposit,depositb);//form 2
      depositb.setOnAction(e->{ if(Double.valueOf(deposit.getText())>0&&(deposit.getText().isEmpty()==false)){Test(accountnumber.getText(),"Deposition", deposit.getText());}else{System.out.println("please input proper amount ");} });
      back=new Button("Back");back.setOnAction(e->start(stage));back.setId("back_button");/*<-backbutton*//*showlogbutton->*/logb=new Button("Show Log");logb.setOnAction(e->{Test2(accountnumber.getText());});
        VBox root = new VBox(withdrawall,r1,r2,r3,r4,withdrawalb,form2,logb,back);
        root.getStylesheets().add("sheets/form2.css");
        moneys = new Scene(root,300,250);
        stage.setScene(moneys);
    }//second stage
//laststage
public void Test2(String accountnumber){
    try {

        File file = new File("C:\\Users\\User\\Desktop\\testjava\\"+accountnumber+".txt"); 
  if (file.exists()) {
           
        
  BufferedReader br = new BufferedReader(new FileReader(file)); 
  
  String st; 
  while ((st = br.readLine()) != null) 
          System.out.println(st); }else{System.out.println("No Previous Transactions Recorded");}

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
