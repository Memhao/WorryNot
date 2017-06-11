package licence.meme.worrynot.gui.screen.worrynotcreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import licence.meme.worrynot.R;
import licence.meme.worrynot.adapter.RecycleViewEntryAdapter;

public class CreateWorryNotQuestionnaireActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String BUNDLE = "CREATE_BUNDLE";
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String STORY = "STORY";
    private static final String STEPS = "STEPS";
    private static final String QUESTIONS = "QUESTIONS";
    private static final String TAG = CreateWorryNotQuestionnaireActivity.class.getSimpleName();

    private Button mBackButton;
    private Button mNextButton;
    private RecyclerView mQuestionsRecyclerView;
    private RecycleViewEntryAdapter mQuestionsAdapter;

    private Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_worry_not_questionnaire);
        mBundle = getIntent().getBundleExtra(BUNDLE);
        mQuestionsRecyclerView = (RecyclerView)findViewById(R.id.questions_create_worry_not_questionnaire_rv);
        mBackButton = (Button)findViewById(R.id.back_create_worry_not_questionnaire_btn);
        mNextButton = (Button)findViewById(R.id.next_create_worry_not_questionnaire_btn);




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mQuestionsRecyclerView.setLayoutManager(linearLayoutManager);

        mQuestionsAdapter = new RecycleViewEntryAdapter(this.getLayoutInflater(),mQuestionsRecyclerView,this);
        mQuestionsRecyclerView.setAdapter(mQuestionsAdapter);

        mNextButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_create_worry_not_questionnaire_btn:
                startActivity(new Intent(this,CreateWorryNotStepsActivity.class));
                finish();
            break;
            case R.id.next_create_worry_not_questionnaire_btn:
                ArrayList<String> questions = new ArrayList<>();
                for(String s:mQuestionsAdapter.getEntries()){
                    Log.e(TAG,"[onCreate]"+s);
                }
                for(int i = 0; i < mQuestionsRecyclerView.getChildCount();i++){
                    RecycleViewEntryAdapter.EntryHolder holder = (RecycleViewEntryAdapter.EntryHolder) mQuestionsRecyclerView.findViewHolderForAdapterPosition(i);
                    EditText et = holder.getEntryEditText();
                    Log.e(TAG,"STEP:"+i+":"+ et.getText().toString());
                    questions.add(et.getText().toString());
                }
                mBundle.putStringArrayList(QUESTIONS,questions);
                Intent intent = new Intent(this,CreateWorryNotResultsActivity.class);
                intent.putExtra(BUNDLE,mBundle);
                startActivity(intent);
                finish();
            break;
            default:break;
        }
    }
}
