package licence.meme.worrynot.config;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import licence.meme.worrynot.models.Comment;
import licence.meme.worrynot.models.Info;
import licence.meme.worrynot.models.Metadata;
import licence.meme.worrynot.models.Method;
import licence.meme.worrynot.models.Result;
import licence.meme.worrynot.models.Step;

/**
 * Created by xander on 11.06.2017.
 */

public class DefaultWorryNotFactory {

    public static Method getLiveInDayTightCompartmentsWorryNot(){
        Metadata metadata = new Metadata();
        metadata.setAuthor(LiveInDayTightCompartments.AUTHOR);
        metadata.setName(LiveInDayTightCompartments.NAME);
        metadata.setDescription(LiveInDayTightCompartments.DESCRIPTION);
        metadata.setRating(LiveInDayTightCompartments.RATING);
        List<Comment> comments= new ArrayList<Comment>();
        Comment comment = new Comment();
        comment.setMessage(LiveInDayTightCompartments.COMMENT1);
        comment.setId(LiveInDayTightCompartments.AUTHOR);
        comments.add(comment);

        Info info = new Info();
        Result result = new Result();
        result.setLow(LiveInDayTightCompartments.RESULT_LOW);
        result.setMedium(LiveInDayTightCompartments.RESULT_MEDIUM);
        result.setHigh(LiveInDayTightCompartments.RESULT_HIGH);
        info.setResults(result);

        Map<String,String> steps = new HashMap<>();
        steps.put("S111",LiveInDayTightCompartments.STEP1);
        steps.put("S222",LiveInDayTightCompartments.STEP2);
        steps.put("S333",LiveInDayTightCompartments.STEP3);
        info.setSteps(steps);

        Map<String,String> questions = new HashMap<>();
        questions.put("Q1111",LiveInDayTightCompartments.QUESTION1);
        questions.put("Q222",LiveInDayTightCompartments.QUESTION2);
        questions.put("Q3333",LiveInDayTightCompartments.QUESTION3);
        info.setQuestionnaire(questions);

        Method method = new Method(metadata,info);
        return method;
    }


    public static Method getAnalyzeAndSolveWorryProblemsWorryNot(){
        Metadata metadata = new Metadata();
        metadata.setAuthor(AnalyzeAndSolveWorryProblems.AUTHOR);
        metadata.setName(AnalyzeAndSolveWorryProblems.NAME);
        metadata.setDescription(AnalyzeAndSolveWorryProblems.DESCRIPTION);
        metadata.setRating(AnalyzeAndSolveWorryProblems.RATING);
        List<Comment> comments= new ArrayList<Comment>();
        Comment comment = new Comment();
        comment.setMessage(AnalyzeAndSolveWorryProblems.COMMENT1);
        comment.setId(AnalyzeAndSolveWorryProblems.AUTHOR);
        comments.add(comment);

        Info info = new Info();
        Result result = new Result();
        result.setLow(AnalyzeAndSolveWorryProblems.RESULT_LOW);
        result.setMedium(AnalyzeAndSolveWorryProblems.RESULT_MEDIUM);
        result.setHigh(AnalyzeAndSolveWorryProblems.RESULT_HIGH);
        info.setResults(result);

        Map<String,String> steps = new HashMap<>();
        steps.put("CXX",AnalyzeAndSolveWorryProblems.STEP1);
        steps.put("BXXX",AnalyzeAndSolveWorryProblems.STEP2);
        steps.put("VZZ",AnalyzeAndSolveWorryProblems.STEP3);
        steps.put("AXX",AnalyzeAndSolveWorryProblems.STEP4);
        info.setSteps(steps);

        Map<String,String> questions = new HashMap<>();
        questions.put("Q11111",AnalyzeAndSolveWorryProblems.QUESTION1);
        questions.put("Q22222",AnalyzeAndSolveWorryProblems.QUESTION2);
        questions.put("Q33333",AnalyzeAndSolveWorryProblems.QUESTION3);
        info.setQuestionnaire(questions);

        Method method = new Method(metadata,info);
        return method;
    }

    public static Method getEliminateFiftyPerCentWorryNot(){
        Metadata metadata = new Metadata();
        metadata.setAuthor(EliminateFiftyPerCent.AUTHOR);
        metadata.setName(EliminateFiftyPerCent.NAME);
        metadata.setDescription(AnalyzeAndSolveWorryProblems.DESCRIPTION);
        metadata.setRating(EliminateFiftyPerCent.RATING);
        List<Comment> comments= new ArrayList<Comment>();
        Comment comment = new Comment();
        comment.setMessage(EliminateFiftyPerCent.COMMENT1);
        comment.setId(EliminateFiftyPerCent.AUTHOR);
        comments.add(comment);

        Info info = new Info();
        Result result = new Result();
        result.setLow(EliminateFiftyPerCent.RESULT_LOW);
        result.setMedium(EliminateFiftyPerCent.RESULT_MEDIUM);
        result.setHigh(EliminateFiftyPerCent.RESULT_HIGH);
        info.setResults(result);

        Map<String,String> steps = new HashMap<>();
        steps.put("s11",AnalyzeAndSolveWorryProblems.STEP1);
        steps.put("s22",AnalyzeAndSolveWorryProblems.STEP2);
        steps.put("S333",AnalyzeAndSolveWorryProblems.STEP3);
        steps.put("s444",AnalyzeAndSolveWorryProblems.STEP4);
        info.setSteps(steps);

        Map<String,String> questions = new HashMap<>();
        questions.put("q1",AnalyzeAndSolveWorryProblems.QUESTION1);
        questions.put("q2",AnalyzeAndSolveWorryProblems.QUESTION2);
        questions.put("q3",AnalyzeAndSolveWorryProblems.QUESTION3);
        info.setQuestionnaire(questions);

        Method method = new Method(metadata,info);
        return method;
    }
}
