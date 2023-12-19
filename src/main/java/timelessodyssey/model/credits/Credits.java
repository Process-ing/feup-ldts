package timelessodyssey.model.credits;

import timelessodyssey.model.game.elements.player.Player;

public class Credits {
    private int score;
    private final int deaths;

    private String[] messages;
    private String[] names;

    private final int seconds;
    private final int minutes;

    public Credits(Player player) {
        this.score = player.getStarCounter();
        this.deaths = player.getNumberOfDeaths();
        long duration = System.currentTimeMillis() - player.getBirthTime();
        this.seconds = (int) ((duration / 1000) % 60);
        this.minutes = (int) ((duration / 1000) / 60);

        String[] messages = new String[2];
        messages[0] = "Game Over!";
        messages[1] = "Thank you for playing :)";
        this.messages = messages;

        String[] names = new String[3];
        names[0] = "Bruno Oliveira";
        names[1] = "   Joao Mendes";
        names[2] = "Rodrigo Coelho";
        this.names = names;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getNames() {
        return names;
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
