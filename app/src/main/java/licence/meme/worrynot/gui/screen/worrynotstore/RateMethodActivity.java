package licence.meme.worrynot.gui.screen.worrynotstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.FirebaseService;
import licence.meme.worrynot.models.MethodRatingManager;

public class RateMethodActivity extends AppCompatActivity implements View.OnClickListener {

    private RatingBar mRatingBar;
    private Button mSubmitButton;
    private static final String RATE_BUNDLE = "RATE_BUNDLE";
    private static final String KEY = "KEY";
    private static final FirebaseService FIREBASE_SERVICE = FirebaseService.getInstance();
    private int mScore;
    private boolean pressed = false;
    private Bundle mBundle;
    private MethodRatingManager mMethodRatingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_method);
        mScore = 0;
        mRatingBar = (RatingBar)findViewById(R.id.rate_method_activity_rb);
        mSubmitButton = (Button)findViewById(R.id.submit_rate_method_activity_btn);
        mBundle = getIntent().getBundleExtra(RATE_BUNDLE);

        mMethodRatingManager  = new MethodRatingManager(mBundle.getString(KEY));
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mScore = (int)rating;
            }
        });
        mSubmitButton.setOnClickListener(this);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*0.19));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pressed){
            mMethodRatingManager.addRating(mScore);
            mMethodRatingManager.persistRating();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_rate_method_activity_btn:
                pressed = true;
                finish();
                break;
            default:break;
        }
    }
}
