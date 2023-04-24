package obj;

import util.PrepareToStart;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

import static java.lang.Thread.sleep;


public class Word extends JLabel implements Runnable {
    static int numberOfThread = 0;
    public String word;//word为显示的字符
    int x1,y1,x2,y2;//从起始位置(x1,y1),移动到目标位置(x2,y2)
    int status = 1;//表示单词的状态;1:正常,2:输入正确,3:输入错误
    int difficulty,dropSpeed;
    public boolean isDropOut = false;
    public int X;//显示单词的横坐标位置
    public int scoreOfWord;


    public Word(int difficulty,String word,int x1,int y1,int x2,int y2)
    {
        this.word = word;
        this.X = x1;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.difficulty = difficulty;
        changeDiffcult();
        setText(word);
        setFont(new Font("Arial", Font.PLAIN, 25));
        numberOfThread++;
        setScoreOfWord();
    }

    private void changeDiffcult(){
        if(difficulty == 1)
            dropSpeed = 20;
        else if(difficulty==2)
            dropSpeed = 15;
        else dropSpeed = 10;
    }

    public String getWord() {
        return word;
    }

    //该方法控制单词下落的位置，从起始位置(x1,y1),移动到目标位置(x2,y2)
    public void move() throws InterruptedException, MalformedURLException {

        while (y1<y2)
        {
            setBounds(x1,y1,300,100);
            sleep(dropSpeed);
            y1++;

            //判断单词状态
            if(status == 3) {
                setForeground(Color.red);
                y1++;
                dropSpeed = 1;
            }
            else if(status == 2){
                sleep(500);
                destroy();
            }

        }
        isDropOut = true;
        numberOfThread--;
    }

    public void run(){
        try {
            move();
        } catch (InterruptedException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //击中单词后的毁灭方法
    public void destroy() throws InterruptedException, MalformedURLException {
        setText(null);
        setIcon(new ImageIcon("image/boom.png"));

        sleep(500);
        y1 = y2;
        setBounds(x1,y1+1000,0,0);
    }

    public void setStatus(boolean input){
        if(input)
            status = 2;
        else
            status = 3;
    }

    public void setScoreOfWord() {
        this.scoreOfWord = 2;
        // TODO: 2021/4/11
    }
}
