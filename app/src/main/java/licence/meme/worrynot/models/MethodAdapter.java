package licence.meme.worrynot.models;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import licence.meme.worrynot.R;

/**
 * Created by xander on 22.05.2017.
 */

public class MethodAdapter extends ArrayAdapter {
    private final Context  mContext;
    private List<Method> mMethods;
    private int mResource;
    private LayoutInflater inflater;

    public MethodAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Method> objects) {
        super(context, resource, objects);
        this.mMethods = objects;
        this.mResource = resource;
        this.mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = inflater.inflate(R.layout.row_method,null);
//        TextView methodNameTextView;
//        TextView descriptionTextView;
//        methodNameTextView = (TextView)convertView.findViewById(R.id.name_method_row_method_tv);
//        descriptionTextView = (TextView)convertView.findViewById(R.id.description_row_method_tv);
//
//        methodNameTextView.setText(mMethods.get(position).getMetadata().getName());
//        descriptionTextView.setText(mMethods.get(position).getMetadata().getDescription());

        TextView  methodNameTextView = (TextView)v.findViewById(R.id.name_method_row_method_tv);
        TextView descriptionTextView = (TextView)v.findViewById(R.id.description_row_method_tv);
        Log.e("TAG----------",mMethods.get(position).getMetadata().getName());
        methodNameTextView.setText(mMethods.get(position).getMetadata().getName());
        descriptionTextView.setText(mMethods.get(position).getMetadata().getDescription());
        return v;
    }
}
