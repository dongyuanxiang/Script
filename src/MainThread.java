import java.awt.*;
import java.awt.event.InputEvent;

public class MainThread extends Thread{
    public void run() {
        Main.tipsJLabel.setText("线程已启动");
        //通过java自带的robot来模拟操作
        //请注意，在操作通过管理员身份打开的窗口时需要用管理员方式打开IDE/jar
        //一些特殊的程序会屏蔽此类模拟操作（例如安全防护级别高的游戏）
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        FindImage FindImage = new FindImage();
        while (true){
            Main.tipsJLabel.setText("线程正在运行");
            //检测线程是否被标记为停止
            if(Main.stop){
                Main.stop = false;
                Main.tipsJLabel.setText("已停止");
                return;
            }
            //输入参数，分别为 查找区域的左上角X坐标 查找区域的左上角Y坐标 查找区域的右下角X坐标 查找区域的右下角Y坐标 目标图片的路径 精确度（0.00-1.00） 精确度越高越准确
            //这里因为是在目标窗口的区域中寻找而不是全屏寻找，所以要输入目标窗口相对于屏幕的位置，如果您想再整个屏幕中寻找目标图片，那么请输入您的屏幕的分辨率 例如0,0,1920,1080
            FindImage.findImage(Main.WindowLeftUpXCoordinate,Main.WindowLeftUpYCoordinate,Main.WindowWidth,Main.WindowHeight,"img\\key.png", 0.8);
            if(Main.x>0 && Main.y>0){
                if(robot!=null){
                    Main.tipsJLabel.setText("正在执行操作：左键单击");
                    //鼠标移动到图片的位置
                    //这里因为是用的程序的数据查早到的结果，所以在用的时候要加上程序相对于屏幕的坐标，如果您是用的全屏寻找，则不用进行这一步
                    robot.mouseMove(Main.x + Main.WindowLeftUpXCoordinate, Main.y + Main.WindowLeftUpYCoordinate);
                    //左键单击
                    robot.mousePress(InputEvent.BUTTON1_MASK);//左键按下
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);//左键松开
                    //操作完成后暂停200毫秒，防止出现bug
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //防止因为鼠标遮挡图片导致的无法识别，所以在操作执行完成之后将鼠标移动到窗口的右下角
                    Main.tipsJLabel.setText("正在将鼠标移动到窗口的右下角");
                    robot.mouseMove(Main.WindowLeftUpXCoordinate + Main.WindowWidth,Main.WindowLeftUpYCoordinate + Main.WindowHeight);
                }
            }
            try {
                if(Main.x > 0 && Main.y > 0){
                    Main.tipsJLabel.setText("本次线程成功完成，等待下一次执行");
                }else{
                    Main.tipsJLabel.setText("未找到目标图片,等待下一次执行");
                }
                //每次线程运行完之后休息一段时间，防止出现一些奇奇怪怪的bug，并降低负载
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
