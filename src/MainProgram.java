import java.awt.*;
import java.awt.event.*;
public class MainProgram {
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) {
        System.loadLibrary("GetWindowData");
        GetWindowData Window = new GetWindowData();
        int WindowLeftUpXCoordinate = Window.LeftUpXCoordinate("CabinetWClass","计算机");
        int WindowLeftUpYCoordinate = Window.LeftUpYCoordinate("CabinetWClass","计算机");
        int WindowWidth = Window.Width("CabinetWClass","计算机");
        int WindowHeight = Window.Height("CabinetWClass","计算机");
        System.out.println("WindowLeftUpXCoordinate:"+WindowLeftUpXCoordinate);
        System.out.println("WindowLeftUpYCoordinate:"+WindowLeftUpYCoordinate);
        System.out.println("WindowWidth:"+WindowWidth);
        System.out.println("WindowHeight:"+WindowHeight);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        FindImage FindImage = new FindImage();
        //输入参数，分别为 查找区域的左上角X坐标 查找区域的左上角Y坐标 查找区域的右下角X坐标 查找区域的右下角Y坐标 目标图片的路径 精确度（0-1） 精确度越高越准确
        FindImage.findImage(WindowLeftUpXCoordinate, WindowLeftUpYCoordinate, WindowWidth, WindowHeight,"img\\key.png", 0.8);
        System.out.println("目标图片相对于截图区域的坐标:"+ x + "," + y);
        if(x>0 && y>0){
            if(robot!=null){
                //鼠标移动到图片的位置
                robot.mouseMove(x + WindowLeftUpXCoordinate,y + WindowLeftUpYCoordinate);
                //左键单击
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("操作已完成");
            }
        }
    }
}