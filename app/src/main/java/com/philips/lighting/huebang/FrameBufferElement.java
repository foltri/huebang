package com.philips.lighting.huebang;

import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

/**
 * Created by folti on 18/06/15.
 */
public class FrameBufferElement {
    public PHLight light;
    public PHLightState lightState;

    public FrameBufferElement(PHLight light, PHLightState lightState) {
        this.light = light;
        this.lightState = lightState;
    }
}
