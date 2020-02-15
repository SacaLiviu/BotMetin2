package sample;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class GlobalMouseListener implements NativeMouseInputListener {
    int x=0,y=0,MouseState=0;

    public void nativeMousePressed(NativeMouseEvent e) {

    }
    public void nativeMouseClicked(NativeMouseEvent e) {
        x=e.getX();
        y=e.getY();
        MouseState=1;
    }
    public void nativeMouseReleased(NativeMouseEvent e) {
       // System.out.println("Mouse Released: " + e.getButton());
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        //System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
       // System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
    }

    public int getX(){
        return x;
    }


    public int getY() {
        return y;
    }

    public int getMouseState() {
        return MouseState;
    }
    public void Resetxy(){
        x=0;
        y=0;
        MouseState=0;
    }
}