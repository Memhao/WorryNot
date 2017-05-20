package licence.meme.worrynot.licence.meme.worrynot.licence.meme.worrynot.activities.obsolated;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.Info;
import licence.meme.worrynot.models.Metadata;
import licence.meme.worrynot.models.Method;


public class AddMethodActivity extends Activity implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_addMethod_btn:
                if(auth != null && database != null){

                    DatabaseReference methodsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("methods");


//                    Info info = new Info(storyEditText.getText().toString(),textInAutoCompleteTextView.getText().toString());
//                    Metadata metadata = new Metadata("$author",descriptionEditText.getText().toString(),nameEditText.getText().toString());
//                    Method method = new Method(metadata,info);
                    methodsDatabaseReference.push().setValue(steps);
//                    database.child(descriptionEditText.getText().toString()).setValue(nameEditText.getText().toString());

//
//                    FirebaseUser currentUser =  FirebaseAuth.getInstance().getCurrentUser();
//                    String currentuser = currentUser.toString();
//                    DatabaseReference UidPerMethodDatabaseReference = FirebaseDatabase.getInstance().getReference().child("methodPerUser").child(currentuser);
//                    UidPerMethodDatabaseReference.push().setValue(true);
                    nameEditText.setText("");
                    descriptionEditText.setText("");
                }
                break;
            case R.id.back_adddMethod_btn:

                break;
            default:break;
        }
    }

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
    private EditText storyEditText;
    private FirebaseAuth auth;
    private DatabaseReference database;

    private HashMap<String,String> steps = new HashMap<String,String>();

    private AutoCompleteTextView textInAutoCompleteTextView;
    private Button dynamicallyAddButton;
    private LinearLayout dynamicContainerLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_method);





        saveButton = (Button)findViewById(R.id.save_addMethod_btn);
        backButton = (Button)findViewById(R.id.back_adddMethod_btn);
        nameEditText = (EditText)findViewById(R.id.name_addMethod_et);
        descriptionEditText = (EditText)findViewById(R.id.description_addMethod_et);
        storyEditText = (EditText)findViewById(R.id.story_addMethod_et);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        saveButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }


}
