package licence.meme.worrynot.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.Map;

import licence.meme.worrynot.R;
import licence.meme.worrynot.main.ProfileActivity;
import licence.meme.worrynot.models.ExperienceManager;
import licence.meme.worrynot.models.FirebaseService;
import licence.meme.worrynot.models.Score;

public class QuestionnaireActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSubmitButton;
    private Button mBackButton;
    private ListView mQuestionnaireListView;
    private Score mScore;
    private ExperienceManager experienceManager;
    private boolean pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        mSubmitButton = (Button)findViewById(R.id.submit_questionnaire_activity_btn);
        mBackButton = (Button)findViewById(R.id.back_questionnaire_activity_btn);
        mQuestionnaireListView = (ListView)findViewById(R.id.questions_activity_questionnaire_lv);
        experienceManager = new ExperienceManager();
        mSubmitButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);
        pressed = false;

        mScore = Score.getInstance();
        FirebaseService.getInstance().populateQuestionnaire(mQuestionnaireListView,this,mScore);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_questionnaire_activity_btn:
                for(Map.Entry entry:mScore.getScore().entrySet() )
                 Log.e("QuestionnaireActiviy","SCORE:"+entry.getValue());
                 createYesNoDialog(this);
            break;
            case R.id.back_questionnaire_activity_btn:
                    startActivity(new Intent(this,ProfileActivity.class));
                    finish();
                break;
            default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pressed){
            experienceManager.increaseExperience(50.0f);
            experienceManager.persistExperience();
        }

    }

    private void createYesNoDialog(final Activity activity) {
        AlertDialog.Builder alertYesNoDialog = new AlertDialog.Builder(this);
        alertYesNoDialog.setMessage("Are you sure you have been self-assessed correctly?");
        alertYesNoDialog.setCancelable(false);
        alertYesNoDialog.setPositiveButton("Yes",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                int score = (int)computeScore();
                Log.e("QuestionnaireActivity", "ComputedScore:"+score);
                pressed = true;
                FirebaseService.getInstance().popUpAdvice(score,activity);
            }

            private float computeScore() {
                float score = 0.0f;
                for(Map.Entry entry:mScore.getScore().entrySet() ){
                    score+=(float)entry.getValue();
                }
                score = score/mScore.getScore().entrySet().size();
                return  score;
            }
        });
        alertYesNoDialog.setNegativeButton("No",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertYesNoDialog.create().show();
    }


}
