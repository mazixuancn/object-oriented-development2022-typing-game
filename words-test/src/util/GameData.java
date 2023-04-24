package util;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import obj.Hunter;
import obj.Word;
import tool.TxtCuter;
import userInterface.MainWindow;

import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

import static util.PrepareToStart.getRandomInt;



public class GameData {
    int score;
    int difficulty;//输入难度分别为1，2，3对应简单、中等、困难
    String[] strArray;
    JPanel jp, jpOfHunter;
    int dropSpace;
    File txt;
    int leftWords;
    MainWindow mainWindow;
    public static ArrayBlockingQueue<Thread> threads = new ArrayBlockingQueue<>(4, true);
    public static ArrayBlockingQueue<Word> words = new ArrayBlockingQueue<>(4, true);
    public static ArrayBlockingQueue<String> inputWords = new ArrayBlockingQueue<>(4, true);//该队列可以取出当前正在处理的单词
    public static Hunter hunter;
    public static String nowWord;//当前正在处理的单词 todo
    public static String nowWrongWord;


    public GameData(int difficulty, File txt) throws IOException {
        this.difficulty = difficulty;
        this.txt = txt;
        this.score=0;
        txtToString();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public int getLeftWords() {
        return leftWords;
    }

    public void setLeftWords(int leftWords) {
        this.leftWords = leftWords;
    }


    private void txtToString() throws IOException {
        TxtCuter cuter = new TxtCuter(txt);
        setStrArray(cuter.getArrey());
    }


    public void setStrArray(String[] strArray) {
        this.strArray = strArray;
        leftWords = strArray.length;
    }


    public void setJp(JPanel jp) {
        this.jp = jp;
    }


    public void setJpOfHunter(JPanel jp) {
        this.jpOfHunter = jp;
    }


    public void runGame() {
        changeDiffcult();//根据难度改变单词下落间隔
        Thread work = new Work(strArray, strArray.length);
        work.start();//不断往阻塞队列放入单词和线程对象的线程
        Thread isDropOut = new isDropOut();
        isDropOut.start();//监听是否有单词调出屏幕外的线程
        hunter = new Hunter();
        jpOfHunter.add(hunter);
    }


    private void changeDiffcult() {
        if (difficulty == 1)
            dropSpace = 2000;
        else if (difficulty == 2)
            dropSpace = 1500;
        else dropSpace = 1500;
    }


    class Work extends Thread {
        String[] strArray;
        int n;

        Work(String[] strArray, int n) {
            this.strArray = strArray;
            this.n = n;
        }

        public void run() {
            int i = 0;
            while (i < n) {
                try {
                    int a = getRandomInt(0, 800);
                    Word word = new Word(difficulty, strArray[i], a, 0, a, 480);
                    jp.add(word);
                    Thread t = new Thread(word);
                    t.start();
                    words.put(word);
                    threads.put(t);
                    inputWords.put(strArray[i]);
                    sleep(dropSpace);
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class isDropOut extends Thread {
        public isDropOut() {
        }

        public void run() {
            try {

                while (true) {
                    sleep(1);
                    if (words.isEmpty() && leftWords == 0) {
                        mainWindow.gameOver();
                        break;
                    } else if (!words.isEmpty()) {
                        if (words.peek().isDropOut) {
                            leftWords--;
                            words.take();
                            threads.take();
                            inputWords.take();
                        }
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void Ananysis() {
    }
}

