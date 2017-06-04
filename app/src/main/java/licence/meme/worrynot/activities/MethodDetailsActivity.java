package licence.meme.worrynot.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import licence.meme.worrynot.R;

public class MethodDetailsActivity extends AppCompatActivity {
    private static final String METHOD_TITLE = "METHOD_TITLE";
    private static final String METHOD_AUTHOR = "METHOD_AUTHOR";
    private static final String BUNDLE = "BUNDLE";
    private TextView mMethodTitle;
    private TextView mMethodAuthor;
    private TextView mMethodDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_details);

        Bundle bundle = getIntent().getBundleExtra(BUNDLE);
        mMethodTitle = (TextView)findViewById(R.id.title_method_details_activity_tv);
        mMethodAuthor = (TextView)findViewById(R.id.author_method_details_activity_tv);
        mMethodDescription = (TextView)findViewById(R.id.description_method_details_activity_tv);

        mMethodTitle.setText(bundle.getString(METHOD_TITLE));
        mMethodAuthor.setText(bundle.getString(METHOD_AUTHOR));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*0.8));

    }
}
