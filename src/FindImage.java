import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class FindImage {
    //创建两个BufferedImage对象，分别用来存储截屏和要找的图
    BufferedImage ScreenShotImage;
    BufferedImage KeyImage;
    //屏幕截图的宽高
    int ScreenShotImageWidth;
    int ScreenShotImageHeight;
    //目标文件的宽高
    int KeyImageWidth;
    int KeyImageHeight;
    //屏幕截图的数据
    int [] [] ScreenShotImageData;
    //目标图片的数据
    int [] [] KeyImageData;

    //计数器
    int Count = 0;

    public void findImage(int LeftUpX,int LeftUpY,int RightDownX,int RightDownY,String KeyImagePath,double Accuracy) {
        ScreenShotImage = this.getScreenShot(LeftUpX,LeftUpY,RightDownX,RightDownY);
        KeyImage = this.getBufferedImageFromPath(KeyImagePath);
        ScreenShotImageData = this.getImageRGB(ScreenShotImage);
        KeyImageData = this.getImageRGB(KeyImage);
        ScreenShotImageWidth = ScreenShotImage.getWidth();
        ScreenShotImageHeight = ScreenShotImage.getHeight();
        KeyImageWidth = KeyImage.getWidth();
        KeyImageHeight = KeyImage.getHeight();
        Find(Accuracy);
    }
    public BufferedImage getScreenShot(int LeftUpX,int LeftUpY,int RightDownX,int RightDownY) {
        BufferedImage BufferedImage = null;
        try {
            Robot Robot = new Robot();
            BufferedImage = Robot.createScreenCapture(new Rectangle(LeftUpX,LeftUpY,RightDownX,RightDownY));
            //写出文件到本地。如果不需要可以注释掉，对程序没有影响
            File Temp = new File("C:\\Temp");
            if(!Temp.exists()) {
                if(Temp.mkdir()){
                    System.out.println("未找到路径，已自动创建");
                }else{
                    System.out.println("未找到目录，且未能创建目录");
                }
            }else {
                System.out.println("路径已存在");
            }
            ImageIO.write(BufferedImage, "png", new File("C:\\Temp\\Temp.png"));
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
        return BufferedImage;
    }
    public BufferedImage getBufferedImageFromPath(String KeyImagePath) {
        BufferedImage BufferedImage = null;
        try {
            BufferedImage = ImageIO.read(new File(KeyImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BufferedImage;
    }
    public int [] [] getImageRGB(BufferedImage BufferedImage){
        int Width = BufferedImage.getWidth();
        int Height = BufferedImage.getHeight();
        int [] [] Result = new int [Height][Width];
        for(int h = 0;h < Height;h++) {
            for(int w = 0;w < Width;w++) {
                Result [h] [w] = BufferedImage.getRGB(w,h)+16777216;
            }
        }
        return Result;
    }
    public void Find (double Accuracy) {
        for(int y = 0;y < ScreenShotImageHeight-KeyImageHeight;y++) {
            for(int x = 0;x < ScreenShotImageWidth-KeyImageWidth;x++) {
                //判断左上角
                if(KeyImageData[0][0]== ScreenShotImageData[y][x]) {
                    //判断右上角
                    if(KeyImageData[0][KeyImageWidth-1]== ScreenShotImageData[y][x+KeyImageWidth-1]) {
                        //判断左下角
                        if(KeyImageData[KeyImageHeight-1][0]== ScreenShotImageData[y+KeyImageHeight-1][x]) {
                            //判断右下角
                            if(KeyImageData[KeyImageHeight-1][KeyImageWidth-1]== ScreenShotImageData[y+KeyImageHeight-1][x+KeyImageWidth-1]) {
                                //如果四个角都相同，那么判断每一个像素点
                                for(int a = 0;a < KeyImageHeight;a++) {
                                    for(int b = 0;b < KeyImageWidth;b++) {
                                        if(KeyImageData[a][b]== ScreenShotImageData[y+a][x+b]) {
                                            Count ++;
                                        }
                                    }
                                }
                                double i = Count;
                                double j = (double)KeyImageHeight*(double)KeyImageWidth;
                                if((i/j) > Accuracy) {
                                    MainProgram.x = x;
                                    MainProgram.y = y;
                                    return;
                                }else {
                                    Count = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("失败");
    }
}