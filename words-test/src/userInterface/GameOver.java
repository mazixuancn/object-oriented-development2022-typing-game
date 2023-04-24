package userInterface;

import obj.Rank;
import util.GameData;
import util.PrepareToStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GameOver extends JPanel implements ActionListener {
    Rank rank;
    GameData game;
    MainWindow mainWindow;
    int width= PrepareToStart.WIDTH;
    int hight=PrepareToStart.HIGHT;
    JLabel titleL,scoreL;
    JPanel trans;
    JButton reStratB,RankingB,ExitB;
    int menuY;
    GamePane.AnanysisPane ananysisPane;

    public GameOver(MainWindow mainWindow,GameData game) throws IOException, ClassNotFoundException {
        super();
        this.game=game;
        this.mainWindow=mainWindow;
        ananysisPane=mainWindow.gameP.getAnanysisPane();
        menuY=300;
        this.rank=mainWindow.rank;
        saveScore(game.getScore());
        setBounds(0,0,width,hight);

        //↓元素创建
        trans=new JPanel();
        trans.setBackground(new Color(0,0,0,50));
        trans.setBounds(440,0,320,720);

        titleL=new JLabel("游戏结束");
        titleL.setFont(new Font("宋体",1,60));
        titleL.setBounds(width/2-130,menuY-180,300,70);

        scoreL=new JLabel("最终得分："+game.getScore(),SwingConstants.CENTER);
        scoreL.setForeground(Color.orange);
        scoreL.setFont(new Font("黑体", Font.BOLD, 16));
        scoreL.setBounds(0,menuY-110,1200,20);

        reStratB =new JButton("重新开始");
        reStratB.setBounds(width/2-55,menuY,100,50);

        RankingB=new JButton("排行榜");
        RankingB.setBounds(width/2-55,menuY+100,100,50);

        ExitB=new JButton("退出");
        ExitB.setBounds(width/2-55,menuY+200,100,50);

        ananysisPane.setBounds(20,20,ananysisPane.getWidth(),ananysisPane.getHeight());
        add(reStratB);add(RankingB);add(ExitB);add(titleL);add(trans);add(ananysisPane);add(scoreL);
        reStratB.addActionListener(this);
        RankingB.addActionListener(this);
        ExitB.addActionListener(this);
    }

    private void saveScore(int scroe) throws IOException, ClassNotFoundException {
        ObjectOutputStream objOut=new ObjectOutputStream(new FileOutputStream("rank.sav"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        rank.addRank(scroe,df.format(new Date()));
        objOut.writeObject(rank);
        objOut.flush();

        objOut.close();
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
