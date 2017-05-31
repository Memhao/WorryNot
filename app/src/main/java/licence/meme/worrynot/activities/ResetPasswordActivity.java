package licence.meme.worrynot.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.FirebaseService;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmailEditText;
    private EditText mOldPasswordEditText;
    private EditText mNewPasswordEditText;
    private TextView mTitleTextView;
    private TextView mFeedBackTextView;
    private Button mSendMailForResetPasswordButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reset_password);

        mEmailEditText = (EditText)findViewById(R.id.email_activity_reset_password_et);
        mOldPasswordEditText = (EditText)findViewById(R.id.old_password_activity_reset_password_et);
        mNewPasswordEditText = (EditText)findViewById(R.id.new_password_activity_reset_password_et);
        mTitleTextView = (TextView)findViewById(R.id.title_activity_reset_password_tv);
        mFeedBackTextView = (TextView)findViewById(R.id.feedback_activity_reset_password_tv);
        mSendMailForResetPasswordButton = (Button)findViewById(R.id.send_mail_activity_reset_password_btn);

        mSendMailForResetPasswordButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_mail_activity_reset_password_btn:
                FirebaseService.getInstance().changePassword(
                        this,
                        mEmailEditText.getText().toString(),
                        mOldPasswordEditText.getText().toString(),
                        mNewPasswordEditText.getText().toString());
            break;
            default:break;
        }
    }
}
