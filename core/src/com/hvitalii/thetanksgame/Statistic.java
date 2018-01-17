package com.hvitalii.thetanksgame;

import com.hvitalii.thetanksgame.Utils.ResourcesHandler;

public class Statistic {

    private int hiScore;
    private int[] playersScores;


    private ResourcesHandler resourcesHandler;
    public Statistic(ResourcesHandler resourcesHandler) {
        this.resourcesHandler = resourcesHandler;
        hiScore = 0;
        playersScores = new int[4];
        read();
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

    public void read() {
        if (resourcesHandler.getStatistic() != null) {
            String s = resourcesHandler.getStatistic().readString();
            hiScore = Integer.parseInt(s);
        }
    }

    public void write() {
        if (resourcesHandler.getStatistic() != null) {
            resourcesHandler.getStatistic().writeString(hiScore + "", false);
        }
    }
}
