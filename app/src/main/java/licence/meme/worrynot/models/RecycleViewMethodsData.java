package licence.meme.worrynot.models;

/**
 * Created by xander on 03.06.2017.
 */

import java.util.ArrayList;
import java.util.List;

import licence.meme.worrynot.R;

/**
 * This class should contains all methods from fire database
 */
public class RecycleViewMethodsData {
    private static final String[] titles  = {"Nothingness cannot be defined", "The softest thing cannot be snapped",
            "be like water, my friend."} ;
    private static final int[] icons = {android.R.drawable.ic_dialog_alert, android.R.drawable.ic_menu_add,
            android.R.drawable.ic_menu_delete};

    private static final String[] authors = {"John Cena", "Smith Jr.","JFK"};
    private static final int star_back_icon = R.drawable.ic_star_black_24dp;
    private static final int star_border_icon = R.drawable.ic_star_border_black_24dp;
    public static List <RecycleViewItem> getListData() {
        List<RecycleViewItem> data = new ArrayList<>();

        //Repeat process 4 times, so that we have enough data to demonstrate a scrollable
        //RecyclerView
        for (int x = 0; x  < 4; x++) {
            //create ListItem with dummy data, then add them to our List
            for (int i = 0; i  < titles.length && i  < authors.length; i++) {
//                RecycleViewItem item = new RecycleViewItem();
//                item.setAuthor(authors[i]);
//                item.setMethodTitle(titles[i]);
//                data.add(item);
            }
        }
        return data;
    }
}
