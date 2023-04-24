package userInterface;

import tool.TxtFileChooser;
import util.PrepareToStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.util.LinkedList;

public class DifficultyChoose extends JDialog implements ActionListener {
    MainWindow mainWindow;
    JButton simpleB,normalB,hardB,fileB;
    JLabel label,fileL;
    JPanel pane;
    TxtFileChooser txtFileChooser;
    File selectedTxt;
    LinkedList<String> words;

    public DifficultyChoose(MainWindow mainWindow)  {
        //界面设置
        super(mainWindow);
        setModal(true);//设置为模式窗口，
        setBounds(750,300,300,400);
        setResizable(false);
        setLayout(null);


        //元素初始化设置
        this.mainWindow=mainWindow;
        words=new LinkedList<>();
        selectedTxt=new File("default.txt");

        pane=new JPanel();
        pane.setBounds(0,0,300,80);
        pane.setBackground(Color.GRAY);
        pane.setLayout(null);

        label=new JLabel("选择难度");
        label.setFont(new Font("宋体",0,32));
        label.setBounds(85,24,200,40);

        simpleB=new JButton("简单");
        simpleB.setBounds(100,100,100,30);

        normalB=new JButton("普通");
        normalB.setBounds(100,160,100,30);

        hardB=new JButton("困难");
        hardB.setBounds(100,220,100,30);

        fileB=new JButton("选择文件");
        fileB.setBounds(100,300,100,30);

        fileL=new JLabel("未选择个人文本",SwingConstants.CENTER);
        fileL.setForeground(Color.red);
        fileL.setBounds(0,260,300,30);
        fileL.setFont(new Font("宋体",1,18));

        add(simpleB);add(normalB);add(hardB);add(fileB);add(fileL);
        pane.add(label);

        simpleB.addActionListener(this);normalB.addActionListener(this);hardB.addActionListener(this);
        fileB.addActionListener(this);

        add(pane);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button=e.getSource();
        try {

            if(Button==simpleB){
                dispose();
                mainWindow.gameStart(1,selectedTxt);//选择简单难度
            }else if(Button==normalB){
                dispose();
                mainWindow.gameStart(2,selectedTxt);//选择普通难度
            }else if(Button==hardB){
                dispose();
                mainWindow.gameStart(3,selectedTxt);//选择困难难度
            }else if(Button==fileB){
                txtFileChooser=new TxtFileChooser();
                if (txtFileChooser.getFlag()==JFileChooser.APPROVE_OPTION){
                    if(txtFileChooser.isTxt()){
                        String txtName=txtFileChooser.getSelectTxt().getName();
                        selectedTxt=txtFileChooser.getSelectTxt();
                        fileL.setText("选择了文本："+txtName.substring(0,txtName.length()-4));
                        fileL.setForeground(new Color(107,194,53));
                    }else {
                        fileL.setText("选择了非文本文件");
                    }
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
