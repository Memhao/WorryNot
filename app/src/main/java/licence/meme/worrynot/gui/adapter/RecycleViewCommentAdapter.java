package licence.meme.worrynot.gui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import licence.meme.worrynot.R;
import licence.meme.worrynot.gui.recycleview.RecycleViewComment;

/**
 * Created by xander on 06.06.2017.
 */

public class RecycleViewCommentAdapter extends RecyclerView.Adapter<RecycleViewCommentAdapter.RecycleViewCommentHolder> {
    private List<RecycleViewComment> comments;
    private LayoutInflater inflater;

    public RecycleViewCommentAdapter(List<RecycleViewComment> comments, LayoutInflater inflater){
        this.comments = comments;
        this.inflater = inflater;
    }

    @Override
    public RecycleViewCommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_comment,parent,false);
        return new RecycleViewCommentHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleViewCommentHolder holder, int position) {
        RecycleViewComment item = comments.get(position);
        holder.commentTextView.setText(item.getComment());
        holder.authorTextView.setText(item.getID());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class RecycleViewCommentHolder extends RecyclerView.ViewHolder{
        private TextView commentTextView;
        private TextView authorTextView;
        public RecycleViewCommentHolder(View itemView) {
            super(itemView);
            commentTextView = (TextView)itemView.findViewById(R.id.comment_recycle_comment_tv);
            authorTextView = (TextView)itemView.findViewById(R.id.author_recycle_comment_tv);
        }
    }
}
