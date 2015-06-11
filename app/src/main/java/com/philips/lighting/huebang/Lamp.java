package com.philips.lighting.huebang;

import android.util.Log;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.ArrayList;

import static com.philips.lighting.huebang.LightEffects.*;

/**
 * Created by folti on 07/06/15.
 */
public class Lamp {
    public int timer_state;
    public String index;
    public PHLight source;
    public int nextFrameIndex;
    public int nextFrameStartTime;
    public boolean heart_beat = false;
    public boolean heart_beat_started = false;
    public boolean timed_task_on = false;
    public Effect onGoingEffect = new Effect();
    private final LightEffects effects = new LightEffects().init();

    public Lamp() {
        this.timer_state = 0;
        this.index = null;
        this.source = null;
        this.nextFrameIndex = 0;
        this.nextFrameStartTime = 0;
        this.heart_beat = false;
        this.onGoingEffect = new Effect();
    }

    public synchronized void setOnGoingEffect(String effect) {
        ArrayList<ControlFrame> newEffect = new ArrayList<ControlFrame>();

        switch (effect) {
            case "heart_normal":
                newEffect.addAll(effects.heart_normal.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart_normal.looping;
                break;
            case "heart_beat":
                newEffect.addAll(effects.heart_beat.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart_beat.looping;
                break;
            case "heart_indian2":
                newEffect.addAll(effects.heart_indian2.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart_indian2.looping;
                break;
            case "heart_indian3":
                newEffect.addAll(effects.heart_indian3.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart_indian3.looping;
                break;
            case "heart1_indian4":
                newEffect.addAll(effects.heart1_indian4.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart1_indian4.looping;
                break;
            case "heart2_indian4":
                newEffect.addAll(effects.heart2_indian4.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart2_indian4.looping;
                break;
            case "heart3_indian4":
                newEffect.addAll(effects.heart3_indian4.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart3_indian4.looping;
                break;
            case "heart_night":
                newEffect.addAll(effects.heart_night.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart_night.looping;
                break;
            case "heart_sunset":
                newEffect.addAll(effects.heart_sunset.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart_sunset.looping;
                break;
            case "heart_arrow":
                newEffect.addAll(effects.heart_arrow.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.heart_arrow.looping;
                break;
            case "shot":
                newEffect.addAll(effects.shot.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.shot.looping;
                break;
            case "top_storm":
                newEffect.addAll(effects.top_storm.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.top_storm.looping;
                break;
            case "top_normal":
                newEffect.addAll(effects.top_normal.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.top_normal.looping;
                break;
            case "top_indian2":
                newEffect.addAll(effects.top_indian2.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.top_indian2.looping;
                break;
            case "top_indian3":
                newEffect.addAll(effects.top_indian3.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.top_indian3.looping;
                break;
            case "top_indian4":
                newEffect.addAll(effects.top_indian4.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.top_indian4.looping;
                break;
            case "top_night":
                newEffect.addAll(effects.top_night.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.top_night.looping;
                break;
            case "top_sunset":
                newEffect.addAll(effects.top_sunset.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.top_sunset.looping;
                break;
            case "top_sunrise":
                newEffect.addAll(effects.top_sunrise.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.timed_task_on = false;
                this.onGoingEffect.looping = this.effects.top_sunrise.looping;
                break;
            case "ambi1_indian1":
                newEffect.addAll(effects.ambi1_indian1.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.timed_task_on = true;
                this.onGoingEffect.looping = this.effects.ambi1_indian1.looping;
                break;
            case "ambi1_indian2":
                newEffect.addAll(effects.ambi1_indian2.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi1_indian2.looping;
                break;
            case "ambi1_indian3":
                newEffect.addAll(effects.ambi1_indian3.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi1_indian3.looping;
                break;
            case "ambi1_indian4":
                newEffect.addAll(effects.ambi1_indian4.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi1_indian4.looping;
                break;
            case "ambi1_normal":
                newEffect.addAll(effects.ambi1_normal.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi1_normal.looping;
                break;
            case "ambi2_indian1":
                newEffect.addAll(effects.ambi2_indian1.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi2_indian1.looping;
                break;
            case "ambi2_indian2":
                newEffect.addAll(effects.ambi2_indian2.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi2_indian2.looping;
                break;
            case "ambi2_indian3":
                newEffect.addAll(effects.ambi2_indian3.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi2_indian1.looping;
                break;
            case "ambi2_indian4":
                newEffect.addAll(effects.ambi2_indian4.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi2_indian4.looping;
                break;
            case "ambi2_normal":
                newEffect.addAll(effects.ambi2_normal.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi2_normal.looping;
                break;
            case "ambi3_indian1":
                newEffect.addAll(effects.ambi3_indian1.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi3_indian1.looping;
                break;
            case "ambi3_indian2":
                newEffect.addAll(effects.ambi3_indian2.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi3_indian2.looping;
                break;
            case "ambi3_indian3":
                newEffect.addAll(effects.ambi3_indian3.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi3_indian3.looping;
                break;
            case "ambi3_indian4":
                newEffect.addAll(effects.ambi3_indian4.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi3_indian4.looping;
                break;
            case "ambi3_normal":
                newEffect.addAll(effects.ambi3_normal.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.ambi3_normal.looping;
                break;
            case "dynamite":
                newEffect.addAll(effects.dynamite.frames);
                this.onGoingEffect.frames = newEffect;
                this.onGoingEffect.name = effect;
                this.onGoingEffect.looping = this.effects.dynamite.looping;
                break;
            default:
                break;
        }

        this.timer_state = 0;
        this.nextFrameIndex = 0;
        this.nextFrameStartTime = 0;
        if (effect.equals("heart_beat")) {
            this.heart_beat = true;
            this.heart_beat_started = true;
        }
        else if (effect.equals("heart_normal")) this.heart_beat = false;
    }

    public void setOnGoingEffect(String effect, boolean heartBeat) {
        ArrayList<ControlFrame> newEffect = new ArrayList<ControlFrame>();
            switch (effect) {
                case "shot":
                    newEffect.addAll(effects.shot.frames);
                    this.onGoingEffect.frames = newEffect;
                    this.onGoingEffect.name = effect;
                    break;
                case "top_storm":
                    newEffect.addAll(effects.top_storm.frames);
                    this.onGoingEffect.frames = newEffect;
                    this.onGoingEffect.name = effect;
                    break;
                case "top_normal":
                    newEffect.addAll(effects.top_normal.frames);
                    this.onGoingEffect.frames = newEffect;
                    this.onGoingEffect.name = effect;
                    break;
                default:
                    break;
            }

            this.timer_state = 0;
            this.nextFrameIndex = 0;
            this.nextFrameStartTime = 0;
            if (heartBeat) {
                this.heart_beat = true;
                //this.heart_beat_started = true;
            } else this.heart_beat = false;
    }

    public void endOfEffect() {
        if((this.onGoingEffect.name == "shot" || this.onGoingEffect.name == "heart_arrow" || this.onGoingEffect.name == "dynamite") && this.heart_beat) {
            this.onGoingEffect.name = null;
            this.onGoingEffect.frames.clear();
            this.setOnGoingEffect("heart_beat");
        }
        //if end of dynamite set back normal state
        else if (this.onGoingEffect.name.equals("dynamite")) {
            switch (this.index) {
                case "Top lamp":
                    this.setOnGoingEffect("top_normal");
                    break;
                case "Ambient 1":
                    this.setOnGoingEffect("ambi1_normal");
                    break;
                case "Ambient 2":
                    this.setOnGoingEffect("ambi2_normal");
                    break;
                case "Ambient 3":
                    this.setOnGoingEffect("ambi3_normal");
                    break;
                default:
                    break;
            }
        }
        else {
            this.onGoingEffect.name = null;
            this.onGoingEffect.frames.clear();
        }
    }

    public synchronized void sendNextFrame() {
        PHHueSDK phHueSDK = PHHueSDK.create();
        PHBridge bridge = phHueSDK.getSelectedBridge();
        ControlFrame nextFrame = getNextFrame();
        //if there's frame to send
        if (nextFrame != null && !this.index.equals("heart_beat_reference")) {
            PHLightState lightState = new PHLightState();

            if (nextFrame.getHue() != 0) {
                lightState.setHue(nextFrame.getHue());
            }
            if (nextFrame.getBri() != 0) {
                lightState.setBrightness(nextFrame.getBri());
            }
            if (nextFrame.getSat() != 0) {
                lightState.setSaturation(nextFrame.getSat());
            }
            lightState.setTransitionTime(nextFrame.getTransitionTime() / 100);
            // To validate your lightstate is valid (before sending to the bridge) you can use:
            // String validState = lightState.validateState();
            //  bridge.updateLightState(light, lightState);   // If no bridge response is required then use this simpler form.

            //turn on if the lamp is off
            if(!this.source.getLastKnownLightState().isOn()) {
                lightState.setOn(true);
            }
            bridge.updateLightState(this.source, lightState);

            //debug
            //Log.w("Sent frames", "Sent: " + nextFrame.getBri());
        }
    }

    //returns the frames that are scheduled for the current tick
    private ControlFrame getNextFrame() {
        ControlFrame frameToSend = null;
        if (this.timer_state == this.nextFrameStartTime && this.onGoingEffect.frames.size() > this.nextFrameIndex) {
            frameToSend = this.onGoingEffect.frames.get(this.nextFrameIndex); // error: onGoingEffect = "shot" but nextFrameIndex = 3 (so it's still heart_beat)

            //reset timer
            this.timer_state = 0;

            //set next frame's start time
            int nextStart = frameToSend.getTransitionTime() / 100 + frameToSend.getUpTime() / 100;
            this.nextFrameStartTime = nextStart;


            //Stop if last frame
            if (this.nextFrameIndex == this.onGoingEffect.frames.size() - 1) {
                //turn off lamp after last frame of sunset
                if (this.onGoingEffect.name.equals("top_sunset") || this.onGoingEffect.name.equals("heart_sunset")) {
                    PHHueSDK phHueSDK = PHHueSDK.create();
                    PHBridge bridge = phHueSDK.getSelectedBridge();
                    PHLightState lightState = new PHLightState();
                    lightState.setOn(false);
                    bridge.updateLightState(this.source, lightState);
                    this.timed_task_on = true;
                }

                //loop if last frame of a looping effect
                if (this.onGoingEffect.looping) {
                    this.nextFrameIndex = 0;
                    this.timer_state += 1;
                } else {
                    this.endOfEffect();
                    this.timer_state = 0;
                }
            } else {
                this.nextFrameIndex += 1;
                this.timer_state += 1;
            }
        } else {
            //Increment timerState
            this.timer_state += 1;
        }

        return frameToSend;
    }
}

