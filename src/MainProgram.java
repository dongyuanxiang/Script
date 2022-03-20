import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainProgram {
    static int x = 0;
    static int y = 0;
    static JLabel jl1 = new JLabel("",JLabel.CENTER);
    static boolean stop = false;
    public static void main(String[] args) {

        JFrame jf = new JFrame("Script");
        jf.setSize(500,200);
        jf.setLayout(null);
        jl1.setBounds(0,50,500,15);
        jl1.setText("点击启动按钮，并在3秒内把鼠标放到目标窗口内");
        jf.add(jl1);
        JButton jb1 = new JButton("启动");
        jb1.setBounds(75,100,100,50);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainThread Main = new MainThread();
                Thread main = new Thread(Main);
                jl1.setText("在3秒后将获取鼠标所在窗口的数据");
                main.start();
            }
        });
        jf.add(jb1);
        JButton jb2 = new JButton("停止");
        jb2.setBounds(325,100,100,50);
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop = true;
                jl1.setText("已发送停止指令（可能不会立刻停止）");
            }
        });
        jf.add(jb2);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}