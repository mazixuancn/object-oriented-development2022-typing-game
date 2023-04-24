package userInterface;

import obj.Rank;
import util.GameData;
import util.PrepareToStart;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;

public class MainWindow extends JFrame {
    int width = PrepareToStart.WIDTH;
    int hight = PrepareToStart.HIGHT;
    GameData game;
    MenuPane menuP;
    GamePane gameP;
    GameOver overP;
    DifficultyChoose diffDialog;
    RankingDialog rankDialog;
    Rank rank;

    public MainWindow(String title) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        //基础的设置
        super(title);
        getRank();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 150, width, hight);
        setLayout(null);
        setVisible(true);
        menuP = new MenuPane(this);
        add(menuP);
        repaint();//刷新

    }

    private void getRank() throws IOException, ClassNotFoundException {
       ObjectInputStream objIn=new ObjectInputStream(new FileInputStream("rank.sav"));
       rank=(Rank) objIn.readObject();
        objIn.close();
    }


    //开始游戏
    public void gameStart(int difficulty, File txt) throws IOException {

        game=new GameData(difficulty,txt);
        gameP = new GamePane(this);
        game.setJp(gameP.gamePanel);
        game.setJpOfHunter(gameP.inputPane);//设置Hunter显示在哪个位置上
        game.setMainWindow(this);
        game.runGame();
        if(overP==null)remove(menuP);
        else remove(overP);
        add(gameP);
        repaint();
    }


    //显示排行榜
    public void showRanking() throws IOException, ClassNotFoundException {
        rankDialog=new RankingDialog(this);

    }

    public void chooseDifficalty(){

        diffDialog=new DifficultyChoose(this);
    }
    public void gameOver() throws IOException, ClassNotFoundException {

        overP=new GameOver(this,game);
        remove(gameP);
        add(overP);
        repaint();
    }

    //退出
    public void showClose() {
        int flag=JOptionPane.showConfirmDialog(this,"是否退出游戏？","退出",JOptionPane.YES_NO_OPTION);
        if(flag==0){
            System.exit(0);
        }
    }
}

