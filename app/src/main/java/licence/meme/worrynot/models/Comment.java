package licence.meme.worrynot.models;

/**
 * Created by xander on 06.06.2017.
 */

public class Comment {
    private String id;
    private String message;
    public Comment(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
