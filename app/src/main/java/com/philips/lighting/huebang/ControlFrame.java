package com.philips.lighting.huebang;

/**
 * Created by folti on 06/06/15.
 */
public class ControlFrame {
    private final int lightIndex;
    private final int hue;
    private final int bri;
    private final int sat;
    private final int transitionTime;
    private final int upTime;

    public ControlFrame(int lightIndex, int hue, int bri, int sat, int transitionTime, int upTime)
    {
        this.lightIndex = lightIndex;
        this.hue = hue;
        this.bri = bri;
        this.sat = sat;
        this.transitionTime = transitionTime;
        this.upTime = upTime;
    }

    public ControlFrame()
    {
        this.lightIndex = 0;
        this.hue = 0;
        this.bri = 0;
        this.sat = 0;
        this.transitionTime = 0;
        this.upTime = 0;
    }

    public int getLightIndex()
    {
        return lightIndex;
    }

    public int getHue() {
        return hue;
    }

    public int getBri() {
        return bri;
    }

    public int getSat() {
        return sat;
    }

    public int getTransitionTime() {
        return transitionTime;
    }

    public int getUpTime() {
        return upTime;
    }
}
