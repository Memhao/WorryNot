package licence.meme.worrynot.gui.screen.worrynotcreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import licence.meme.worrynot.R;
import licence.meme.worrynot.gui.logic.adapter.RecycleViewEntryAdapter;

public class CreateWorryNotStepsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String BUNDLE = "CREATE_BUNDLE";
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String STORY = "STORY";
    private static final String STEPS = "STEPS";
    private static final String TAG = CreateWorryNotStepsActivity.class.getSimpleName();
    private Button mBackButton;
    private Button mNextButton;
    private RecyclerView mStepsRecyclerView;
    private RecycleViewEntryAdapter mStepsAdapter;

    private Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_worry_not_steps);
        mBundle = getIntent().getBundleExtra(BUNDLE);
        mBackButton = (Button)findViewById(R.id.back_create_worry_not_steps_btn);
        mNextButton = (Button)findViewById(R.id.next_create_worry_not_steps_btn);
        mStepsRecyclerView = (RecyclerView)findViewById(R.id.steps_create_worry_not_steps_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mStepsRecyclerView.setLayoutManager(linearLayoutManager);

        mStepsAdapter = new RecycleViewEntryAdapter(this.getLayoutInflater(),mStepsRecyclerView,this);
        mStepsRecyclerView.setAdapter(mStepsAdapter);

        mNextButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.back_create_worry_not_steps_btn:
                startActivity(new Intent(this,CreateWorryNotInfoActivity.class));
                finish();
                break;
            case R.id.next_create_worry_not_steps_btn:
                ArrayList<String> steps = new ArrayList<>();
                for(String s:mStepsAdapter.getEntries()){
                    Log.e(TAG,"[onCreate]"+s);
                }
                for(int i = 0; i < mStepsRecyclerView.getChildCount();i++){
                    RecycleViewEntryAdapter.EntryHolder holder = (RecycleViewEntryAdapter.EntryHolder) mStepsRecyclerView.findViewHolderForAdapterPosition(i);
                    EditText et = holder.getEntryEditText();
                    Log.e(TAG,"STEP:"+i+":"+ et.getText().toString());
                    steps.add(et.getText().toString());
                }
                mBundle.putStringArrayList(STEPS,steps);
                Intent intent = new Intent(this,CreateWorryNotQuestionnaireActivity.class);
                intent.putExtra(BUNDLE,mBundle);
                startActivity(intent);
                finish();
                break;
            default:break;
        }
    }
}
