package licence.meme.worrynot.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xander on 09.04.2017.
 */

public class Info {
    private String story;
    private List<String> steps;

    public Info(String story, List<String> steps) {
        this.story = story;
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (story != null ? !story.equals(info.story) : info.story != null) return false;
        return steps != null ? steps.equals(info.steps) : info.steps == null;

    }

    @Override
    public int hashCode() {
        int result = story != null ? story.hashCode() : 0;
        result = 31 * result + (steps != null ? steps.hashCode() : 0);
        return result;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<String> getSteps() {
        return steps;
    }


    public void setSteps(LinkedList<String> steps) {
        this.steps = steps;
    }
}
