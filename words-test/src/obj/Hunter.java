package obj;

import javax.swing.*;

public class Hunter extends JLabel implements Runnable{
    int currentX;
    int nextX;
    public Hunter(){
        currentX=420;
        ImageIcon icon = new ImageIcon("image/plane.png");
        setIcon(icon);
        setVisible(true);
        setBounds(currentX,0,50,50);
    }

   public void mov(int x) throws InterruptedException {
        int movSpeed=2;
        if(currentX<x){
            while (currentX<x){
                currentX+=2;
                setBounds(currentX,0,50,50);
                Thread.sleep(movSpeed);
            }
        }else {
            while (currentX>x){
                currentX-=2;
                setBounds(currentX,0,50,50);
                Thread.sleep(movSpeed);
            }
        }
   }

    @Override
    public void run() {
        try {
            mov(nextX);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setNextX(int nextX) {
        this.nextX = nextX;
    }
}
