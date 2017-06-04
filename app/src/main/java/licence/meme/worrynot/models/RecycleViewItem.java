package licence.meme.worrynot.models;

/**
 * Created by xander on 03.06.2017.
 */

/**
 * Representation of Method to be displayed in RecycleView
 */
public class RecycleViewItem {
    private String methodTitle;
    private String author;
    private int methodImageResourceID;
    private boolean isFavourite = false;

    public String getMethodTitle() {
        return methodTitle;
    }

    public void setMethodTitle(String methodTitle) {
        this.methodTitle = methodTitle;
    }

    public int getMethodImageResourceID() {
        return methodImageResourceID;
    }

    public void setMethodImageResourceID(int methodImageResourceID) {
        this.methodImageResourceID = methodImageResourceID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
