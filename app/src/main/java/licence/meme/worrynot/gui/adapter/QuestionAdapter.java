package licence.meme.worrynot.gui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.Score;

/**
 * Created by xander on 01.06.2017.
 */

public class QuestionAdapter extends BaseAdapter {
    private  Context context;
    private  List<String > questions;
    private static LayoutInflater inflater = null;
    private float ratings[];
    private Score score;
    public QuestionAdapter(Context context, List<String> questions,Score score) {
        this.context = context;
        this.questions = questions;
        this.ratings= new float[questions.size()];
        this.score = score;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public float[] getRatings(){
        return ratings;
    }


    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View return_view = convertView;
        if(return_view == null){
            return_view = inflater.inflate(R.layout.row_questionnaire,null);
        }
        TextView questionTextView = (TextView)return_view.findViewById(R.id.question_row_question_tv);
        RatingBar ratingBar = (RatingBar)return_view.findViewById(R.id.evaluate_question_row_question_rb);
        if(questions.get(position) != null){
            questionTextView.setText(questions.get(position));
        }
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratings[position] = rating;
                score.setScoreAt(position,rating);
                Log.e("--------->","RATING AT "+ position +":" + ratings[position]);
                Log.e("--------->","RATING AT SCORE "+ position +":" + Score.getScoreAt(position));
            }
        });
        return return_view;
    }
}
