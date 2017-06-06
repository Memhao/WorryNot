package licence.meme.worrynot.models;

/**
 * Created by xander on 06.06.2017.
 */

public class Question {
    private String questionID;
    private String question;

    public Question(){

    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
