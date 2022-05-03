import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Main {
    static int WindowLeftUpXCoordinate = 0;
    static int WindowLeftUpYCoordinate = 0;
    static int WindowWidth = 0;
    static int WindowHeight = 0;
    static int x = 0;
    static int y = 0;
    static JLabel tipsJLabel = new JLabel("",JLabel.CENTER);
    static boolean stop = false;
    public static void main(String[] args) {
        JFrame jf = new JFrame("Script");
        jf.setSize(500,200);
        jf.setLayout(null);
        tipsJLabel.setBounds(0,50,500,15);
        tipsJLabel.setFont(new Font("等线",Font.PLAIN,14));
        tipsJLabel.setText("先选择窗口再启动");
        jf.add(tipsJLabel);
        JButton getWindowDataJButton = new JButton("选择窗口");
        getWindowDataJButton.setBounds(75,100,100,50);
        getWindowDataJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipsJLabel.setText("请在3秒内把鼠标放到指定窗口内");
                GetWindowDataThread GetWindowData = new GetWindowDataThread();
                GetWindowData.start();
            }
        });
        jf.add(getWindowDataJButton);
        JButton startJButton = new JButton("启动");
        startJButton.setBounds(200,100,100,50);
        startJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(WindowWidth > 0 && WindowHeight > 0){
                    MainThread Main = new MainThread();
                    Main.start();
                }else{
                    tipsJLabel.setText("请先选择窗口");
                }
            }
        });
        jf.add(startJButton);
        JButton stopJButton = new JButton("停止");
        stopJButton.setBounds(325,100,100,50);
        stopJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop = true;
                tipsJLabel.setText("已发送停止指令（出于安全性考虑，可能不会立刻停止）");
            }
        });
        jf.add(stopJButton);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}