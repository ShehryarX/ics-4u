package Logic;

public class Result {
    boolean possible;
    int score;
    String word;

    public String getWord() {
        return word;
    }

    public Result(boolean possible, int score, String word) {
        this.possible = possible;
        this.score = score;
        this.word = word;
    }

    public Result(boolean possible) {
        this.possible = possible;
        this.score = 0;
        this.word = "";
    }

    public boolean isPossible() {
        return possible;
    }

    public int getScore() {
        return score;
    }
}
