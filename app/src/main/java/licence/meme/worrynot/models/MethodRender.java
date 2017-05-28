package licence.meme.worrynot.models;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import licence.meme.worrynot.R;

/**
 * Created by xander on 25.05.2017.
 */

public class MethodRender {
    private Method mMethod;
    public MethodRender(Method method){
        this.mMethod = method;
    }
    private class UpDownListener implements View.OnClickListener{
        private int mCurrent;
        private List<String> mSteps;
        private TextView mTextView;
        public UpDownListener(List<String> steps,TextView stepText){
            this.mCurrent = 0;
            this.mSteps = steps;
            this.mTextView = stepText;
        }
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.up_sample_method_btn){
                if(mCurrent < mSteps.size()-1){
                    mCurrent++;
                    mTextView.setText(mSteps.get(mCurrent));
                }
            }else if(v.getId() == R.id.down_sample_method_btn){
                if(mCurrent > 0){
                    mCurrent--;
                    mTextView.setText(mSteps.get(mCurrent));
                }
            }
        }
    }
    public void drawMethod(Context context, LayoutInflater inflater, View parentView){
        LinearLayout methodLinearLayout = (LinearLayout)parentView.findViewById(R.id.method_fragment_ll);
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.sample_method_render_custom_view, null, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.getChildAt(0);

        TextView nameTextView = (TextView)constraintLayout.getChildAt(0);
        nameTextView.setMovementMethod(new ScrollingMovementMethod());
        nameTextView.setTextColor(Color.parseColor("#AF5910"));
        TextView authorTextView = (TextView)constraintLayout.getChildAt(1);
        authorTextView.setMovementMethod(new ScrollingMovementMethod());
        authorTextView.setTextColor(Color.parseColor("#AF5910"));
        TextView descriptionTextView = (TextView)constraintLayout.getChildAt(2);
        descriptionTextView.setMovementMethod(new ScrollingMovementMethod());
        descriptionTextView.setTextColor(Color.parseColor("#AF5910"));
        TextView storyTextView = (TextView)constraintLayout.getChildAt(3);
        storyTextView.setMovementMethod(new ScrollingMovementMethod());
        storyTextView.setTextColor(Color.parseColor("#AF5910"));
        Button upButton = (Button) constraintLayout.getChildAt(4);
        TextView stepTextView = (TextView)constraintLayout.getChildAt(5);
        Button downButton = (Button)constraintLayout.getChildAt(6);

        List<String> steps = mMethod.getInfo().getSteps();
        //debug
//        for(int i = 0; i< steps.size();i++){
//            Log.e("MethodRender--->",steps.get(i));
//        }

        /**
         * Set Name
         */
        nameTextView.setText(mMethod.getMetadata().getName());

        /**
         * Set Author
         */
        authorTextView.setText(mMethod.getMetadata().getAuthor());

        /**
         * Set Description
         */
        descriptionTextView.setText(mMethod.getMetadata().getDescription());

        /**
         * Set Story
         */
        storyTextView.setText(mMethod.getInfo().getStory());


        stepTextView.setText(steps.get(0));
        UpDownListener upDownListener = new UpDownListener(steps,stepTextView);
        upButton.setOnClickListener(upDownListener);
        downButton.setOnClickListener(upDownListener);


        methodLinearLayout.addView(frameLayout);

    }

}
