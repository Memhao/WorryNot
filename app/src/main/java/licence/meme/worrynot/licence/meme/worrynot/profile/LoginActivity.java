package licence.meme.worrynot.licence.meme.worrynot.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import licence.meme.worrynot.R;

/**
 * Created by xander on 24.02.2017.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText emailEditText,passwordEditText;
    private TextView forgotPasswordTextView,signUpTextView;
    private Button loginButton;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = (EditText)findViewById(R.id.email_et);
        passwordEditText = (EditText)findViewById(R.id.password_et);

        forgotPasswordTextView = (TextView)findViewById(R.id.login_forgot_password_tv);
        signUpTextView = (TextView)findViewById(R.id.login_signup_tv);

        loginButton = (Button)findViewById(R.id.login_btn);

        auth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(this);
        forgotPasswordTextView.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
/**
 * This will keep current session if user is login
 */
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,AccountActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_forgot_password_tv:
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivityD.class));
                finish();
                break;
            case R.id.login_signup_tv:
                startActivity(new Intent(LoginActivity.this,SignUpActivityD.class));
                finish();
                break;
            case R.id.login_btn:
                loginUser(emailEditText.getText().toString(),passwordEditText.getText().toString());
            default:
                break;
        }
    }

    private void loginUser(String email, final String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    if(password.length()<6){
                        Toast.makeText(LoginActivity.this,"Password too short ",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    startActivity(new Intent(LoginActivity.this,AccountActivity.class));
                }
            }
        });
    }
}
