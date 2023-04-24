package obj;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.LinkedList;


public class Rank implements Serializable {
    private static final long serialVersionUID = 1L;
    private LinkedList<Pair<Integer,String>> rankList;
    public Rank(LinkedList<Pair<Integer,String>> rankList){
        this.rankList=rankList;

    }
    public void addRank(int score,String date){
        for(int i=0;i<rankList.size();i++) {
            if(rankList.get(i).getKey()<=score){
                rankList.add(i,new Pair<>(score,date));
                break;
            }
            if(i==rankList.size()-1){
                rankList.addLast(new Pair<>(score,date));
                break;
            }
        }
        if (rankList.isEmpty()){
            rankList.add(new Pair<>(score,date));
        }
        if(rankList.size()>10){
            rankList.removeLast();
        }
    }

    public LinkedList<Pair<Integer, String>> getRankList() {
        return rankList;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "rankList=" + rankList +
                '}';
    }
}
