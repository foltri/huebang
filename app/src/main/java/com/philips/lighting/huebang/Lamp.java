package com.philips.lighting.huebang;

import android.util.Log;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    //public boolean heart_beat = false;
    public boolean heart_beat_started = false;
    public boolean night_task_on = false;
    public boolean indian_task_on = false;
    public Effect onGoingEffect = new Effect();
    public final LightEffects effects = new LightEffects().init();
    public Player player = new Player();
    private PHHueSDK phHueSDK = PHHueSDK.create();
    public PHBridge bridge = phHueSDK.getSelectedBridge();




    public Lamp() {
        this.timer_state = 0;
        this.index = null;
        this.source = null;
        this.nextFrameIndex = 0;
        this.nextFrameStartTime = 0;
        this.onGoingEffect = new Effect();
    }

    public synchronized void setOnGoingEffect(Effect effect) {
        ArrayList<ControlFrame> newEffect = new ArrayList<ControlFrame>();
        newEffect.addAll(effect.frames);
        this.onGoingEffect.frames = newEffect;
        this.onGoingEffect.name = effect.name;
        this.onGoingEffect.looping = effect.looping;

        if(this.onGoingEffect.name.equals("sun_sunrise")) {
            //PHHueSDK phHueSDK = PHHueSDK.create();
            //PHBridge bridge = phHueSDK.getSelectedBridge();
            PHLightState lightState = new PHLightState();
            lightState.setOn(true);
            lightState.setHue(13393);
            lightState.setBrightness(0);

            //todo all lights at the same time vs in order?
            ////bridge.updateLightState(this.source, lightState);
            bridge.setLightStateForDefaultGroup(lightState);
            ////bridge.setLightStateForGroup("heart_group",lightState);
            this.night_task_on = true;
        }

        if (this.onGoingEffect.name.equals("heart_normal")) {
            int newBri = 0;
            ControlFrame adjusted = new ControlFrame();
            switch (this.player.getLives()) {
                case 8:
                    newBri = 20;
                    adjusted = new ControlFrame(0,this.onGoingEffect.frames.get(0).getHue(), newBri,this.onGoingEffect.frames.get(0).getSat(),this.onGoingEffect.frames.get(0).getTransitionTime(),this.onGoingEffect.frames.get(0).getUpTime());
                    this.onGoingEffect.frames.set(0,adjusted);
                    break;
                case 7:
                    newBri = 16;
                    adjusted = new ControlFrame(0,this.onGoingEffect.frames.get(0).getHue(), newBri,this.onGoingEffect.frames.get(0).getSat(),this.onGoingEffect.frames.get(0).getTransitionTime(),this.onGoingEffect.frames.get(0).getUpTime());
                    this.onGoingEffect.frames.set(0,adjusted);
                    break;
                case 6:
                    newBri = 13;
                    adjusted = new ControlFrame(0,this.onGoingEffect.frames.get(0).getHue(), newBri,this.onGoingEffect.frames.get(0).getSat(),this.onGoingEffect.frames.get(0).getTransitionTime(),this.onGoingEffect.frames.get(0).getUpTime());
                    this.onGoingEffect.frames.set(0,adjusted);
                    break;
                case 5:
                    newBri = 11;
                    adjusted = new ControlFrame(0,this.onGoingEffect.frames.get(0).getHue(), newBri,this.onGoingEffect.frames.get(0).getSat(),this.onGoingEffect.frames.get(0).getTransitionTime(),this.onGoingEffect.frames.get(0).getUpTime());
                    this.onGoingEffect.frames.set(0,adjusted);
                    break;
                case 4:
                    newBri = 9;
                    adjusted = new ControlFrame(0,this.onGoingEffect.frames.get(0).getHue(), newBri,this.onGoingEffect.frames.get(0).getSat(),this.onGoingEffect.frames.get(0).getTransitionTime(),this.onGoingEffect.frames.get(0).getUpTime());
                    this.onGoingEffect.frames.set(0,adjusted);
                    break;
                case 3:
                    newBri = 8;
                    adjusted = new ControlFrame(0,this.onGoingEffect.frames.get(0).getHue(), newBri,this.onGoingEffect.frames.get(0).getSat(),this.onGoingEffect.frames.get(0).getTransitionTime(),this.onGoingEffect.frames.get(0).getUpTime());
                    this.onGoingEffect.frames.set(0,adjusted);
                    break;
                case 2:
                    this.setOnGoingEffect(effects.heart_beat);
                    this.heart_beat_started = true;
                    break;
                case 1:
                    this.setOnGoingEffect(effects.heart_beat);
                    this.heart_beat_started = true;
                    break;
                default:
                    break;
            }
        }

        if (this.onGoingEffect.name.equals("ambi_indian1") || this.onGoingEffect.name.equals("ambi_indian2") || this.onGoingEffect.name.equals("ambi_indian3") || this.onGoingEffect.name.equals("ambi_indian4")) {
            int delayMax = 0;
            int delayMin = 0;
            Random rand = new Random();
            int lampIndex = rand.nextInt(4) + 1;
            int colorIndex = 0;//rand.nextInt(2) + 0;
            int delay = 0;
            ControlFrame colored = new ControlFrame();
            ControlFrame normal = new ControlFrame();
            ArrayList<ControlFrame> frameList1 = new ArrayList<ControlFrame>();
            ArrayList<ControlFrame> frameList2 = new ArrayList<ControlFrame>();

            switch (effect.name) {
                case "ambi_indian1":
                    delayMax = 800;
                    delayMin = 200;

                    delay = rand.nextInt(delayMax / 100) + delayMin / 100;
                    delay *= 100;
                    colored = new ControlFrame(lampIndex, this.effects.ambi_indian1.frames.get(colorIndex).getHue(), this.effects.ambi_indian1.frames.get(colorIndex).getBri(), this.effects.ambi_indian1.frames.get(colorIndex).getSat(), this.effects.ambi_indian1.frames.get(colorIndex).getTransitionTime(), this.effects.ambi_indian1.frames.get(colorIndex).getUpTime());
                    normal = new ControlFrame(lampIndex, this.effects.ambi1_normal.frames.get(0).getHue(), this.effects.ambi1_normal.frames.get(0).getBri(), this.effects.ambi1_normal.frames.get(0).getSat(), this.effects.ambi_indian1.frames.get(colorIndex).getTransitionTime(), delay);

                    this.onGoingEffect.frames.clear();
                    this.onGoingEffect.frames.add(colored);
                    this.onGoingEffect.frames.add(normal);
                    break;

                case "ambi_indian2":
                    delayMax = 200;
                    delayMin = 100;

                    delay = rand.nextInt(delayMax / 100) + delayMin / 100;
                    delay *= 100;

                    for (ControlFrame frame:this.effects.ambi11_indian2.frames) {
                        frameList1.add(new ControlFrame(lampIndex, frame.getHue(), frame.getBri(), frame.getSat(), frame.getTransitionTime(), frame.getUpTime()));
                        //todo delay
                    }
                    for (ControlFrame frame:this.effects.ambi12_indian2.frames) {
                        frameList2.add(new ControlFrame(lampIndex, frame.getHue(), frame.getBri(), frame.getSat(), frame.getTransitionTime(), frame.getUpTime()));
                        //todo delay
                    }

                    this.onGoingEffect.frames.clear();
                    this.onGoingEffect.frames.add(colored);
                    this.onGoingEffect.frames.add(normal);
                    break;

                case "ambi_indian3":
                    delayMax = 3200;
                    delayMin = 1200;

                    delay = rand.nextInt(delayMax / 100) + delayMin / 100;
                    delay *= 100;
                    colored = new ControlFrame(lampIndex, this.effects.ambi_indian3.frames.get(colorIndex).getHue(), this.effects.ambi_indian3.frames.get(colorIndex).getBri(), this.effects.ambi_indian3.frames.get(colorIndex).getSat(), this.effects.ambi_indian3.frames.get(colorIndex).getTransitionTime(), this.effects.ambi_indian3.frames.get(colorIndex).getUpTime());
                    normal = new ControlFrame(lampIndex, this.effects.ambi1_normal.frames.get(0).getHue(), this.effects.ambi1_normal.frames.get(0).getBri(), this.effects.ambi1_normal.frames.get(0).getSat(), this.effects.ambi_indian1.frames.get(colorIndex).getTransitionTime(), delay);

                    this.onGoingEffect.frames.clear();
                    this.onGoingEffect.frames.add(colored);
                    this.onGoingEffect.frames.add(normal);
                    break;

                case "ambi_indian4":
                    delayMax = 200;
                    delayMin = 100;

                    delay = rand.nextInt(delayMax / 100) + delayMin / 100;
                    delay = 100;
                    colored = new ControlFrame(lampIndex, this.effects.ambi_indian4.frames.get(colorIndex).getHue(), this.effects.ambi_indian4.frames.get(colorIndex).getBri(), this.effects.ambi_indian4.frames.get(colorIndex).getSat(), this.effects.ambi_indian4.frames.get(colorIndex).getTransitionTime(), this.effects.ambi_indian4.frames.get(colorIndex).getUpTime());
                    normal = new ControlFrame(lampIndex, this.effects.ambi1_normal.frames.get(0).getHue(), this.effects.ambi1_normal.frames.get(0).getBri(), this.effects.ambi1_normal.frames.get(0).getSat(), this.effects.ambi_indian1.frames.get(colorIndex).getTransitionTime(), delay);

                    this.onGoingEffect.frames.clear();
                    this.onGoingEffect.frames.add(colored);
                    this.onGoingEffect.frames.add(normal);
                    break;
                default:
                    break;
            }


        } else {
            this.timer_state = 0;
            this.nextFrameIndex = 0;
            this.nextFrameStartTime = 0;
        }
    }

    public void endOfEffect() {

        //id end of shot/arrow/dynamite set back normal heart state
        if(this.onGoingEffect.name.equals("shot") || this.onGoingEffect.name.equals("heart_arrow")) {
            this.onGoingEffect.name = null;
            this.onGoingEffect.frames.clear();

            this.setOnGoingEffect(this.effects.heart_normal);

        }
        //if end of dynamite set back normal ambience state
        else if (this.onGoingEffect.name.equals("dynamite")) {
            this.onGoingEffect.name = null;
            this.onGoingEffect.frames.clear();
            switch (this.index) {
                case "Top lamp":
                    this.setOnGoingEffect(this.effects.top_normal);
                    //Log.w("top_normal", String.valueOf(this.onGoingEffect.frames.get(0).getHue()));
                    break;
                case "Ambi 11":
                    this.setOnGoingEffect(this.effects.ambi1_normal);
                    //Log.w("ambi1_normal", String.valueOf(this.onGoingEffect.frames.get(0).getHue()));
                    break;
                case "Ambi 12":
                    this.setOnGoingEffect(this.effects.ambi1_normal);
                    break;
                case "Ambi 21":
                    this.setOnGoingEffect(this.effects.ambi1_normal);
                    break;
                case "P1 lamp":
                    this.setOnGoingEffect(this.effects.heart_normal);
                    break;
                case "P2 lamp":
                    this.setOnGoingEffect(this.effects.heart_normal);
                    break;
                case "P3 lamp":
                    this.setOnGoingEffect(this.effects.heart_normal);
                    break;
                case "Ambi 22":
                    this.setOnGoingEffect(this.effects.ambi1_normal);
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

    public synchronized FrameBufferElement sendNextFrame() {
        PHHueSDK phHueSDK = PHHueSDK.create();
        PHBridge bridge = phHueSDK.getSelectedBridge();
        ControlFrame nextFrame = getNextFrame();
        FrameBufferElement newElement = null;
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

            if (this.onGoingEffect.name == "ambi_indian1" || this.onGoingEffect.name == "ambi_indian2" || this.onGoingEffect.name == "ambi_indian3" || this.onGoingEffect.name == "ambi_indian4") {
                List<PHLight> allLights = bridge.getResourceCache().getAllLights();
                PHLight lsource = null;

                switch (nextFrame.getLightIndex()) {
                    case 1:
                        //bridge.updateLightState(this.source, lightState);
                        newElement = new FrameBufferElement(this.source, lightState);
                        break;
                    case 2:
                        for (PHLight light:allLights) {
                            if (light.getName().equals("Ambi 12")) {
                                //bridge.updateLightState(light, lightState);
                                newElement = new FrameBufferElement(light, lightState);
                            }
                        }
                        break;
                    case 3:
                        for (PHLight light:allLights) {
                            if (light.getName().equals("Ambi 21")) {
                                //bridge.updateLightState(light, lightState);
                                newElement = new FrameBufferElement(light, lightState);
                            }
                        }
                        break;
                    case 4:
                        for (PHLight light:allLights) {
                            if (light.getName().equals("Ambi 22")) {
                                //bridge.updateLightState(light, lightState);
                                newElement = new FrameBufferElement(light, lightState);
                            }
                        }
                        break;
                    default:
                        break;
                }
            } else {
                //bridge.updateLightState(this.source, lightState);
                newElement = new FrameBufferElement(this.source, lightState);
            }



            //bridge.updateLightState(this.source, lightState);

            //debug
            //Log.w("Sent frames", this.index + ": " + String.valueOf(nextFrame.getBri()) + " " + String.valueOf(this.onGoingEffect.name));
        }
        return newElement;
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
                //if (this.onGoingEffect.name.equals("top_sunset") || this.onGoingEffect.name.equals("heart_sunset") || this.onGoingEffect.name.equals("ambix1_sunset") || this.onGoingEffect.name.equals("ambix2_sunset")) {
                if (this.onGoingEffect.name.equals("ambix1_sunset")) {
                    PHHueSDK phHueSDK = PHHueSDK.create();
                    PHBridge bridge = phHueSDK.getSelectedBridge();
                    PHLightState lightState = new PHLightState();
                    lightState.setOn(false);

                    //todo all lights at the same time vs in order?
                    //bridge.updateLightState(this.source, lightState);
                    bridge.setLightStateForDefaultGroup(lightState);
                    this.night_task_on = true;
                }

                //turn off top lamp at night
                if (this.onGoingEffect.name.equals("top_night")) {
                    PHHueSDK phHueSDK = PHHueSDK.create();
                    PHBridge bridge = phHueSDK.getSelectedBridge();
                    PHLightState lightState = new PHLightState();
                    lightState.setOn(false);

                    bridge.updateLightState(this.source, lightState);

                }

                //loop if last frame of a looping effect
                if (this.onGoingEffect.looping) {
                    this.nextFrameIndex = 0;
                    this.timer_state += 1;
                    //restart ambi_indian effect
                    if (this.onGoingEffect.name.equals("ambi_indian1") || this.onGoingEffect.name.equals("ambi_indian2") || this.onGoingEffect.name.equals("ambi_indian3") || this.onGoingEffect.name.equals("ambi_indian4")) {
                        this.setOnGoingEffect(this.onGoingEffect);
                    }
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

