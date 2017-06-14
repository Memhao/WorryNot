package licence.meme.worrynot.gui.recycleview;

/**
 * Created by xander on 03.06.2017.
 */

import licence.meme.worrynot.models.Method;

/**
 * Representation of Method to be displayed in RecycleView
 *
 */
public class RecycleViewItem {
//    private String methodTitle;
//    private String author;
//    private int methodImageResourceID;
    private boolean isFavourite = false;

    private Method method;
    private String key;


    public RecycleViewItem(Method method,String key){
        this.method = method;
        this.key =key;
    }
    public String getItemName(){
        return method.getMetadata().getName();
    }
    public String getItemAuthor(){
        return method.getMetadata().getAuthor();
    }
    public String getItemDescription(){
        return method.getMetadata().getDescription();
    }
    public int getItemRating(){
        return method.getMetadata().getRating();
    }
    public String getKey(){
        return key;
    }
    public Method getMethod(){
        return method;
    }
//    public String getMethodTitle() {
//        return methodTitle;
//    }
//
//    public void setMethodTitle(String methodTitle) {
//        this.methodTitle = methodTitle;
//    }
//
//    public int getMethodImageResourceID() {
//        return methodImageResourceID;
//    }
//
//    public void setMethodImageResourceID(int methodImageResourceID) {
//        this.methodImageResourceID = methodImageResourceID;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
