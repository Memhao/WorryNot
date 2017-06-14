package licence.meme.worrynot.gui.screen.worrynotcreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

import licence.meme.worrynot.R;
import licence.meme.worrynot.main.ProfileActivity;
import licence.meme.worrynot.controller.FirebaseService;
import licence.meme.worrynot.models.Info;
import licence.meme.worrynot.models.Metadata;
import licence.meme.worrynot.models.Method;
import licence.meme.worrynot.models.Result;

public class CreateWorryNotResultsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String BUNDLE = "CREATE_BUNDLE";
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String STORY = "STORY";
    private static final String STEPS = "STEPS";
    private static final String QUESTIONS = "QUESTIONS";
    private static final String USER_NAME = "USER_NAME";
    private static final String TAG = CreateWorryNotQuestionnaireActivity.class.getSimpleName();

    private Button mBackButton;
    private Button mNextButton;
    private Bundle mBundle;
    private EditText mLowEditText;
    private EditText mMediumEditText;
    private EditText mHighEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_worry_not_results);
        mBundle = getIntent().getBundleExtra(BUNDLE);
        mBackButton = (Button)findViewById(R.id.back_create_worry_not_results_btn);
        mNextButton = (Button)findViewById(R.id.finish_create_worry_not_results_btn);
        mLowEditText = (EditText)findViewById(R.id.low_create_create_worry_not_results_et);
        mMediumEditText = (EditText)findViewById(R.id.medium_create_create_worry_not_results_et);
        mHighEditText = (EditText)findViewById(R.id.high_create_create_worry_not_results_et);

        mBackButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_create_worry_not_results_btn:
                startActivity(new Intent(this,CreateWorryNotQuestionnaireActivity.class));
                finish();
                break;
            case R.id.finish_create_worry_not_results_btn:
                String user_name = mBundle.getString(USER_NAME);
                String name = mBundle.getString(NAME);
                String description = mBundle.getString(DESCRIPTION);
                String story = mBundle.getString(STORY);
                ArrayList<String> steps = mBundle.getStringArrayList(STEPS);
                ArrayList<String> questions = mBundle.getStringArrayList(QUESTIONS);

                String lowResult = mLowEditText.getText().toString();
                String mediumResult = mMediumEditText.getText().toString();
                String highResult = mHighEditText.getText().toString();
                String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Metadata metadata = new Metadata(user_name,description,name);

                HashMap hashMapQuestions = new HashMap();
                for(String s : questions){
                    hashMapQuestions.put(s+user,s);
                }

                HashMap hashMapSteps = new HashMap();
                for(String s : steps){
                    hashMapSteps.put(s+user,s);
                }
                Info info = new Info();
                info.setQuestionnaire(hashMapQuestions);
                info.setSteps(hashMapSteps);

                Result result = new Result();
                result.setLow(lowResult);
                result.setMedium(mediumResult);
                result.setHigh(highResult);
                info.setResults(result);

                Method worryNot = new Method(metadata,info);
                FirebaseService.getInstance().createMethod(this,worryNot);
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                break;
            default:break;
        }
    }
}
