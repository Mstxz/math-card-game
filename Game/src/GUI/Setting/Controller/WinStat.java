package GUI.Setting.Controller;

import java.io.Serializable;

public class WinStat implements Serializable {
    private int win;
    private int lose;
    private int play;

    public WinStat(){
        win = 0;
        lose = 0;
        play = 0;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
    }
}
