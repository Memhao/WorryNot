package licence.meme.worrynot.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.FirebaseService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private  Button mLoginButton ;
    private  Button mForgtoPasswordButton ;
    private  Button mRegisterButton ;

    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    private FirebaseService mFirebaseService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mFirebaseService = FirebaseService.getInstance();
        mLoginButton = (Button)findViewById(R.id.login_main_activity_btn);
        mForgtoPasswordButton = (Button)findViewById(R.id.forgot_main_activity_btn);
        mRegisterButton = (Button)findViewById(R.id.signup_main_avitivty_btn);
        mEmailEditText = (EditText)findViewById(R.id.email_main_activity_et);
        mPasswordEditText = (EditText)findViewById(R.id.password_main_activity_et);

        mLoginButton.setOnClickListener(this);
        mForgtoPasswordButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_main_activity_btn:
                mFirebaseService.loginUser(MainActivity.this,mEmailEditText.getText().toString(),mPasswordEditText.getText().toString());
                break;
            case R.id.forgot_main_activity_btn:
                startActivity(new Intent(MainActivity.this,ForgotPasswordActivity.class));
                finish();
                break;
            case R.id.signup_main_avitivty_btn:
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                finish();
                break;
            default:break;
        }
    }
}
