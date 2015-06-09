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


    public Lamps(List<PHLight> lights) {
        this.p1light = new Lamp();
        this.p2light = new Lamp();
        this.p3light = new Lamp();
        this.top_light = new Lamp();

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
                    p2light.source = light;
                    p2light.timer_state = 0;
                    p2light.index = "P2 lamp";
                    p2light.nextFrameIndex = 0;
                    p2light.nextFrameStartTime = 0;
                    p2light.onGoingEffect = new Effect();
                    break;
                case "P3 lamp":
                    p3light.source = light;
                    p3light.timer_state = 0;
                    p3light.index = "P3 lamp";
                    p3light.nextFrameIndex = 0;
                    p3light.nextFrameStartTime = 0;
                    p3light.onGoingEffect = new Effect();
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
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 100);
    }

    public void setIndians(LightEffects effect) {

    }

    public void setNight(LightEffects effect) {

    }

    public void setStorm(LightEffects effect) {

    }

    public void pause() {
        pause = true;
    }

    public void resume() {
        pause = false;
    }

    public void stop() {
        if (myTimer != null) myTimer.cancel();
    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.
        if(p1light.onGoingEffect.name != null) p1light.sendNextFrame();
        if(p2light.onGoingEffect.name != null) p2light.sendNextFrame();
        if(p3light.onGoingEffect.name != null) p3light.sendNextFrame();
        if(top_light.onGoingEffect.name != null) top_light.sendNextFrame();

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        //this.runOnUiThread(Timer_Tick);
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
