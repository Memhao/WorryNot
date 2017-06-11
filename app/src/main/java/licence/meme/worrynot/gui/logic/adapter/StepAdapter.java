package licence.meme.worrynot.gui.logic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.Step;

/**
 * Created by xander on 10.06.2017.
 */

public class StepAdapter extends BaseAdapter{
    private List<String> mSteps;
    private static LayoutInflater INFLATER = null;

    public StepAdapter(Context context, List<String> steps){
        INFLATER = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSteps = steps;
    }
    @Override
    public int getCount() {
        return mSteps.size();
    }

    @Override
    public Object getItem(int position) {
        return mSteps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View outView = convertView;
        if(outView == null){
            outView = INFLATER.inflate(R.layout.row_step,null);
        }
        TextView step = (TextView)outView.findViewById(R.id.step_row_step_tv);
        if(mSteps.get(position) != null)
        {
            step.setText(mSteps.get(position));
        }
        return outView;
    }
}
