package com.philips.lighting.huebang;

import android.widget.TextView;

/**
 * Created by folti on 06/06/15.
 */
public class Player {
    private int lives;
    private int arrows;
    private TextView arrowNum;
    private TextView lifeNum;

    public Player(int lives, int arrows, TextView arrowNum, TextView lifeNum) {
        this.lives = lives;
        this.arrows = arrows;
        this.arrowNum = arrowNum;
        this.lifeNum = lifeNum;
        this.arrowNum.setText("A:" + arrows);
        this.lifeNum.setText("L:" + lives);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives, Lamp lamp) {
        this.lives = lives;
        this.lifeNum.setText("L:" + this.lives);
        if(this.lives <= 3 && lamp.onGoingEffect.name != "heart_beat") {
            lamp.setOnGoingEffect("heart_beat");
        }
        if(this.lives > 3 && lamp.onGoingEffect.name == "heart_beat") {
            lamp.setOnGoingEffect("heart_normal");
        }
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
        this.arrowNum.setText("A:" + arrows);
    }

    public void gotShot(Lamp lamp) {
        this.lives = this.lives - 1;
        this.lifeNum.setText("L:" + this.lives);
        lamp.setOnGoingEffect("shot", isHeartBeat());
    }

    public void gotArrow() {
        this.arrows = this.arrows + 1;
        this.arrowNum.setText("A:" + this.arrows);
    }

    public void gotBeer(Lamp lamp) {
        this.lives = this.lives + 1;
        this.lifeNum.setText("L:" + this.lives);
        if(isHeartBeat() && lamp.onGoingEffect.name != "heart_beat") {
            lamp.setOnGoingEffect("heart_beat");
        }
        if(isHeartBeat() && lamp.onGoingEffect.name == "heart_beat") {
            lamp.setOnGoingEffect("heart_normal");
        }
    }

    public void gotGgun(Player other1, Player other2) {
        other1.lives -= 1;
        other2.lives -= 1;
        other1.lifeNum.setText("L:" + other1.lives);
        other2.lifeNum.setText("L:" + other2.lives);

        this.setArrows(0);
    }

    private boolean isHeartBeat() {
        if(this.lives <= 3) {
            return true;
        }
        else return false;

    }
}
