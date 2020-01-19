public class Guesser {
    int path;
    boolean last = false;
    int sitFinal;
    public boolean done = false;
    public boolean liar = false;
    public int num = 0;
    //index[] 0: Completely North of Colorado 1: Completely East of Wyoming 2: Not Completely East of Wyoming 3: South of the Tennessee-Kentucky Border 4: Borders a Great Lake 5: Not North of New Jersey 6: North of New Jersey
    public String[][] states = {{"Washington", "Idaho", "Oregon", "Montana", "Wyoming", "North Dakota", "South Dakota", "Minnesota"}, {"Nebraska", "Kansas", "Oklahoma", "Iowa", "Missouri", "Louisiana"}, {"Colorado", "Utah", "New Mexico", "Arizona", "Nevada", "California"}, {"Tennessee", "North Carolina", "South Carolina", "Mississippi", "Alabama", "Georgia", "Florida"}, {"Wisconsin", "Illinois", "Indiana", "Ohio", "Pennsylvania", "New York"}, {"New Jersey", "Maryland", "West Virginia", "Kentucky", "Virginia"}, {"Connecticut", "Massachusetts", "Vermont", "New Hampshire", "Maine"}};
    public String[] questions = {"Is your state west of the Mississippi River?", "Is your state completely North of Colorado?", "Is your state completely East of Wyoming?", "Is your state North of the Tennessee-Kentucky Border?", "Does your state border a Great Lake?", "Is your state mostly North of New Jersey?"};
    public int[] questionIDs = {0, 1, 12, 2, 21, 212};
    public int[][] lastIDs = {{11, 0}, {121, 1}, {122, 2}, {22, 3}, {211, 4}, {2122, 5}, {2121, 6}};
    public Guesser() {
        path = 0;
        last = false;
    }
    public void startOver() {
        last = false;
        path = 0;
        done = false;
        num = 0;
        sitFinal = 0;
    }
    public String back() {
        int question = 0;
        System.out.println("1: " + path);
        if (! last || num == 0) {
            for (int i = 0; i < 3; i++) {
                if (((path - i) / 10) == (int) ((path - i) / 10)) {
                    path = (path - i) / 10;
                    break;
                }
            }
            System.out.println("2: " + path);
            for (int i = 0; i < questionIDs.length; i++) {
                if (questionIDs[i] == path) {
                    question = i;
                    last = false;
                    break;
                }
            }
            return questions[question];
        }
        else{
            if (! liar) {
                num--;
            }
            return "Is it " + states[sitFinal][num] + "?";
        }
    }
    public String calculate(int selection) {
        liar = false;
        path = (path * 10) + (selection + 1);
        int question = 0;
        if (! last) {
            for (int i = 0; i < questionIDs.length; i++) {
                if (questionIDs[i] == path) {
                    question = i;
                    last = false;
                    break;
                }
                else{
                    last = true;
                }
            }
            if (last) {
                for (int i = 0; i < lastIDs.length; i++) {
                    if (lastIDs[i][0] == path) {
                        sitFinal = lastIDs[i][1];
                        break;
                    }
                }
                return "Is it " + states[sitFinal][num] + "?";
            }
            return questions[question];
        }
        else{
            if (selection == 0) {
                done = true;
                return "Guessed It! Click the button to play again!";
            }
            else {
                num++;
                if (num >= states[sitFinal].length) {
                    liar = true;
                    num--;
                    return "You must've answered something wrong. Go back to reanswer a question.";
                }
                return "Is it " + states[sitFinal][num] + "?";

            }
        }
    }
}
