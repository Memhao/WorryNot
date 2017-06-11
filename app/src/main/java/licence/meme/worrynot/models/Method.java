package licence.meme.worrynot.models;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.constraint.ConstraintLayout;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import licence.meme.worrynot.R;

/**
 * Created by xander on 09.04.2017.
 */

public class Method {
    public Method(){

    }
    private Metadata metadata;
    private Info info;

    public Method(Metadata metadata, Info info) {
        this.metadata = metadata;
        this.info = info;
    }





    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;

        if (metadata != null ? !metadata.equals(method.metadata) : method.metadata != null)
            return false;
        return info != null ? info.equals(method.info) : method.info == null;

    }

    @Override
    public int hashCode() {
        int result = metadata != null ? metadata.hashCode() : 0;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    public LinearLayout drawMethod(Context context, LayoutInflater inflater, View parentView){

//        LinearLayout methodLinearLayout = (LinearLayout)parentView.findViewById(R.id.method_fragment_ll);
//
//        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.sample_method_render_custom_view, null, false);
//
//        ConstraintLayout constraintLayout = (ConstraintLayout) frameLayout.getChildAt(0);
//
//
//        TextView nameTextView = (TextView)constraintLayout.getChildAt(0);
//        nameTextView.setMovementMethod(new ScrollingMovementMethod());
//        nameTextView.setTextColor(Color.parseColor("#AF5910"));
//        TextView authorTextView = (TextView)constraintLayout.getChildAt(1);
//        authorTextView.setMovementMethod(new ScrollingMovementMethod());
//        authorTextView.setTextColor(Color.parseColor("#AF5910"));
//        TextView descriptionTextView = (TextView)constraintLayout.getChildAt(2);
//        descriptionTextView.setMovementMethod(new ScrollingMovementMethod());
//        descriptionTextView.setTextColor(Color.parseColor("#AF5910"));
//        TextView storyTextView = (TextView)constraintLayout.getChildAt(3);
//        storyTextView.setMovementMethod(new ScrollingMovementMethod());
//        storyTextView.setTextColor(Color.parseColor("#AF5910"));
//        ScrollView scrollView = (ScrollView) constraintLayout.getChildAt(4);
//
//
//        /**
//         * Set Name
//         */
//        nameTextView.setText(this.getMetadata().getName());
//
//        /**
//         * Set Author
//         */
//        authorTextView.setText(this.getMetadata().getAuthor());
//
//        /**
//         * Set Description
//         */
//        descriptionTextView.setText(this.getMetadata().getDescription());
//
//        /**
//         * Set Story
//         */
//        storyTextView.setText(this.getInfo().getStory());
//
//
//
//        LinearLayout wrapLayout = (LinearLayout) scrollView.getChildAt(0);
//
//
//        for(int i = 0; i < this.getInfo().getSteps().size(); ++i){
//            TextView textView = new TextView(context);
//            textView.setBackgroundResource(R.drawable.et1);
//            textView.setTextColor(Color.parseColor("#AF5910"));
//            String step = this.getInfo().getSteps().get(i);
//            textView.setText(step);
//
//            RatingBar ratingBar = new RatingBar(context,null,android.R.attr.ratingBarStyleIndicator);
//            ratingBar.setBackgroundResource(R.drawable.et1);
//            ratingBar.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT));
//            ratingBar.setIsIndicator(false);
//            LayerDrawable stars0 = (LayerDrawable)ratingBar.getProgressDrawable();
//            stars0.getDrawable(2).setColorFilter(Color.parseColor("#AF5910"), PorterDuff.Mode.SRC_ATOP);//fully selected
//            stars0.getDrawable(1).setColorFilter(Color.parseColor("#AF5910"), PorterDuff.Mode.SRC_ATOP);
//            stars0.getDrawable(0).setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);//not selected
//            ratingBar.setMax(5);
//            ratingBar.setNumStars(5);
//
//            wrapLayout.addView(textView);
//            wrapLayout.addView(ratingBar);
//        }
//
//        wrapLayout.addView(new TextView(context));
//        methodLinearLayout.addView(frameLayout);
//
//
//
//

        return  null;
    }

}
