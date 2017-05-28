package licence.meme.worrynot.models;

/**
 * Created by xander on 09.04.2017.
 */

public class Metadata {
    private String author;
    private String description;
    private String name;
    private int rating;

    public Metadata(String author, String description, String name) {
        this.author = author;
        this.description = description;
        this.name = name;
    }

    public Metadata(){

    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metadata metadata = (Metadata) o;

        if (author != null ? !author.equals(metadata.author) : metadata.author != null)
            return false;
        if (description != null ? !description.equals(metadata.description) : metadata.description != null)
            return false;
        return name != null ? name.equals(metadata.name) : metadata.name == null;

    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
