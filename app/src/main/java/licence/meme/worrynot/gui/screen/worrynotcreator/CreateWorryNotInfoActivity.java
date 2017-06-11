package licence.meme.worrynot.gui.screen.worrynotcreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import licence.meme.worrynot.R;
import licence.meme.worrynot.main.ProfileActivity;

public class CreateWorryNotInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBackButton;
    private Button mNextButton;
    private EditText mNameWorryNotEditText;
    private EditText mDescriptionWorryNotEditText;
    private EditText mStoryWorryNotEditText;

    private static final String BUNDLE = "CREATE_BUNDLE";
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String STORY = "STORY";
    private static final String TAG = CreateWorryNotInfoActivity.class.getSimpleName();
    private Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_worry_not_info);
        mBackButton = (Button)findViewById(R.id.back_create_worry_not_info_btn);
        mNextButton = (Button)findViewById(R.id.next_create_worry_not_info_btn);
        mNameWorryNotEditText = (EditText)findViewById(R.id.name_create_create_worry_not_info_et);
        mDescriptionWorryNotEditText = (EditText)findViewById(R.id.description_create_create_worry_not_info_et);
        mStoryWorryNotEditText = (EditText)findViewById(R.id.story_create_worry_not_info_et);
        mNameWorryNotEditText.setText("Name");
        mDescriptionWorryNotEditText.setText("Description");
        mStoryWorryNotEditText.setText("Story");
        mBundle = new Bundle();

        mBackButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_create_worry_not_info_btn:
                startActivity(new Intent(this,ProfileActivity.class));
                finish();
                break;
            case R.id.next_create_worry_not_info_btn:
                Log.e(TAG,"NAME:"+mNameWorryNotEditText.getText().toString());

                mBundle.putString(NAME,mNameWorryNotEditText.getText().toString());
                mBundle.putString(DESCRIPTION,mDescriptionWorryNotEditText.getText().toString());
                mBundle.putString(STORY,mStoryWorryNotEditText.getText().toString());
                Intent intent = new  Intent(this, CreateWorryNotStepsActivity.class);
                intent.putExtra(BUNDLE,mBundle);
                startActivity(intent);
                finish();
                break;
            default:break;
        }
    }
}
