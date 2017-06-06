package licence.meme.worrynot.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.nio.BufferUnderflowException;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.FirebaseService;

public class MethodDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String METHOD_TITLE = "METHOD_TITLE";
    private static final String METHOD_AUTHOR = "METHOD_AUTHOR";
    private static final String METHOD_RATING = "METHOD_RATING";
    private static final String METHOD_DESCRIPTION = "METHOD_DESCRIPTION";

    private static final String KEY = "KEY";
    private static final String BUNDLE = "BUNDLE";
    private static final String RATE_BUNDLE = "RATE_BUNDLE";
    private static final String WRITE_COMMENT_BUNDLE = "WRITE_COMMENT_BUNDLE";
    private static final String SHOW_COMMENT_BUNDLE = "SHOW_COMMENT_BUNDLE";

    private TextView mMethodTitle;
    private TextView mMethodAuthor;
    private TextView mMethodDescription;
    private RatingBar mRatingBar;

    private ImageView mDownloadImageView;
    private ImageView mRateImageView;
    private ImageView mWriteComment;

    private Button mCommentsButton;
    private Bundle mBundle;
    private static final FirebaseService mFirebaseService = FirebaseService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_details);

        mBundle = getIntent().getBundleExtra(BUNDLE);

        mMethodTitle = (TextView)findViewById(R.id.title_method_details_activity_tv);
        mMethodAuthor = (TextView)findViewById(R.id.author_method_details_activity_tv);
        mMethodDescription = (TextView)findViewById(R.id.description_method_details_activity_tv);
        mRatingBar = (RatingBar)findViewById(R.id.rating_method_details_rb);
        mCommentsButton = (Button)findViewById(R.id.show_comments_method_details_btn);
        mMethodTitle.setText(mBundle.getString(METHOD_TITLE));
        mMethodAuthor.setText(mBundle.getString(METHOD_AUTHOR));
        mMethodDescription.setText(mBundle.getString(METHOD_DESCRIPTION));
        mRatingBar.setProgress(mBundle.getInt(METHOD_RATING));

        mDownloadImageView = (ImageView)findViewById(R.id.download_activity_method_details_iv);
        mRateImageView = (ImageView)findViewById(R.id.rate_activity_method_details_iv);
        mWriteComment = (ImageView)findViewById(R.id.write_comment_activity_method_details_iv);

        mDownloadImageView.setOnClickListener(this);
        mRateImageView.setOnClickListener(this);
        mWriteComment.setOnClickListener(this);
        mCommentsButton.setOnClickListener(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*0.8));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.download_activity_method_details_iv:
                mFirebaseService.existsMethod(this,mBundle.getString(KEY),mBundle.getString(METHOD_TITLE),mBundle.getString(METHOD_AUTHOR));
                break;
            case R.id.rate_activity_method_details_iv:
                Intent rateIntent = new Intent(this, RateMethodActivity.class);
                Bundle rateBundle = new Bundle();
                rateBundle.putString(KEY,mBundle.getString(KEY));
                rateIntent.putExtra(RATE_BUNDLE,rateBundle);
                startActivity(rateIntent);
                break;
            case R.id.write_comment_activity_method_details_iv:
                Intent writeIntent = new Intent(this, WriteCommentActivity.class);
                Bundle writeBundle = new Bundle();
                writeBundle.putString(KEY,mBundle.getString(KEY));
                writeIntent.putExtra(WRITE_COMMENT_BUNDLE,writeBundle);
                startActivity(writeIntent);
                break;
            case R.id.show_comments_method_details_btn:
                Intent showIntent = new Intent(this,ShowMethodCommentsActivity.class);
                Bundle showBundle = new Bundle();
                showBundle.putString(KEY,mBundle.getString(KEY));
                showBundle.putString(METHOD_TITLE,mBundle.getString(METHOD_TITLE));
                showIntent.putExtra(SHOW_COMMENT_BUNDLE,showBundle);
                startActivity(showIntent);
                break;
            default:break;
        }
    }
}
