package licence.meme.worrynot.licence.meme.worrynot.method;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import licence.meme.worrynot.R;
import licence.meme.worrynot.licence.meme.worrynot.profile.AccountActivity;

public class CreateMethod extends Activity implements View.OnClickListener {

    public static class User {
        private String name;
        public User(String name){
            this.name = name;
        }
        public  String toString(){
            return  this.name+"";
        }

    }
    private Button saveButton;
    private Button backButton;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private FirebaseAuth auth;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_method);

        saveButton = (Button)findViewById(R.id.save_createMethod_btn);
        backButton = (Button)findViewById(R.id.back_createMethod_btn);
        nameEditText = (EditText)findViewById(R.id.name_createMethod_et);
        descriptionEditText = (EditText)findViewById(R.id.description_createMethod_et);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();


        saveButton.setOnClickListener(this);
        backButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_createMethod_btn:
                if(auth != null && database != null){


//                    database.child(auth.getCurrentUser().toString()).setValue(nameEditText.getText().toString());
                    database.child(descriptionEditText.getText().toString()).setValue(nameEditText.getText().toString());
                    nameEditText.setText("");
                    descriptionEditText.setText("");
                }
                break;
            case R.id.back_createMethod_btn:
                startActivity(new Intent(CreateMethod.this,AccountActivity.class));
                finish();
                break;
            default:break;
        }
    }
}
