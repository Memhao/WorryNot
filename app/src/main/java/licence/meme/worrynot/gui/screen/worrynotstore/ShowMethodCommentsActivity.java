package licence.meme.worrynot.gui.screen.worrynotstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.FirebaseService;

public class ShowMethodCommentsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final FirebaseService FIREBASE_SERVICE = FirebaseService.getInstance();
    private static final String KEY = "KEY";
    private static final String SHOW_COMMENT_BUNDLE = "SHOW_COMMENT_BUNDLE";
    private static final String METHOD_TITLE = "METHOD_TITLE";
    private RecyclerView mRecyclerView;
    private Button mBackButton;
    private TextView mTitleTextView;
    private Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_method_comments);
        mRecyclerView = (RecyclerView)findViewById(R.id.comments_show_method_comments_activity_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mBackButton = (Button)findViewById(R.id.back_show_method_comments_activity_btn);
        mTitleTextView = (TextView) findViewById(R.id.title_show_method_comments_activity_tv);
        mBundle = getIntent().getBundleExtra(SHOW_COMMENT_BUNDLE);
        mTitleTextView.setText(mBundle.getString(METHOD_TITLE));
        FIREBASE_SERVICE.populateMethodsCommentsRecycleView(mRecyclerView,this.getLayoutInflater(),this,mBundle.getString(KEY));
        mBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_show_method_comments_activity_btn:
                finish();
                break;
            default:break;
        }
    }
}
