package com.philips.lighting.huebang;

import android.util.Log;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.ArrayList;

/**
 * Created by folti on 18/06/15.
 */
public class FrameBuffer {
    public ArrayList<FrameBufferElement> frames = new ArrayList<FrameBufferElement>();
    private PHHueSDK phHueSDK = PHHueSDK.create();
    public PHBridge bridge = phHueSDK.getSelectedBridge();

    public void addFrame(FrameBufferElement newElement) {
        if(newElement != null) frames.add(newElement);
    }

    public synchronized void sendNextFrame() {
        PHLightState lightState = this.frames.get(0).lightState;
        PHLight light = this.frames.remove(0).light;

        bridge.updateLightState(light, lightState);
        Log.w("Sent: ", String.valueOf(light.getName()));
    }

    public int size() {
        return this.frames.size();
    }

    public void clear() {
        this.frames.clear();
    }
}
