package userInterface;

import util.PrepareToStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MenuPane extends JPanel implements ActionListener {
    int width= PrepareToStart.WIDTH;
    int hight=PrepareToStart.HIGHT;
    JLabel overL;
    JPanel trans;
    JButton reStratB,RankingB,ExitB;
    MainWindow mainWindow;
    int menuY;

    public MenuPane(MainWindow mainWindow) {
        super(null);
        this.mainWindow=mainWindow;
        menuY=300;
        setBounds(0,0,width,hight);

        trans=new JPanel();
        trans.setBackground(new Color(0,0,0,50));
        trans.setBounds(440,0,320,720);

        overL =new JLabel("打字游戏");
        overL.setFont(new Font("宋体", Font.BOLD,60));
        overL.setBounds(width/2-130,menuY-180,300,80);


        reStratB =new JButton("开始游戏");
        reStratB.setBounds(width/2-55,menuY,100,50);

        RankingB=new JButton("排行榜");
        RankingB.setBounds(width/2-55,menuY+100,100,50);

        ExitB=new JButton("退出");
        ExitB.setBounds(width/2-55,menuY+200,100,50);

        add(reStratB);add(RankingB);add(ExitB);add(overL);add(trans);
        reStratB.addActionListener(this);
        RankingB.addActionListener(this);
        ExitB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button=e.getSource();
        try {
            if(Button== reStratB){
                mainWindow.chooseDifficalty();
            }else if(Button==RankingB){
                mainWindow.showRanking();
            }else if(Button==ExitB){
                mainWindow.showClose();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}
