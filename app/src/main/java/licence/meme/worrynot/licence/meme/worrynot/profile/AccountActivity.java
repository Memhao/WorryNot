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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import licence.meme.worrynot.R;
import licence.meme.worrynot.licence.meme.worrynot.method.CreateMethod;
import licence.meme.worrynot.licence.meme.worrynot.method.ProfileActivityDeprecated;

/**
 * Created by xander on 21.02.2017.
 */

public class AccountActivity extends Activity implements View.OnClickListener {
    private Button logOutButton;
    private Button changePasswordButton;
    private Button createMethodButton;
    private Button profileActivityButton;
    private TextView welcomeTextView;
    private EditText newPasswordEditText;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        logOutButton = (Button) findViewById(R.id.logOut_account_btn);
        changePasswordButton = (Button) findViewById(R.id.changePassword_account_btn);
        createMethodButton = (Button)findViewById(R.id.createMethod_account_btn);
        profileActivityButton = (Button)findViewById(R.id.profileActivity_account_btn);

        welcomeTextView = (TextView) findViewById(R.id.welcome_account_tv);
        newPasswordEditText = (EditText) findViewById(R.id.newPassword_account_et);


        changePasswordButton.setOnClickListener(this);
        createMethodButton.setOnClickListener(this);
        logOutButton.setOnClickListener(this);
        profileActivityButton.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            welcomeTextView.setText(auth.getCurrentUser().getEmail());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changePassword_account_btn:
                changePassword(newPasswordEditText.getText().toString());
                break;
            case R.id.createMethod_account_btn:
                startActivity(new Intent(AccountActivity.this,CreateMethod.class));
                finish();
                break;
            case R.id.logOut_account_btn:
                logOut();
                break;
            case R.id.profileActivity_account_btn:
                startActivity(new Intent(AccountActivity.this,ProfileActivityDeprecated.class));
                finish();
            default:
                break;
        }
    }

    private void logOut() {
        auth.signOut();
        if(auth.getCurrentUser() == null){
            startActivity(new Intent(AccountActivity.this,LoginActivity.class));
            finish();
        }
    }

    private void changePassword(String newPassword) {
        FirebaseUser user = auth.getCurrentUser();
        user.updatePassword(newPassword).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AccountActivity.this,"Password successfully changed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}