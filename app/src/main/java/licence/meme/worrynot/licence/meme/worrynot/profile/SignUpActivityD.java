//package licence.meme.worrynot.licence.meme.worrynot.profile;
//
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import licence.meme.worrynot.R;
//import licence.meme.worrynot.licence.meme.worrynot.Levels;
//import licence.meme.worrynot.models.User;
//
//public class SignUpActivityD extends AppCompatActivity implements View.OnClickListener {
//
//    private EditText emailEditText,passwordEditText,usernameEditText;
//    private TextView loginTextView, forgotPasswordTextView;
//    private Button registerButton;
//
//    private FirebaseAuth auth;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup_deprecated);
//
//        emailEditText = (EditText)findViewById(R.id.email_signUp_et);
//        passwordEditText = (EditText)findViewById(R.id.password_signUp_et);
//        usernameEditText = (EditText)findViewById(R.id.username_signUp_et);
//        loginTextView = (TextView)findViewById(R.id.already_account_signUp_tv);
//        forgotPasswordTextView = (TextView)findViewById(R.id.forgot_password_signUp_tv);
//        registerButton = (Button)findViewById(R.id.register_signUp_btn);
//        auth = FirebaseAuth.getInstance();
//        registerButton.setOnClickListener(this);
//        forgotPasswordTextView.setOnClickListener(this);
//        loginTextView.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.register_signUp_btn:
//                signUpUser(emailEditText.getText().toString(),passwordEditText.getText().toString());
////                finish();
//                break;
//            case R.id.forgot_password_signUp_tv:
//                startActivity(new Intent(SignUpActivityD.this,ForgotPasswordActivityD.class));
//                finish();
//                break;
//            case R.id.already_account_signUp_tv:
//                startActivity(new Intent(SignUpActivityD.this,LoginActivity.class));
//                finish();
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void signUpUser(final String email, final String password) {
//        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(!task.isSuccessful()){
//                    Toast.makeText(SignUpActivityD.this,"Error:" + task.getException().toString(),Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(SignUpActivityD.this,"Registration succeed !",Toast.LENGTH_SHORT);
//
//
//                    DatabaseReference usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
//
//                    usersDatabaseReference.push().setValue(new User(usernameEditText.getText().toString(),email,"0",Levels.level_0));
//
//                    DatabaseReference levelsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("top").child(Levels.level_0).child("memebers");
//                    levelsDatabaseReference.push().setValue(true);
//
//
//                    DatabaseReference UidPerMethodDatabaseReference = FirebaseDatabase.getInstance().getReference().child("methodPerUser");
//                    UidPerMethodDatabaseReference.push().setValue(false);
//                    usernameEditText.setText("");
//                    emailEditText.setText("");
//                    passwordEditText.setText("");
////                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
////                    imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(),0);
//
//                }
//            }
//        });
//    }
//
//
//}
