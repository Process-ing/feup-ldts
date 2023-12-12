package timelessodyssey.model.credits;

public class Credits {

    private int score;
    private int deaths;

    private int seconds;
    private int minutes;

    public Credits(int starCounter, int numberOfDeaths, long duration) {
        this.score = starCounter;
        this.deaths = numberOfDeaths;
        this.seconds = (int) ((duration / 1000) % 60);
        this.minutes = (int) ((duration / 1000) / 60);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String[] getMessages() {
        String[] strings = new String[2];
        strings[0] = "Game Over!";
        strings[1] = "Thank you for playing :)";
        return strings;
    }

    public String[] getNames() {
        String[] strings = new String[3];
        strings[0] = "Bruno Oliveira";
        strings[1] = "   Joao Mendes";
        strings[2] = "Rodrigo Coelho";
        return strings;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }
}
