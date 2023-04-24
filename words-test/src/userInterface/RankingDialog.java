package userInterface;

import javafx.util.Pair;
import obj.Rank;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class RankingDialog extends JDialog {
    Rank rank;
    public RankingDialog(MainWindow mainWindow) throws IOException, ClassNotFoundException {
        super(mainWindow,"排行榜");
        this.rank=mainWindow.rank;
        setLocationRelativeTo(mainWindow);
        setSize(300,400);
        setResizable(false);
        setLayout(new GridLayout(10,1));
        setUI();
        setVisible(true);
    }

    private void setUI() {
        for(int i=0;i<10;i++){
            if(i<rank.getRankList().size()){
                JPanel panel=new JPanel();

                JLabel list=new JLabel("第"+(i+1)+"名:"+
                        "\t 得分:"+rank.getRankList().get(i).getKey()+
                        "时间:"+rank.getRankList().get(i).getValue(),SwingConstants.CENTER);
                list.setFont(new Font("微软雅黑",0,14));
                list.setSize(300,30);
                add(list);
            }else {
                JLabel list=new JLabel("第"+(i+1)+"名:未上榜",SwingConstants.CENTER);
                list.setFont(new Font("微软雅黑",0,14));
                list.setSize(300,30);
                add(list);
            }

        }
    }
}
