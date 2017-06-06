package licence.meme.worrynot.models;

/**
 * Created by xander on 06.06.2017.
 */

public class RecycleViewComment {
    private String key;
    private Comment comment;

    public RecycleViewComment(String key, Comment comment){
        this.key = key;
        this.comment = comment;
    }
    public String getComment(){
        return comment.getMessage();
    }
    public String getID(){
        return comment.getId();
    }
}
