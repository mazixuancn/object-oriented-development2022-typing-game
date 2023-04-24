package util;


import userInterface.MainWindow;

import javax.swing.*;
import java.io.IOException;


public class PrepareToStart {

    public static final int WIDTH=1200;//窗口宽度
    public static final int HIGHT=720;//窗口高度

    public static final String title="打字游戏";//标题

    //获取a-b随机数
    public static int getRandomInt(int a,int b){
        int rand=b-a;
        return (int) (Math.random()*10000%rand+a);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {

        new MainWindow(title);
    }
}
