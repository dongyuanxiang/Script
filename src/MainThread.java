import java.awt.*;
import java.awt.event.InputEvent;

public class MainThread implements Runnable{
    public void run() {
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
        robot.setAutoDelay(1000);
        System.loadLibrary("LeftMouseButtonOperation");
        LeftMouseButtonOperation LeftMouseButton = new LeftMouseButtonOperation();
        FindImage FindImage = new FindImage();
        while (true){
            //输入参数，分别为 查找区域的左上角X坐标 查找区域的左上角Y坐标 查找区域的右下角X坐标 查找区域的右下角Y坐标 目标图片的路径 精确度（0-1） 精确度越高越准确
            FindImage.findImage(WindowLeftUpXCoordinate, WindowLeftUpYCoordinate, WindowWidth, WindowHeight,"img\\key.png", 0.8);
            if(MainProgram.x>0 && MainProgram.y>0){
                if(robot!=null){
                    //鼠标移动到图片的位置
                    robot.mouseMove(MainProgram.x + WindowLeftUpXCoordinate,MainProgram.y + WindowLeftUpYCoordinate);
                    //左键双击
//                    robot.mousePress(InputEvent.BUTTON1_MASK);
//                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
//                    robot.mousePress(InputEvent.BUTTON1_MASK);
//                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    LeftMouseButton.Press();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LeftMouseButton.Release();
                    robot.mouseMove(MainProgram.x + WindowLeftUpXCoordinate + 100,MainProgram.y + WindowLeftUpYCoordinate);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
