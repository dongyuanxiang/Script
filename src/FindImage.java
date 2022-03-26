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
    //用于处理目标图片中会影响到结果的背景颜色
    int BackgroundColorData;
    int BackgroundColorCount;
    //计数器
    int Count = 0;

    public void findImage(int LeftUpX,int LeftUpY,int RightDownX,int RightDownY,String KeyImagePath,double Accuracy) {
        ScreenShotImage = this.getScreenShot(LeftUpX,LeftUpY,RightDownX,RightDownY);
        KeyImage = this.getBufferedImageFromPath(KeyImagePath);
        ScreenShotImageData = this.getImageRGB(ScreenShotImage);
        ScreenShotImageWidth = ScreenShotImage.getWidth();
        ScreenShotImageHeight = ScreenShotImage.getHeight();
        KeyImageData = this.getImageRGB(KeyImage);
        KeyImageWidth = KeyImage.getWidth();
        KeyImageHeight = KeyImage.getHeight();
        if(KeyImageData[0][0] == KeyImageData[0][KeyImageWidth-1] && KeyImageData[0][KeyImageWidth-1] == KeyImageData[KeyImageHeight-1][0] && KeyImageData[KeyImageHeight-1][0] == KeyImageData[KeyImageHeight-1][KeyImageWidth-1]){
            System.out.println("目标图片四角颜色相等，将其判定为背景颜色，防止对结果产生干扰");
            BackgroundColorData = KeyImageData[0][0];
        }
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
                //-100000 < RGBData < 100000 的意思是比较两个像素点RGB数据时如果小于这个差值则视为相同
                //判断左上角
                if(KeyImageData[0][0] - ScreenShotImageData[y][x] < 1000 && KeyImageData[0][0] - ScreenShotImageData[y][x] > -1000) {
                    //判断右上角
                    if(KeyImageData[0][KeyImageWidth-1] - ScreenShotImageData[y][x+KeyImageWidth-1] < 1000 && KeyImageData[0][KeyImageWidth-1] - ScreenShotImageData[y][x+KeyImageWidth-1] > -1000) {
                        //判断左下角
                        if(KeyImageData[KeyImageHeight-1][0] - ScreenShotImageData[y+KeyImageHeight-1][x] < 1000 && KeyImageData[KeyImageHeight-1][0] - ScreenShotImageData[y+KeyImageHeight-1][x] > -1000) {
                            //判断右下角
                            if(KeyImageData[KeyImageHeight-1][KeyImageWidth-1] - ScreenShotImageData[y+KeyImageHeight-1][x+KeyImageWidth-1] < 1000 && KeyImageData[KeyImageHeight-1][KeyImageWidth-1] - ScreenShotImageData[y+KeyImageHeight-1][x+KeyImageWidth-1] > -1000) {
                                //如果四个角都相同，那么判断每一个像素点
                                for(int a = 0;a < KeyImageHeight;a++) {
                                    for(int b = 0;b < KeyImageWidth;b++) {
                                        if(KeyImageData[a][b] - ScreenShotImageData[y+a][x+b] < 1000 && KeyImageData[a][b] - ScreenShotImageData[y+a][x+b] > -1000) {
                                            //如果对比成功的这一个像素的颜色与背景颜色相同则忽略
                                            if(KeyImageData[a][b] == BackgroundColorData){
                                                BackgroundColorCount ++;
                                            }else{
                                                Count ++;
                                            }
                                        }
                                    }
                                }
                                double i = Count;
                                //在总像素中减去背景颜色所占用的像素
                                double j = (double)KeyImageHeight*(double)KeyImageWidth - (double)BackgroundColorCount;
                                if((i/j) > Accuracy) {
                                    MainProgram.x = x;
                                    MainProgram.y = y;
                                    System.out.println("成功，匹配率为：" + (i/j));
                                    MainProgram.jl1.setText("成功找到目标图片");
                                    Count = 0;
                                    BackgroundColorCount = 0;
                                    return;
                                }else {
                                    System.out.println("失败，匹配率为：" + (i/j));
                                    Count = 0;
                                    BackgroundColorCount = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("失败");
        MainProgram.x = -1;
        MainProgram.y = -1;
    }
}