import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainProgram {
    static int x = 0;
    static int y = 0;
    static boolean stop = false;
    public static void main(String[] args) {

        JFrame jf = new JFrame("Script");
        jf.setSize(500,200);
        jf.setLayout(null);
        JLabel jl1 = new JLabel("");
        jl1.setBounds(140,50,250,15);

        jf.add(jl1);
        JButton jb1 = new JButton("启动");
        jb1.setBounds(75,100,100,50);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainThread Main = new MainThread();
                Thread main = new Thread(Main);
                jl1.setText("在三秒后将获取鼠标所在窗口的数据");
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
            }
        });
        jf.add(jb2);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}