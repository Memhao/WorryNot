package licence.meme.worrynot.licence.meme.worrynot.licence.meme.worrynot.activities.obsolated;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import licence.meme.worrynot.R;

public class RetrieveActivity extends AppCompatActivity implements View.OnClickListener {
    private Button printMethodsButton;
    private TextView printMethodsTextView;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        printMethodsTextView = (TextView)findViewById(R.id.printMethods_retrieve_et);
        printMethodsButton = (Button)findViewById(R.id.printMethods_retrieve_btn);
        ref = FirebaseDatabase.getInstance().getReference().child("USER");
        printMethodsButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.printMethods_retrieve_btn:
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                        Thread t = new Thread() {
//
//                            @Override
//                            public void run() {
//                                try {
//                                    while (!isInterrupted()) {
//                                        Thread.sleep(1000);
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                newMethodsTextView.setText();
//                                            }
//                                        });
//                                    }
//                                } catch (InterruptedException e) {
//                                }
//                            }
//                        };
//
//                        t.start();
                        String userName = dataSnapshot.getValue().toString();
                        printMethodsTextView.setText("Name:"+userName);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
            default: break;
        }
    }
}
