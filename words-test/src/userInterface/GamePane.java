package userInterface;

import util.PrepareToStart;
import util.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePane extends JPanel {
    int width = PrepareToStart.WIDTH;
    int hight = PrepareToStart.HIGHT;
    MainWindow mainWindow;
    public GamePanel gamePanel;
    public InputPane inputPane;
    public ScorePane scorePane;
    public AnanysisPane ananysisPane;
    public static int X;
    boolean changed = false;

    public GamePane(MainWindow mainWindow) {
        super();
        this.mainWindow = mainWindow;
        setBounds(0, 0, width, hight);
        setLayout(null);

        //各个panel以及他们对应的位置
        gamePanel = new GamePanel();
        add(gamePanel);

        inputPane = new InputPane();
        add(inputPane);

        ananysisPane = new AnanysisPane();
        add(ananysisPane);

        scorePane = new ScorePane();
        add(scorePane);

    }

    public AnanysisPane getAnanysisPane() {
        return ananysisPane;
    }

    class GamePanel extends JPanel {
        public GamePanel() {
            setBackground(new Color(0xFABEF1FF, true));
            setVisible(true);
            setLayout(null);
            setBounds(0, 0, 950, hight - 200);
        }
    }

    class InputPane extends JPanel {
        JTextField input = new JTextField();

        public InputPane() {
            setBounds(0, hight - 200, 950, 200);
            add(input);
            setLayout(null);
            input.setBounds(240, 85, 500, 50);
            input.setFont(new Font("Arial", Font.PLAIN, 25));

            input.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    try {
                        int key = e.getKeyCode();
                        String a = input.getText();
                        if (key == KeyEvent.VK_ENTER && !GameData.words.isEmpty() && !a.trim().equals("")) {
                            String answer = GameData.inputWords.take();
                            GameData.nowWord = answer;
                            GameData.nowWrongWord = a;
                            if (a.equals(GameData.words.peek().getWord())) {
                                mainWindow.game.setLeftWords(mainWindow.game.getLeftWords() - 1);
                                mainWindow.game.setScore(mainWindow.game.getScore() + GameData.words.peek().scoreOfWord);;//SCOREPANE的得分在这里修改
                                scorePane.scoreOutput.setText("当前得分:" + mainWindow.game.getScore());
                                GameData.hunter.setNextX(GameData.words.peek().X);
                                (new Thread(GameData.hunter)).start();//移动
                                GameData.words.take().setStatus(true);
                                GameData.threads.take();
                            } else {
                                ananysisPane.re += "正确答案为：" + GameData.words.peek().getWord() +" " + "您输入的答案为："+ a + " ";
                                ananysisPane.re += analy(GameData.words.peek().getWord(),a) +"\n";
                                ananysisPane.result.setText(ananysisPane.re);
                                changed = true;

                                mainWindow.game.setLeftWords(mainWindow.game.getLeftWords() - 1);
                                GameData.words.take().setStatus(false);
                                GameData.threads.take();
                            }
                            input.setText(null);
                        } else if (key == KeyEvent.VK_SPACE) {
                            input.setText(null);
                        }
                    } catch (Exception E) {
                        E.printStackTrace();
                    }
                }
            });
        }

        public String analy(String answer,String input){
            String result1 = "错误原因:";
            if(answer.length()==input.length())
            {
               result1 += "单词长度一致，但部分字母不一致";
            }
            else if(answer.length() < input.length())
                result1 += "输入字母个数比正确答案还要多！";
            else
                result1 += "输入字母个数比正确答案还要少！";
            return  result1;
        }
    }
    


    class AnanysisPane extends JScrollPane {
        public String re = "分析页面:\n";
        JTextArea result;
        public AnanysisPane() {
            super();
            result = new JTextArea();
            result.setBounds(0,0,250, hight - 300);
            result.setWrapStyleWord(true);
            result.setEditable(false);
            setViewportView(result);
            result.setText(re);
            setBounds(width - 250, 0, 250, hight - 300);
        }
    }


    class ScorePane extends JPanel {
        JLabel scoreOutput;
        public ScorePane() {
            super();
            setBackground(new Color(0xDDFFD0));
            setBounds(950, hight - 300, 250, 300);
            scoreOutput = new JLabel("当前得分:" + mainWindow.game.getScore(), SwingConstants.CENTER);
            scoreOutput.setBounds(0, 100, 250, 60);
            scoreOutput.setForeground(Color.orange);
            scoreOutput.setFont(new Font("黑体", Font.BOLD, 16));
            setLayout(null);
            add(scoreOutput);

        }
    }


}
