package licence.meme.worrynot.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xander on 09.04.2017.
 */

public class Info {
    private String story;
//    private List<String> steps;
//    private List<String> questionnaire;

    public Map<String, String> getSteps() {
        return steps;
    }

    public void setSteps(Map<String, String> steps) {
        this.steps = steps;
    }

    public Map<String, String> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Map<String, String> questionnaire) {
        this.questionnaire = questionnaire;
    }

    private Map<String,String> steps;
    private Map<String,String> questionnaire;
    private Result results;
    public Info(){

    }
//    public Info(String story, List<String> steps, List<String> questionnaire, Result results) {
//        this.story = story;
//        this.steps = steps;
//        this.questionnaire = questionnaire;
//        this.results = results;
//    }


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

//    public List<String> getSteps() {
//        return steps;
//    }
//    public void setSteps(List<String> steps) {
//        this.steps = steps;
//    }
//
//    public List<String> getQuestionnaire() {
//        return questionnaire;
//    }
//
//    public void setQuestionnaire(List<String> questionnaire) {
//        this.questionnaire = questionnaire;
//    }

}
