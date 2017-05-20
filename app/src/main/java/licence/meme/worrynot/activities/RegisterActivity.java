package licence.meme.worrynot.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import licence.meme.worrynot.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mRegisterButton;
    private Button mBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        mRegisterButton = (Button)findViewById(R.id.register_register_activity_btn);
        mBackButton = (Button)findViewById(R.id.back_register_activity_btn);

        mRegisterButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_register_activity_btn:break;
            case R.id.back_register_activity_btn:
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                finish();
            default:break;
        }
    }
}
