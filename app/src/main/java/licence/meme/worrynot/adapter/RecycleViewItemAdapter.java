package licence.meme.worrynot.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import licence.meme.worrynot.R;
import licence.meme.worrynot.models.RecycleViewItem;

/**
 * Created by xander on 03.06.2017.
 */
//class decoration
public class RecycleViewItemAdapter extends RecyclerView.Adapter<RecycleViewItemAdapter.RecycleViewItemHolder> {

    private List<RecycleViewItem> methods;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback{
        void onItemClick(int position);
        void onFavouriteIconClick(int position);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback){
        this.itemClickCallback = itemClickCallback;
    }


    public RecycleViewItemAdapter(List<RecycleViewItem> methods, LayoutInflater inflater){
        this.inflater = inflater;
        this.methods = methods;
    }
    @Override
    public RecycleViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_item,parent,false);
        return new RecycleViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleViewItemHolder holder, int position) {
        RecycleViewItem item = methods.get(position);
        holder.methodTitle.setText(item.getItemName());
        holder.methodAuthor.setText(item.getItemAuthor());
//        holder.methodIcon.setImageResource(item.getMethodImageResourceID());
        if(item.isFavourite()){
            holder.favouriteIcon.setImageResource(R.drawable.ic_star_black_24dp);
        }else{
            holder.favouriteIcon.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return methods.size();
    }

    //view holder pattern
    class RecycleViewItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView methodTitle;
        private TextView methodAuthor;
        private ImageView methodIcon;
        private ImageView favouriteIcon;
        private View container;
        public RecycleViewItemHolder(View itemView) {
            super(itemView);

            methodTitle = (TextView)itemView.findViewById(R.id.title_recycle_item_tv);
            methodIcon = (ImageView)itemView.findViewById(R.id.icon_recycle_item_iv);
            methodAuthor = (TextView)itemView.findViewById(R.id.author_recycle_item_tv);
            favouriteIcon=(ImageView)itemView.findViewById(R.id.favourite_mark_recycle_item_iv);
            container = itemView.findViewById(R.id.container_recycle_item_ll);

            favouriteIcon.setOnClickListener(this);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.container_recycle_item_ll:
                    itemClickCallback.onItemClick(getAdapterPosition());
                    break;
                case R.id.favourite_mark_recycle_item_iv:
                    itemClickCallback.onFavouriteIconClick(getAdapterPosition());
                    break;
            }
        }
    }
}
