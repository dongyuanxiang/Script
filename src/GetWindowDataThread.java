public class GetWindowDataThread extends Thread{
    public void run(){
        //通过调用GetMousePositionWindowData.dll并将鼠标放在窗口上来获得窗口数据
        System.loadLibrary("GetMousePositionWindowData");
        GetMousePositionWindowData Window = new GetMousePositionWindowData();
        //3秒后获得鼠标所在位置的窗口的数据
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //通过调用dll来获得窗口的数据
        Main.WindowLeftUpXCoordinate = Window.LeftUpXCoordinate();
        Main.WindowLeftUpYCoordinate = Window.LeftUpYCoordinate();
        Main.WindowWidth = Window.Width();
        Main.WindowHeight = Window.Height();
        System.out.println("WindowLeftUpXCoordinate:"+Main.WindowLeftUpXCoordinate);
        System.out.println("WindowLeftUpYCoordinate:"+Main.WindowLeftUpYCoordinate);
        System.out.println("WindowWidth:"+Main.WindowWidth);
        System.out.println("WindowHeight:"+Main.WindowHeight);
        Main.tipsJLabel.setText("鼠标所在窗口的宽为："+Main.WindowWidth+"高为："+Main.WindowHeight);
    }
}
