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
    public Lamp ambi1light;
    public Lamp ambi2light;
    public Lamp ambi3light;
//    private Timer myTimer;
//    private Timer myTimer1;
    public int task_timer;
    private boolean pause = false;
    private boolean heart_beat_reference_on = false;
    private Lamp heart_beat_reference;
    private  Runnable timer = new Runnable() {
        public void run() {
            TimerMethod();
        }
    };
    private  Runnable timer1 = new Runnable() {
        public void run() {
            TimerMethod1();
        }
    };
    private  Runnable timer2 = new Runnable() {
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
        this.ambi1light = new Lamp();
        this.ambi2light = new Lamp();
        this.ambi3light = new Lamp();
        this.heart_beat_reference = new Lamp();

        this.task_timer = 0;
        this.heart_beat_reference.timer_state = 0;
        this.heart_beat_reference.nextFrameIndex = 0;
        this.heart_beat_reference.nextFrameStartTime = 0;
        this.heart_beat_reference.index = "heart_beat_reference";
        this.heart_beat_reference.setOnGoingEffect("heart_beat");

        this.nightTimerView = nightTimer;
        this.indianTimerView = indianTimer;
        this.nightTimer = 0;
        this.indianTimer = 0;

        for(PHLight light:lights) {
            switch (light.getName()) {
                case "P1 lamp":
                    this.p1light.source = new PHLight(light);
                    this.p1light.timer_state = 0;
                    this.p1light.index = "P1 lamp";
                    this.p1light.nextFrameIndex = 0;
                    this.p1light.nextFrameStartTime = 0;
                    this.p1light.onGoingEffect = new Effect();
                    break;
                case "P2 lamp":
                    this.p2light.source = light;
                    this.p2light.timer_state = 0;
                    this.p2light.index = "P2 lamp";
                    this.p2light.nextFrameIndex = 0;
                    this.p2light.nextFrameStartTime = 0;
                    this.p2light.onGoingEffect = new Effect();
                    break;
                case "P3 lamp":
                    this.p3light.source = light;
                    this.p3light.timer_state = 0;
                    this.p3light.index = "P3 lamp";
                    this.p3light.nextFrameIndex = 0;
                    this.p3light.nextFrameStartTime = 0;
                    this.p3light.onGoingEffect = new Effect();
                    break;
                case "Top lamp":
                    this.top_light.source = light;
                    this.top_light.timer_state = 0;
                    this.top_light.index = "Top lamp";
                    this.top_light.nextFrameIndex = 0;
                    this.top_light.nextFrameStartTime = 0;
                    this.top_light.onGoingEffect = new Effect();
                    break;
                case "Ambient 1":
                    this.ambi1light.source = light;
                    this.ambi1light.timer_state = 0;
                    this.ambi1light.index = "Ambient 1";
                    this.ambi1light.nextFrameIndex = 0;
                    this.ambi1light.nextFrameStartTime = 0;
                    this.ambi1light.onGoingEffect = new Effect();
                    break;
                case "Ambient 2":
                    this.ambi2light.source = light;
                    this.ambi2light.timer_state = 0;
                    this.ambi2light.index = "Ambient 2";
                    this.ambi2light.nextFrameIndex = 0;
                    this.ambi2light.nextFrameStartTime = 0;
                    this.ambi2light.onGoingEffect = new Effect();
                    break;
                case "Ambient 3":
                    this.ambi3light.source = light;
                    this.ambi3light.timer_state = 0;
                    this.ambi3light.index = "Ambient 3";
                    this.ambi3light.nextFrameIndex = 0;
                    this.ambi3light.nextFrameStartTime = 0;
                    this.ambi3light.onGoingEffect = new Effect();
                    break;
                default:
                    break;
            }
        }
        scheduler.scheduleAtFixedRate(timer, 0, 110, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(timer1, 5, 110, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(timer2, 5, 110, TimeUnit.MILLISECONDS);
    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.
        this.heart_beat_sync();
        if(this.heart_beat_reference.onGoingEffect.name != null) this.heart_beat_reference.sendNextFrame();
        //if the lamp is connected and there's an ongoing effect
        if(this.p1light.onGoingEffect.name != null && this.p1light.source != null) this.p1light.sendNextFrame();
        if(this.p2light.onGoingEffect.name != null && this.p2light.source != null) this.p2light.sendNextFrame();
        if(this.p3light.onGoingEffect.name != null && this.p3light.source != null) this.p3light.sendNextFrame();
//        if(this.top_light.onGoingEffect.name != null && this.top_light.source != null) this.top_light.sendNextFrame();

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        //this.runOnUiThread(Timer_Tick);
    }

    private void TimerMethod1()
    {
        //night timing
        if(this.top_light.timed_task_on) {
            this.nightTimer += 1;
            if(this.nightTimer%10 ==0) MyApplicationActivity.getInstance().runOnUiThread(Timer_Tick);
        } else this.nightTimer = 0;

        //indian timing
        if(this.ambi1light.timed_task_on) {
            this.indianTimer += 1;
            if(this.indianTimer%10 ==0) MyApplicationActivity.getInstance().runOnUiThread(Timer_Tick);
        } else this.indianTimer = 0;
        //needed another thread, otherwise it didn't always turn (off/) back on during sunrise effect
        if(this.top_light.onGoingEffect.name != null && this.top_light.source != null) this.top_light.sendNextFrame();


        //We call the method that will work with the UI
        //through the runOnUiThread method.
    }

    private void TimerMethod2()
    {
        if(this.ambi1light.onGoingEffect.name != null && this.ambi1light.source != null) this.ambi1light.sendNextFrame();
        if(this.ambi2light.onGoingEffect.name != null && this.ambi2light.source != null) this.ambi2light.sendNextFrame();
        if(this.ambi3light.onGoingEffect.name != null && this.ambi3light.source != null) this.ambi3light.sendNextFrame();

        //We call the method that will work with the UI
        //through the runOnUiThread method.
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            //This method runs in the same thread as the UI.
            //Do something to the UI thread here
            if(Lamps.this.top_light.timed_task_on) {
                Lamps.this.nightTimerView.setText("T:" + Lamps.this.nightTimer / 10);
            }
            if(Lamps.this.ambi1light.timed_task_on) {
                Lamps.this.indianTimerView.setText("T:" + Lamps.this.indianTimer / 10);
            }
        }
    };

    public void heart_beat_sync() {
        if(this.p1light.heart_beat_started) {
                this.p1light.nextFrameIndex = this.heart_beat_reference.nextFrameIndex;
                this.p1light.nextFrameStartTime = this.heart_beat_reference.nextFrameStartTime;
                this.p1light.timer_state = this.heart_beat_reference.timer_state;
                this.p1light.heart_beat_started = false;
        }
        if(this.p2light.heart_beat_started) {
                this.p2light.nextFrameIndex = this.heart_beat_reference.nextFrameIndex;
                this.p2light.nextFrameStartTime = this.heart_beat_reference.nextFrameStartTime;
                this.p2light.timer_state = this.heart_beat_reference.timer_state;
                this.p2light.heart_beat_started = false;
        }
        if(this.p3light.heart_beat_started) {
                this.p3light.nextFrameIndex = this.heart_beat_reference.nextFrameIndex;
                this.p3light.nextFrameStartTime = this.heart_beat_reference.nextFrameStartTime;
                this.p3light.timer_state = this.heart_beat_reference.timer_state;
                this.p3light.heart_beat_started = false;
        }
    }

    public void setIndians(LightEffects effect) {

    }

    public void setNight(LightEffects effect) {

    }

    public void setStorm(LightEffects effect) {

    }

    public void pause() {
        this.pause = true;
    }

    public void resume() {
        this.pause = false;
    }

    public void stop() {
        //if (this.myTimer != null) this.myTimer.cancel();
    }

    public void setOnGoingEffect(String effect) {
        switch (effect) {
            case "night":
                this.p1light.setOnGoingEffect("heart_night");
                this.top_light.setOnGoingEffect("top_night");
                break;
        }
    }

//    private void processRawFrames(ArrayList<ControlFrame> rawFrames) {
//
//        int numberOfLights = getNumberOfLights(rawFrames);
//        allFrames.clear();
//        newFrameStartTime.clear();
//        timerState.clear();
//        nextFrameIndex.clear();
//
//        //intitialise allFrames and timer arrays
//        for (int i = 0; i < numberOfLights; i++) {
//            allFrames.add(new ArrayList<controlFrame>());
//            newFrameStartTime.add(0);
//            timerState.add(0);
//            nextFrameIndex.add(0);
//        }
//
//        //separate frames by lights
//        for (controlFrame frame : rawFrames) {
//            int lightIndex = frame.getLightIndex();
//            allFrames.get(lightIndex - 1).add(frame);
//        }
//    }
}
