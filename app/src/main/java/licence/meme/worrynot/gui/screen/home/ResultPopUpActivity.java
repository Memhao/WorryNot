package licence.meme.worrynot.gui.screen.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import licence.meme.worrynot.R;
import licence.meme.worrynot.main.ProfileActivity;

public class ResultPopUpActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView interpretationTextView;
    private TextView titleTextView;
    private Button mOkButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_pop_up);
        interpretationTextView = (TextView)findViewById(R.id.interpretation_result_pop_up_activity_tv);
        titleTextView = (TextView)findViewById(R.id.title_result_pop_up_activity_tv);
        mOkButton = (Button)findViewById(R.id.ok_result_pop_up_activity_btn);
        mOkButton.setOnClickListener(this);
        Intent result = getIntent();
        final String TAG_TRANSFER = "RESULT";
        String interpretation = result.getExtras().getString(TAG_TRANSFER);
//        final String BUNDLE = "BUNDLE_POP_UP";
//        final String RESULT = "RESULT";
//        String interpretation = result.getBundleExtra(BUNDLE).getString(RESULT);
        Log.e("ResultPopUpAcitivty","INTERPREATION :"+ interpretation);

        titleTextView.setText("Interpretation");
        interpretationTextView.setText("                "+interpretation);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*0.8));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ok_result_pop_up_activity_btn){
            finish();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // If we've received a touch notification that the user has touched
        // outside the app, finish the activity.
        if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
            finish();
            return true;
        }

        // Delegate everything else to Activity.
        return super.onTouchEvent(event);
    }
}
