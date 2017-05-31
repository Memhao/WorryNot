package licence.meme.worrynot.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.FirebaseService;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mResetPasswordButton;
    private Button mBackButton;
    private EditText mEmailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);

        mResetPasswordButton = (Button)findViewById(R.id.reset_forgot_password_activity_btn);
        mBackButton = (Button)findViewById(R.id.back_forgot_password_activity_btn);
        mEmailEditText = (EditText)findViewById(R.id.email_forgot_password_activity_et);
        mResetPasswordButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reset_forgot_password_activity_btn:
                FirebaseService.getInstance().resetPassword(this,mEmailEditText.getText().toString());
                 break;
            case R.id.back_forgot_password_activity_btn:
                startActivity(new Intent(ForgotPasswordActivity.this,MainActivity.class));
                finish();
            default:break;
        }
    }
}
