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

    public Player() {
        this.lives = 0;
        this.arrows = 0;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives, Lamp lamp) {
        this.lives = lives;
        this.lifeNum.setText("L:" + this.lives);
        lamp.setOnGoingEffect(lamp.effects.heart_normal);
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
        if (this.lives < 0) this.lives = 0;
        this.lifeNum.setText("L:" + this.lives);
        lamp.setOnGoingEffect(lamp.effects.shot);
    }

    public void gotIndianAttack(Lamp lamp) {
        this.lives = this.lives - this.arrows;
        if (this.lives < 0) this.lives = 0;
        this.lifeNum.setText("L:" + this.lives);
        this.setArrows(0);
        lamp.setOnGoingEffect(lamp.effects.heart_arrow);
    }

    public void gotArrow() {
        this.arrows = this.arrows + 1;
        this.arrowNum.setText("A:" + this.arrows);
    }

    public void gotBeer(Lamp lamp) {
        this.lives = this.lives + 1;
        this.lifeNum.setText("L:" + this.lives);
        lamp.setOnGoingEffect(lamp.effects.heart_normal);
    }

    public void gotGgun(Player other1, Player other2, Lamp l1, Lamp l2) {
        other1.lives -= 1;
        if (other1.lives < 0) other1.lives = 0;
        other2.lives -= 1;
        if (other1.lives < 0) other1.lives = 0;
        other1.lifeNum.setText("L:" + other1.lives);
        other2.lifeNum.setText("L:" + other2.lives);
        l1.setOnGoingEffect(l1.effects.shot);
        l2.setOnGoingEffect(l1.effects.shot);

        this.setArrows(0);
    }

    public boolean isHeartBeat() {
        if(this.lives <= 2) {
            return true;
        }
        else return false;

    }
}
