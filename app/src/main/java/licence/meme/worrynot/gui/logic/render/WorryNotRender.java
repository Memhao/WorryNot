package licence.meme.worrynot.gui.logic.render;

import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import licence.meme.worrynot.gui.logic.listener.UpDownMovementListener;
import licence.meme.worrynot.models.Method;

/**
 * Created by xander on 10.06.2017.
 */

public class WorryNotRender {
    private Method mWorryNot;
    private UpDownMovementListener mUpDownMovementListener;
    private TextView mStepTextView;
    public WorryNotRender(Method worryNot, TextView stepTextView){
        mWorryNot = worryNot;
        mStepTextView = stepTextView;
        List<String > steps = worryNot.getInfo().getSteps();
        mUpDownMovementListener = new UpDownMovementListener(steps,mStepTextView);

    }
    public void drawWorryNot(TextView worryNotName,TextView worryNotDescription, Button upButton, Button downButton){
        worryNotName.setText(mWorryNot.getMetadata().getName());
        worryNotDescription.setText(mWorryNot.getMetadata().getDescription());
        downButton.setOnClickListener(mUpDownMovementListener);
        upButton.setOnClickListener(mUpDownMovementListener);
    }
}
