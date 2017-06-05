package licence.meme.worrynot.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xander on 09.04.2017.
 */

public class Info {
    private String story;
    private List<String> steps;
    private List<String> questionnaire;
    private Result results;
    public Info(){

    }
    public Info(String story, List<String> steps, List<String> questionnaire, Result results) {
        this.story = story;
        this.steps = steps;
        this.questionnaire = questionnaire;
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (story != null ? !story.equals(info.story) : info.story != null) return false;
        if (steps != null ? !steps.equals(info.steps) : info.steps != null) return false;
        if (questionnaire != null ? !questionnaire.equals(info.questionnaire) : info.questionnaire != null)
            return false;
        return results != null ? results.equals(info.results) : info.results == null;

    }

    @Override
    public int hashCode() {
        int result = story != null ? story.hashCode() : 0;
        result = 31 * result + (steps != null ? steps.hashCode() : 0);
        result = 31 * result + (questionnaire != null ? questionnaire.hashCode() : 0);
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
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

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public List<String> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(List<String> questionnaire) {
        this.questionnaire = questionnaire;
    }

}
