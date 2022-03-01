import java.awt.*;
import java.awt.event.InputEvent;

public class MainThread extends Thread{
    public void run() {
        //目前提供了两种方式来获得窗口数据，当然您也可以选择跳过这一步，直接在整个屏幕中寻找目标图片，只不过在效率上可能会有些影响
        // 一 通过调用GetWindowData.dll 输入窗口的类名和标题来获得窗口数据（不支持特殊字符），而且目前不太稳定，推荐使用第二种
//        System.loadLibrary("GetWindowData");
//        GetWindowData Window = new GetWindowData();
//        int WindowLeftUpXCoordinate = Window.LeftUpXCoordinate("CabinetWClass","此电脑");
//        int WindowLeftUpYCoordinate = Window.LeftUpYCoordinate("CabinetWClass","此电脑");
//        int WindowWidth = Window.Width("CabinetWClass","计算机");
//        int WindowHeight = Window.Height("CabinetWClass","计算机");
        // 二 通过调用GetMousePositionWindowData.dll并将鼠标放在窗口上来获得窗口数据
        System.loadLibrary("GetMousePositionWindowData");
        GetMousePositionWindowData Window = new GetMousePositionWindowData();
        //3秒后获得鼠标所在位置的窗口的数据
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int WindowLeftUpXCoordinate = Window.LeftUpXCoordinate();
        int WindowLeftUpYCoordinate = Window.LeftUpYCoordinate();
        int WindowWidth = Window.Width();
        int WindowHeight = Window.Height();
        System.out.println("WindowLeftUpXCoordinate:"+WindowLeftUpXCoordinate);
        System.out.println("WindowLeftUpYCoordinate:"+WindowLeftUpYCoordinate);
        System.out.println("WindowWidth:"+WindowWidth);
        System.out.println("WindowHeight:"+WindowHeight);
        //通过java自带的robot来模拟操作
        //请注意，在操作通过管理员身份打开的窗口时需要用管理员方式打开IDE
        //一些特殊的程序会屏蔽此类模拟操作（安全防护级别高的游戏）
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.setAutoDelay(200);
        FindImage FindImage = new FindImage();
        while (true){
            if(MainProgram.stop){
                MainProgram.stop = false;
                return;
            }
            //输入参数，分别为 查找区域的左上角X坐标 查找区域的左上角Y坐标 查找区域的右下角X坐标 查找区域的右下角Y坐标 目标图片的路径 精确度（0-1） 精确度越高越准确
            //这里因为是在目标程序的屏幕区域中寻找而不是全屏寻找，所以要输入从dll中获取的数据，如果您想再整个屏幕中寻找目标图片，那么请输入您的屏幕的分辨率 例如0,0,1920,1080
            FindImage.findImage(WindowLeftUpXCoordinate, WindowLeftUpYCoordinate, WindowWidth, WindowHeight,"img\\key.png", 0.8);
            if(MainProgram.x>0 && MainProgram.y>0){
                if(robot!=null){
                    //鼠标移动到图片的位置
                    //这里因为是用的程序的数据查早到的结果，所以在用的时候要加上程序相对于屏幕的坐标，如果您是用的全屏寻找，则不用进行这一步
                    robot.mouseMove(MainProgram.x + WindowLeftUpXCoordinate,MainProgram.y + WindowLeftUpYCoordinate);
                    //左键双击
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
