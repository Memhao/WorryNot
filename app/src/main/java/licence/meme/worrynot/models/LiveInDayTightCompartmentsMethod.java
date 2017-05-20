package licence.meme.worrynot.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xander on 16.05.2017.
 */

public class LiveInDayTightCompartmentsMethod {

        private String name ;
        private String author;
        private String story;
        private String description;
        private List<String > steps = new LinkedList<String>();

        public LiveInDayTightCompartmentsMethod(){
                name = "Live in Day-tight Compartiments";
                author = "Sir William Osier";
                story = new String("Story"+"\n"+"Sir William Osier had crossed the Atlantic on a great ocean liner where the captain standing on the bridge,"+
                        " could press a button and-presto! - there was a clanging machinery and various parts of the ship "+
                        "were immediately shut off from one another= shut off into watertight compartments")
                ;
                description = new String("Our main business is not to see what lies dimly at a distance,but to do what lies clearly at hand.");
                steps.add("Do I tend to put off living in the present in order to worry about the future.");
                steps.add("Do I yearn for some 'magical rose garden over the horizon'");
                steps.add("Do I sometimes embitter the present by regretting things that happened in the past-that are over and done with?");
                steps.add("Do I get up in the morning determined to 'Seize the day'- to get the utmost out of these twenty for hours?");
                steps.add("Can I get more out of life by 'living in day-tight compartments'");

        }

        public String getName() {
                return name;
        }

        public String getAuthor() {
                return author;
        }

        public String getStory() {
                return story;
        }

        public String getDescription() {
                return description;
        }

        public List<String> getSteps() {
                return steps;
        }
}
