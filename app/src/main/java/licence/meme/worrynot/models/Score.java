package licence.meme.worrynot.models;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by xander on 02.06.2017.
 */

public class Score  {
    private static HashMap<Integer,Float> score;
    private static Score instance;
    private Score(){
        this.score = new HashMap<>();
    }
    public static  Score getInstance(){
        if(instance == null){
            return new Score();
        }
        return instance;
    }
    public static HashMap<Integer,Float> getScore(){
        return score;
    }
    public static void setScoreAt(int pos, float rating){
        score.put(pos,rating);
    }
    public void releaseInstance(){
        instance = null;
    }

    public static float getScoreAt(int position) {
        return score.get(position);
    }

}
