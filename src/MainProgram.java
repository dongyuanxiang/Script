import java.awt.*;
import java.awt.event.*;
public class MainProgram {
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) {
        MainThread Main = new MainThread();
        Thread main = new Thread(Main);
        main.start();
    }
}