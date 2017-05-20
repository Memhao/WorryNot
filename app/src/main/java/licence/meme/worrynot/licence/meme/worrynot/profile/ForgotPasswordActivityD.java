package licence.meme.worrynot.licence.meme.worrynot.profile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import licence.meme.worrynot.R;


public class ForgotPasswordActivityD extends AppCompatActivity implements View.OnClickListener {

    private Button backButton, resetPasswordButton;
    private EditText emailEditText;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_deprecated);

        backButton = (Button)findViewById(R.id.back_forgotPassword_btn);
        resetPasswordButton = (Button)findViewById(R.id.reset_forgotPassword_btn);
        emailEditText = (EditText)findViewById(R.id.email_forgotPassword_et);


        auth = FirebaseAuth.getInstance();
        backButton.setOnClickListener(this);
        resetPasswordButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_forgotPassword_btn:
                startActivity(new Intent(ForgotPasswordActivityD.this,LoginActivity.class));
                finish();
                break;
            case R.id.reset_forgotPassword_btn:
                resetPassword(emailEditText.getText().toString());
                break;
            default:
                break;
        }
    }

    private void resetPassword(String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivityD.this,"Email sent. Please check your inbox ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
