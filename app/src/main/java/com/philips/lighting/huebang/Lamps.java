package com.philips.lighting.huebang;

import android.widget.TextView;

import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by folti on 07/06/15.
 */
public class Lamps {
    public Lamp p1light;
    public Lamp p2light;
    public Lamp p3light;
    public Lamp top_light;
    public Lamp ambi11light;
    public Lamp ambi12light;
    public Lamp ambi21light;
    public Lamp ambi22light;
    public Lamp sun_light;
    //    private Timer myTimer;
//    private Timer myTimer1;
    public int task_timer;
    private boolean pause = false;
    private boolean heart_beat_reference_on = false;
    private Lamp heart_beat_reference;
    private Runnable timer = new Runnable() {
        public void run() {
            TimerMethod();
        }
    };
    private Runnable timer1 = new Runnable() {
        public void run() {
            TimerMethod1();
        }
    };
    private Runnable timer2 = new Runnable() {
        public void run() {
            TimerMethod2();
        }
    };
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

    private TextView nightTimerView = null;
    private TextView indianTimerView = null;
    public int nightTimer = 0;
    public int indianTimer = 0;

    public Lamps(List<PHLight> lights, TextView nightTimer, TextView indianTimer) {
        this.p1light = new Lamp();
        this.p2light = new Lamp();
        this.p3light = new Lamp();
        this.top_light = new Lamp();
        this.ambi11light = new Lamp();
        this.ambi12light = new Lamp();
        this.ambi21light = new Lamp();
        this.ambi22light = new Lamp();
        this.sun_light = new Lamp();
        this.heart_beat_reference = new Lamp();

        this.nightTimerView = nightTimer;
        this.indianTimerView = indianTimer;
        this.nightTimer = 0;
        this.indianTimer = 0;

        for (PHLight light : lights) {
            switch (light.getName()) {
                case "P1 lamp":
                    this.p1light.source = new PHLight(light);
                    this.p1light.timer_state = 0;
                    this.p1light.index = "P1 lamp";
                    this.p1light.nextFrameIndex = 0;
                    this.p1light.nextFrameStartTime = 0;
                    this.p1light.onGoingEffect = new Effect();
                    this.p1light.player = MyApplicationActivity.getInstance().p1;
                    break;
                case "P2 lamp":
                    this.p2light.source = light;
                    this.p2light.timer_state = 0;
                    this.p2light.index = "P2 lamp";
                    this.p2light.nextFrameIndex = 0;
                    this.p2light.nextFrameStartTime = 0;
                    this.p2light.onGoingEffect = new Effect();
                    this.p2light.player = MyApplicationActivity.getInstance().p2;
                    break;
                case "P3 lamp":
                    this.p3light.source = light;
                    this.p3light.timer_state = 0;
                    this.p3light.index = "P3 lamp";
                    this.p3light.nextFrameIndex = 0;
                    this.p3light.nextFrameStartTime = 0;
                    this.p3light.onGoingEffect = new Effect();
                    this.p3light.player = MyApplicationActivity.getInstance().p3;
                    break;
                case "Top lamp":
                    this.top_light.source = light;
                    this.top_light.timer_state = 0;
                    this.top_light.index = "Top lamp";
                    this.top_light.nextFrameIndex = 0;
                    this.top_light.nextFrameStartTime = 0;
                    this.top_light.onGoingEffect = new Effect();
                    break;
                case "Sun lamp":
                    this.sun_light.source = light;
                    this.sun_light.timer_state = 0;
                    this.sun_light.index = "Sun lamp";
                    this.sun_light.nextFrameIndex = 0;
                    this.sun_light.nextFrameStartTime = 0;
                    this.sun_light.onGoingEffect = new Effect();
                    break;
                case "Ambi 11":
                    this.ambi11light.source = light;
                    this.ambi11light.timer_state = 0;
                    this.ambi11light.index = "Ambi 11";
                    this.ambi11light.nextFrameIndex = 0;
                    this.ambi11light.nextFrameStartTime = 0;
                    this.ambi11light.onGoingEffect = new Effect();
                    break;
                case "Ambi 12":
                    this.ambi12light.source = light;
                    this.ambi12light.timer_state = 0;
                    this.ambi12light.index = "Ambi 12";
                    this.ambi12light.nextFrameIndex = 0;
                    this.ambi12light.nextFrameStartTime = 0;
                    this.ambi12light.onGoingEffect = new Effect();
                    break;
                case "Ambi 21":
                    this.ambi21light.source = light;
                    this.ambi21light.timer_state = 0;
                    this.ambi21light.index = "Ambi 21";
                    this.ambi21light.nextFrameIndex = 0;
                    this.ambi21light.nextFrameStartTime = 0;
                    this.ambi21light.onGoingEffect = new Effect();
                    break;
                case "Ambi 22":
                    this.ambi22light.source = light;
                    this.ambi22light.timer_state = 0;
                    this.ambi22light.index = "Ambi 22";
                    this.ambi22light.nextFrameIndex = 0;
                    this.ambi22light.nextFrameStartTime = 0;
                    this.ambi22light.onGoingEffect = new Effect();
                    break;
                default:
                    break;
            }
        }

        this.task_timer = 0;
        this.heart_beat_reference.timer_state = 0;
        this.heart_beat_reference.nextFrameIndex = 0;
        this.heart_beat_reference.nextFrameStartTime = 0;
        this.heart_beat_reference.index = "heart_beat_reference";
        this.heart_beat_reference.setOnGoingEffect(this.top_light.effects.heart_beat);

        scheduler.scheduleAtFixedRate(timer, 0, 110, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(timer1, 5, 110, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(timer2, 10, 110, TimeUnit.MILLISECONDS);

    }

    private void TimerMethod() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.
        this.heart_beat_sync();
        if (this.heart_beat_reference.onGoingEffect.name != null)
            this.heart_beat_reference.sendNextFrame();
        //if the lamp is connected and there's an ongoing effect
        if (this.p1light.onGoingEffect.name != null && this.p1light.source != null)
            this.p1light.sendNextFrame();
        if (this.p2light.onGoingEffect.name != null && this.p2light.source != null)
            this.p2light.sendNextFrame();
        if (this.p3light.onGoingEffect.name != null && this.p3light.source != null)
            this.p3light.sendNextFrame();
//        if(this.top_light.onGoingEffect.name != null && this.top_light.source != null) this.top_light.sendNextFrame();

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        //this.runOnUiThread(Timer_Tick);
    }

    private void TimerMethod1() {
        //night timing
        if (this.ambi11light.night_task_on) {
            this.nightTimer += 1;
            if (this.nightTimer % 10 == 0)
                MyApplicationActivity.getInstance().runOnUiThread(Timer_Tick);
        } else this.nightTimer = 0;

        //indian timing
        if (this.ambi11light.indian_task_on) {
            this.indianTimer += 1;
            if (this.indianTimer % 10 == 0)
                MyApplicationActivity.getInstance().runOnUiThread(Timer_Tick);
        } else this.indianTimer = 0;

        //needed another thread, otherwise it didn't always turn (off/) back on during sunrise effect
        if (this.top_light.onGoingEffect.name != null && this.top_light.source != null)
            this.top_light.sendNextFrame();
        if (this.sun_light.onGoingEffect.name != null && this.sun_light.source != null)
            this.sun_light.sendNextFrame();


        //We call the method that will work with the UI
        //through the runOnUiThread method.
    }

    private void TimerMethod2() {
        if (this.ambi11light.onGoingEffect.name != null && this.ambi11light.source != null)
            this.ambi11light.sendNextFrame();
        if (this.ambi12light.onGoingEffect.name != null && this.ambi12light.source != null)
            this.ambi12light.sendNextFrame();
        if (this.ambi21light.onGoingEffect.name != null && this.ambi21light.source != null)
            this.ambi21light.sendNextFrame();
        if (this.ambi22light.onGoingEffect.name != null && this.ambi22light.source != null)
            this.ambi22light.sendNextFrame();

        //We call the method that will work with the UI
        //through the runOnUiThread method.
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            //This method runs in the same thread as the UI.
            //Do something to the UI thread here
            if (Lamps.this.ambi11light.night_task_on) {
                Lamps.this.nightTimerView.setText("T:" + Lamps.this.nightTimer / 10);
            }
            if (Lamps.this.ambi11light.indian_task_on) {
                Lamps.this.indianTimerView.setText("T:" + Lamps.this.indianTimer / 10);
            }
        }
    };

    public void heart_beat_sync() {
        if (this.p1light.heart_beat_started) {
            this.p1light.nextFrameIndex = this.heart_beat_reference.nextFrameIndex;
            this.p1light.nextFrameStartTime = this.heart_beat_reference.nextFrameStartTime;
            this.p1light.timer_state = this.heart_beat_reference.timer_state;
            this.p1light.heart_beat_started = false;
        }
        if (this.p2light.heart_beat_started) {
            this.p2light.nextFrameIndex = this.heart_beat_reference.nextFrameIndex;
            this.p2light.nextFrameStartTime = this.heart_beat_reference.nextFrameStartTime;
            this.p2light.timer_state = this.heart_beat_reference.timer_state;
            this.p2light.heart_beat_started = false;
        }
        if (this.p3light.heart_beat_started) {
            this.p3light.nextFrameIndex = this.heart_beat_reference.nextFrameIndex;
            this.p3light.nextFrameStartTime = this.heart_beat_reference.nextFrameStartTime;
            this.p3light.timer_state = this.heart_beat_reference.timer_state;
            this.p3light.heart_beat_started = false;
        }
    }

    public void setOngoingAmbiEffect(Effect effect) {
        this.ambi11light.timer_state = 0;
        this.ambi11light.nextFrameIndex = 0;
        this.ambi11light.nextFrameStartTime = 0;
        this.ambi11light.setOnGoingEffect(effect);
    }
}