package com.hvitalii.thetanksgame;

public class Statistic {

    private int hiScore;
    private int[] playersScores;
    public Statistic() {
        hiScore = 20000;
        playersScores = new int[4];
    }

    public int getHiScore() {
        return hiScore;
    }

    public void setHiScore(int hiScore) {
        this.hiScore = hiScore;
    }

    public int[] getPlayersScores() {
        return playersScores;
    }

    public void setPlayersScores(int[] playersScores) {
        this.playersScores = playersScores;
    }
}
