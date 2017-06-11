package licence.meme.worrynot.gui.logic.listener;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import licence.meme.worrynot.R;

/**
 * Created by xander on 10.06.2017.
 */

public class UpDownMovementListener implements View.OnClickListener{
    private int mCurrent;
    private List<String> mSteps;
    private TextView mTextView;
    public UpDownMovementListener(List<String> steps,TextView stepText){
        this.mCurrent = 0;
        this.mSteps = steps;
        this.mTextView = stepText;
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.up_home_fragment_btn){
            if(mCurrent < mSteps.size()-1){
                mCurrent++;
                mTextView.setText(mSteps.get(mCurrent));
            }
        }else if(v.getId() == R.id.down_home_fragment_btn){
            if(mCurrent > 0){
                mCurrent--;
                mTextView.setText(mSteps.get(mCurrent));
            }
        }
    }
}
