package licence.meme.worrynot.gui.screen.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import licence.meme.worrynot.R;

public class ResultPopUpActivity extends AppCompatActivity {
    private TextView interpretationTextView;
    private TextView titleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_pop_up);
        interpretationTextView = (TextView)findViewById(R.id.interpretation_result_pop_up_activity_tv);
        titleTextView = (TextView)findViewById(R.id.title_result_pop_up_activity_tv);

        Intent result = getIntent();
        final String TAG_TRANSFER = "RESULT";
        String interpretation = result.getExtras().getString(TAG_TRANSFER);
        Log.e("ResultPopUpAcitivty","INTERPREATION :"+ interpretation);

        titleTextView.setText("Interpretation");
        interpretationTextView.setText("                "+interpretation);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*0.8));
    }
}
