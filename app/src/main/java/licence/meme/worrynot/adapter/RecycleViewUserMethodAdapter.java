package licence.meme.worrynot.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.Method;

/**
 * Created by xander on 07.06.2017.
 */

public class RecycleViewUserMethodAdapter  extends RecyclerView.Adapter<RecycleViewUserMethodAdapter.RecycleViewMethodHolder> {
    private List<Method> methods;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback{
        void onItemClick(int position);
        void onActiveSelected(int position);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback){
        this.itemClickCallback = itemClickCallback;
    }


    public RecycleViewUserMethodAdapter(List<Method> methods, LayoutInflater inflater){
        this.inflater = inflater;
        this.methods = methods;
    }
    @Override
    public RecycleViewMethodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_method,parent,false);
        return new RecycleViewMethodHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleViewMethodHolder holder, int position) {
        Method item = methods.get(position);
        holder.methodTitle.setText(item.getMetadata().getName());
        holder.methodAuthor.setText(item.getMetadata().getAuthor());
    }

    @Override
    public int getItemCount() {
        return methods.size();
    }

    //view holder pattern
    class RecycleViewMethodHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView methodTitle;
        private TextView methodAuthor;
        private ImageView methodIcon;
        private ImageView activateIcon;
        private View container;
        public RecycleViewMethodHolder(View itemView) {
            super(itemView);

            methodTitle = (TextView)itemView.findViewById(R.id.title_row_method_tv);
            methodIcon = (ImageView)itemView.findViewById(R.id.icon_row_method_iv);
            methodAuthor = (TextView)itemView.findViewById(R.id.author_row_method_tv);
            activateIcon=(ImageView)itemView.findViewById(R.id.activate_method_row_method_iv);
            container = itemView.findViewById(R.id.container_row_method_rl);

            activateIcon.setOnClickListener(this);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.container_row_method_rl:
                    itemClickCallback.onItemClick(getAdapterPosition());
                    break;
                case R.id.activate_method_row_method_iv:
                    itemClickCallback.onActiveSelected(getAdapterPosition());
                    break;
            }
        }
    }
}
