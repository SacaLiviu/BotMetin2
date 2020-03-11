package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jnativehook.GlobalScreen;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;


public class Controller {

    @FXML
    private Button ButtonStack;
    @FXML
    private Button ButtonShop;
    @FXML
    private TextField TextFieldNrIteme;
    @FXML
    private TextField TextFieldStack;
    @FXML
    private TextField TextFieldShopLine;
    @FXML
    private TextField TextFieldShopRow;
    @FXML
    private TextField TextFieldPrice;
    @FXML
    private CheckBox Kashmir;
    @FXML
    private Label Loading;

    //BUTTON SHOP
    @FXML
    private void ButtonShop() throws InterruptedException, AWTException {

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        GlobalCoordsXY coordonate= new GlobalCoordsXY();
        MyThread threadShop = new MyThread();
        threadShop.start();
        threadShop.join();
        int xShop=threadShop.returnThreadX();
        int yShop=threadShop.returnThreadY();
        MyThread ThreadInventar = new MyThread();

        ThreadInventar.start();
        ThreadInventar.join();
        int xInventar=ThreadInventar.returnThreadX();
        int yInventar=ThreadInventar.returnThreadY();
        int pret =TextFieldPrice();
        int linie=TextFieldLine();
        int coloana=TextFieldRow();
        PuneShop(xShop,yShop,xInventar,yInventar,pret,linie,coloana);
    }

    private int TextFieldPrice(){
        return parseInt(TextFieldPrice.getText());
    }

    private int TextFieldLine(){
        return parseInt(TextFieldShopLine.getText());
    }

    private int TextFieldRow(){
        return parseInt(TextFieldShopRow.getText());
    }

    private int TextFieldStack(){ return parseInt(TextFieldStack.getText()); }

    private int TextFieldNrIteme(){ return parseInt(TextFieldNrIteme.getText()); }

    private boolean kashmir(){ return Kashmir.isSelected();}

    private void PuneShop(int xShop, int yShop, int xInventar, int yInventar, int pret,int linii,int coloane) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        boolean kashmirCheck=kashmir();
            //descompun pretu in numere
            //NU UITA CA E DE LA COADA LA CAP NU INVERS
            int length = (int) (Math.log10(pret) + 1);
            int[] ListaPret = descompunere(pret);
            if(!kashmirCheck) {
                for (int i = 0; i < coloane; i++) {
                    for (int j = 0; j < linii; j++) {
                        robot.mouseMove(xShop + i * 30, yShop + (j + 1) * 30);
                        Thread.sleep(25);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        Thread.sleep(25);
                        robot.mouseMove(xInventar + i * 30, yInventar + j * 30);
                        Thread.sleep(25);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        Thread.sleep(25);
                        ApasaPret(ListaPret, length);
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyRelease(KeyEvent.VK_ENTER);
                    }
                }
            }
            else{
                boolean kashmirCounter=false;
                for (int i = 0; i < coloane; i++) {
                    for (int j = 0; j < linii; j++) {
                        robot.mouseMove(xShop + i * 30, yShop + (j + 1) * 30);
                        Thread.sleep(25);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        Thread.sleep(25);
                        robot.mouseMove(xInventar + i * 30, yInventar + j * 30);
                        Thread.sleep(25);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        Thread.sleep(25);
                        if(!kashmirCounter) {
                            for(int k=0;k<=10;k++){
                                robot.keyPress(KeyEvent.VK_BACK_SPACE);
                                robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                            }
                            ApasaPret(ListaPret, length);
                            kashmirCounter = true;
                            robot.keyPress(KeyEvent.VK_ENTER);
                            robot.keyRelease(KeyEvent.VK_ENTER);
                        }
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyRelease(KeyEvent.VK_ENTER);
                        Thread.sleep(25);
                    }
                }
            }

    }

    private static void ApasaPret(int[] ListaPret, int length) throws AWTException, InterruptedException {
        Robot robot= new Robot();
        int NumarTemporar;
        for(int i=length;i>0;i--){
            Thread.sleep(15);
            NumarTemporar=ListaPret[i-1];
            if(NumarTemporar==0){
                robot.keyPress(KeyEvent.VK_0);
                robot.keyRelease(KeyEvent.VK_0);
            }
            if(NumarTemporar==1){
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
            }
            if(NumarTemporar==2){
                robot.keyPress(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_2);
            }
            if(NumarTemporar==3){
                robot.keyPress(KeyEvent.VK_3);
                robot.keyRelease(KeyEvent.VK_3);
            }
            if(NumarTemporar==4){
                robot.keyPress(KeyEvent.VK_4);
                robot.keyRelease(KeyEvent.VK_4);
            }
            if(NumarTemporar==5){
                robot.keyPress(KeyEvent.VK_5);
                robot.keyRelease(KeyEvent.VK_5);
            }
            if(NumarTemporar==6){
                robot.keyPress(KeyEvent.VK_6);
                robot.keyRelease(KeyEvent.VK_6);
            }
            if(NumarTemporar==7){
                robot.keyPress(KeyEvent.VK_7);
                robot.keyRelease(KeyEvent.VK_7);
            }
            if(NumarTemporar==8){
                robot.keyPress(KeyEvent.VK_8);
                robot.keyRelease(KeyEvent.VK_8);
            }
            if(NumarTemporar==9){
                robot.keyPress(KeyEvent.VK_9);
                robot.keyRelease(KeyEvent.VK_9);
            }
        }
    }

    private int[] descompunere(int pret){
        int n=pret;
        int length = (int)(Math.log10(n)+1);
        int[] ListaPret = new int [length];
        for(int i=0;i<length;i++){
            if(pret<10) {
                ListaPret[i] = pret;
            }
            ListaPret[i]=pret%10;
            pret=pret/10;
        }
        return ListaPret;
    }

    //BUTTON STACK
    @FXML
    private void ButtonStack() throws InterruptedException, AWTException {
        int NrStack=TextFieldStack();
        int[] NrStackList= new int [1];
        NrStackList[0]=NrStack;
        int NrIteme=TextFieldNrIteme();
        MyThread ThreadInventar = new MyThread();
        ThreadInventar.start();
        ThreadInventar.join();
        int xInventar=ThreadInventar.returnThreadX();
        int yInventar=ThreadInventar.returnThreadY();
        MoveMouse(xInventar,yInventar,NrStackList,NrIteme);
    }

    private static void MoveMouse(int xShop,int yShop,int[] NrStack,int NrIteme) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        // da un click sa fie centrat metinul
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        int Stop = 0;
        IesireLoop:
        for (int i = 0; i < 5 ; i++) {
            for (int j = 1; j < 9 ; j++) {
                //Muta mouse pe primu spatiu
                robot.mouseMove(xShop, yShop);
                Thread.sleep(25);
                //Apasa shit->apasa click
                robot.keyPress(KeyEvent.VK_SHIFT);
                Thread.sleep(25);// DACA MODIFICI MAI MULT SARE PESTE ITEME!!
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                //Ridica shit->Ridica click
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                //Apasa sageata dreapta si ridica
                Thread.sleep(25);
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                //Sterge 1
                robot.keyPress(KeyEvent.VK_BACK_SPACE);
                robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                Thread.sleep(25);
                //Adauga de cat sa fie stack
                ApasaPret(NrStack,1);
                //Apasa enter si ridica
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.mouseMove(xShop + 30 * i, yShop + 30 * j);
                Thread.sleep(25);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                Stop+=NrStack[0];
                if(Stop>=NrIteme)
                break IesireLoop;
            }
        }
    }
}