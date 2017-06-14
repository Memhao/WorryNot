package licence.meme.worrynot.gui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import licence.meme.worrynot.R;

/**
 * Created by xander on 08.06.2017.
 */

public class RecycleViewEntryAdapter extends RecyclerView.Adapter<RecycleViewEntryAdapter.EntryHolder>{
    private List<String> entries;
    private LayoutInflater inflater;
    private RecyclerView mRecyclerView;
    private Activity mActivity;
    public RecycleViewEntryAdapter(LayoutInflater inflater,RecyclerView recyclerView,Activity activity){
        this.entries = new ArrayList<>();
        this.inflater = inflater;
        this.mRecyclerView = recyclerView;
        this.mActivity = activity;
        addEntry();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(newHelperCallback());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }



    private ItemTouchHelper.Callback newHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleEntryTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        moveEntry(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                        return true;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        deleteEntry(viewHolder.getAdapterPosition());
                    }
                };
        return simpleEntryTouchCallback;
    }

    public void moveEntry(int oldPosition, int newPosition){
        Collections.swap(entries,oldPosition,newPosition);
        this.notifyItemMoved(oldPosition,newPosition);
    }
    public void deleteEntry(int position){
            entries.remove(position);
            this.notifyItemRemoved(position);
    }

    public void addEntry(){
        String newEntry = new String("");
        entries.add(newEntry);
        this.notifyItemInserted(entries.indexOf(newEntry));
    }
    @Override
    public EntryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_enrty,parent,false);
        return new EntryHolder(view);
    }

    @Override
    public void onBindViewHolder(EntryHolder holder, final int position) {
    }



    @Override
    public int getItemCount() {
        return entries.size();
    }

    public List<String>getEntries(){
        return entries;
    }

    public class EntryHolder extends RecyclerView.ViewHolder {
        private ImageView addButton;
        private EditText entryEditText;
        public EntryHolder(View itemView){
            super(itemView);
            addButton = (ImageView)itemView.findViewById(R.id.add_row_entry_iv);
            entryEditText = (EditText)itemView.findViewById(R.id.text_row_entry_fragment_et);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addEntry();
                }
            });
        }
        public EditText getEntryEditText(){
            return entryEditText;
        }
    }
}
