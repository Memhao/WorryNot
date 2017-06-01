package licence.meme.worrynot.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.FirebaseService;
import licence.meme.worrynot.models.QuestionnaireRender;
import licence.meme.worrynot.models.Score;

public class QuestionnaireActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSubmitButton;
    private ListView mQuestionnaireListView;
    private Score mScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        mSubmitButton = (Button)findViewById(R.id.submit_questionnaire_activity_btn);
        mQuestionnaireListView = (ListView)findViewById(R.id.questions_activity_questionnaire_lv);
        mSubmitButton.setOnClickListener(this);
        mScore = Score.getInstance();
        FirebaseService.getInstance().populateQuestionnaire(mQuestionnaireListView,this,mScore);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_questionnaire_activity_btn:
                for(Map.Entry entry:mScore.getScore().entrySet() )
                 Log.e("QuestionnaireActiviy","SCORE:"+entry.getValue());
                 // here compute the experience :D then the score
//                startActivity(new Intent(this, ProfileActivity.class));
//                finish();
            break;
            default:break;
        }
    }
}
