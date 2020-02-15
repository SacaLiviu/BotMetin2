package sample;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread {
    int x,y;

    GlobalCoordsXY coordonate = new GlobalCoordsXY();
    public void run(){
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalMouseListenerExample MouseListener1 = new GlobalMouseListenerExample();
        GlobalScreen.addNativeMouseListener(MouseListener1);
        int MouseState;
        do {
            coordonate.setI(MouseListener1.getX());
            coordonate.setJ(MouseListener1.getY());
            MouseState = MouseListener1.getMouseState();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setX();
            setY();
        } while (MouseState != 1);
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }
    
    public void setX(){
        x=coordonate.getI();
    }
    public void setY(){ y=coordonate.getJ(); }
    public int returnThreadX(){
        return x;
    }
    public int returnThreadY(){
        return y;
    }
}
