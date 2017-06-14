package licence.meme.worrynot.gui.screen.worrynotstore;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import licence.meme.worrynot.R;
import licence.meme.worrynot.controller.FirebaseService;

public class WriteCommentActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout mContainerTextInputLayout;
    private EditText mCommentEditText;
    private Button mCommentButton;
    private Bundle mBundle;
    private static final String KEY = "KEY";
    private static final String WRITE_COMMENT_BUNDLE = "WRITE_COMMENT_BUNDLE";
    private static final FirebaseService FIREBASE_SERVICE = FirebaseService.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.7),(int)(height*0.7));
        mBundle = getIntent().getBundleExtra(WRITE_COMMENT_BUNDLE);
        mContainerTextInputLayout = (TextInputLayout)findViewById(R.id.comm_container_write_comment_activity_til);
        mCommentEditText = (EditText)findViewById(R.id.comment_write_comment_activity_et);
        mCommentButton = (Button)findViewById(R.id.submit_comm_write_comment_activity_btn);
        mCommentEditText.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });
        mCommentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submit_comm_write_comment_activity_btn){
            FIREBASE_SERVICE.writeComment(this,mBundle.getString(KEY),mCommentEditText.getText().toString());
            finish();
        }
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
