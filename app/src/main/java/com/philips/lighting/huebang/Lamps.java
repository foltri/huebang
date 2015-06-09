package com.philips.lighting.huebang;

import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by folti on 07/06/15.
 */
public class Lamps {
    public Lamp p1light;
    public Lamp p2light;
    public Lamp p3light;
    public Lamp top_light;
    private Timer myTimer;
    private boolean pause = false;
    private boolean heart_beat_reference_on = false;
    private Lamp heart_beat_reference;


    public Lamps(List<PHLight> lights) {
        this.p1light = new Lamp();
        this.p2light = new Lamp();
        this.p3light = new Lamp();
        this.top_light = new Lamp();
        this.heart_beat_reference = new Lamp();

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
                    this.p1light.index = "Top lamp";
                    this.p1light.nextFrameIndex = 0;
                    this.p1light.nextFrameStartTime = 0;
                    this.p1light.onGoingEffect = new Effect();
                    break;
                default:
                    break;
            }
        }
        //Todo init timer and frame arrays, start timer
        this.myTimer = new Timer();
        this.myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 200);
    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.
        this.heart_beat_sync();
        if(this.p1light.onGoingEffect.name != null) this.p1light.sendNextFrame();
        if(this.p2light.onGoingEffect.name != null) this.p2light.sendNextFrame();
        if(this.p3light.onGoingEffect.name != null) this.p3light.sendNextFrame();
        if(this.top_light.onGoingEffect.name != null) this.top_light.sendNextFrame();

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        //this.runOnUiThread(Timer_Tick);
    }

    public void heart_beat_sync() {
        if(this.p1light.heart_beat_started) {
            if(this.heart_beat_reference_on) {
                this.p1light.nextFrameIndex = this.heart_beat_reference.nextFrameIndex;
                this.p1light.nextFrameStartTime = this.heart_beat_reference.nextFrameStartTime;
                this.p1light.timer_state = this.heart_beat_reference.timer_state;
                this.p1light.heart_beat_started = false;
                this.heart_beat_reference = this.p1light;
            } else {
                this.heart_beat_reference_on = true;
                this.heart_beat_reference = this.p1light;
                this.p1light.heart_beat_started = false;
            }
        }
        if(this.p2light.heart_beat_started) {
            if(this.heart_beat_reference_on) {
                this.p2light.nextFrameIndex = this.heart_beat_reference.nextFrameIndex;
                this.p2light.nextFrameStartTime = this.heart_beat_reference.nextFrameStartTime;
                this.p2light.timer_state = this.heart_beat_reference.timer_state;
                this.p2light.heart_beat_started = false;
                this.heart_beat_reference = this.p2light;
            } else {
                this.heart_beat_reference_on = true;
                this.heart_beat_reference = this.p2light;
                this.p2light.heart_beat_started = false;
            }
        }
        if(this.p3light.heart_beat_started) {
            if(this.heart_beat_reference_on) {
                this.p3light.nextFrameIndex = this.heart_beat_reference.nextFrameIndex;
                this.p3light.nextFrameStartTime = this.heart_beat_reference.nextFrameStartTime;
                this.p3light.timer_state = this.heart_beat_reference.timer_state;
                this.p3light.heart_beat_started = false;
                this.heart_beat_reference = this.p3light;
            } else {
                this.heart_beat_reference_on = true;
                this.heart_beat_reference = this.p3light;
                this.p3light.heart_beat_started = false;
            }
        }

        if(!this.p1light.heart_beat && !this.p2light.heart_beat && !this.p3light.heart_beat) {
            this.heart_beat_reference_on = false;
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
        if (this.myTimer != null) this.myTimer.cancel();
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
