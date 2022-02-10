import java.awt.*;
import java.awt.event.InputEvent;

public class MainThread implements Runnable{
    public void run() {
        //提供两种方式来获得窗口数据
        //通过调用GetWindowData.dll输入窗口的类名和标题来获得窗口数据（不支持特殊字符）
        System.loadLibrary("GetWindowData");
        GetWindowData Window = new GetWindowData();
        int WindowLeftUpXCoordinate = Window.LeftUpXCoordinate("CabinetWClass","此电脑");
        int WindowLeftUpYCoordinate = Window.LeftUpYCoordinate("CabinetWClass","此电脑");
        int WindowWidth = Window.Width("CabinetWClass","计算机");
        int WindowHeight = Window.Height("CabinetWClass","计算机");
        //通过调用GetMousePositionWindowData.dll并将鼠标放在窗口上来获得窗口数据
//        System.loadLibrary("GetMousePositionWindowData");
//        GetMousePositionWindowData Window = new GetMousePositionWindowData();
//        //3秒后获得窗口数据
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        int WindowLeftUpXCoordinate = Window.LeftUpXCoordinate();
//        int WindowLeftUpYCoordinate = Window.LeftUpYCoordinate();
//        int WindowWidth = Window.Width();
//        int WindowHeight = Window.Height();
        System.out.println("WindowLeftUpXCoordinate:"+WindowLeftUpXCoordinate);
        System.out.println("WindowLeftUpYCoordinate:"+WindowLeftUpYCoordinate);
        System.out.println("WindowWidth:"+WindowWidth);
        System.out.println("WindowHeight:"+WindowHeight);
        //两种操作方式 一是通过java自带的robot来模拟操作，二是通过调用dll来模拟
        //请注意，无论使用哪种方式进行模拟操作，在操作通过管理员身份打开的窗口时都需要用管理员方式打开IDE/jar
        //一些特殊的程序会屏蔽此类模拟操作（游戏）
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.setAutoDelay(200);
        //System.loadLibrary("LeftMouseButtonOperation");
        //LeftMouseButtonOperation LeftMouseButton = new LeftMouseButtonOperation();
        FindImage FindImage = new FindImage();
        while (true){
            //输入参数，分别为 查找区域的左上角X坐标 查找区域的左上角Y坐标 查找区域的右下角X坐标 查找区域的右下角Y坐标 目标图片的路径 精确度（0-1） 精确度越高越准确
            FindImage.findImage(WindowLeftUpXCoordinate, WindowLeftUpYCoordinate, WindowWidth, WindowHeight,"img\\key.png", 0.8);
            if(MainProgram.x>0 && MainProgram.y>0){
                if(robot!=null){
                    //鼠标移动到图片的位置
                    robot.mouseMove(MainProgram.x + WindowLeftUpXCoordinate,MainProgram.y + WindowLeftUpYCoordinate);
                    //左键双击
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //LeftMouseButton.Press();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //LeftMouseButton.Release();
                    robot.mouseMove(MainProgram.x + WindowLeftUpXCoordinate + 100,MainProgram.y + WindowLeftUpYCoordinate);
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
