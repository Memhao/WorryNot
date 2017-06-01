package licence.meme.worrynot.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import licence.meme.worrynot.R;

/**
 * Created by xander on 01.06.2017.
 */

public class QuestionnaireRender {
    private List<String> questionnaire;
    public QuestionnaireRender(List<String> questionnaire){
        this.questionnaire = questionnaire;
    }
    public void drawQuestionnaire(Activity activity, ListView listView)
    {
        listView = (ListView)activity.findViewById(R.id.questions_activity_questionnaire_lv);
//        listView.setAdapter(new QuestionAdapter(activity,questionnaire,m));
    }
}
