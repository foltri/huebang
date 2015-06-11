package com.philips.lighting.huebang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.philips.lighting.hue.listener.PHLightListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResource;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

public class MyApplicationActivity extends Activity {
    private PHHueSDK phHueSDK;
    private static final int MAX_HUE=65535;
    public static final String TAG = "QuickStart";
    public RadioButton p1radioButton = null;
    public RadioButton p2radioButton = null;
    public RadioButton p3radioButton = null;
    private static MyApplicationActivity instance = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.instance = this;
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        phHueSDK = PHHueSDK.create();
        TextView nightTimer = (TextView) findViewById(R.id.nightTimerView);
        TextView indianTimer = (TextView) findViewById(R.id.indianTimerView);

        //final LightEffects lightEffects = new LightEffects().init();

        final Lamps lamps = new Lamps(phHueSDK.getSelectedBridge().getResourceCache().getAllLights(), nightTimer, indianTimer);
        lamps.p1light.setOnGoingEffect("heart_normal");
        lamps.p2light.setOnGoingEffect("heart_normal");
        lamps.p3light.setOnGoingEffect("heart_normal");
        lamps.top_light.setOnGoingEffect("top_normal");

        final Player p1 = new Player(4,0, (TextView) findViewById(R.id.p1arrowView), (TextView) findViewById(R.id.p1lifeView));
        final Player p2 = new Player(4,0, (TextView) findViewById(R.id.p2arrowView), (TextView) findViewById(R.id.p2lifeView));
        final Player p3 = new Player(4,0, (TextView) findViewById(R.id.p3arrowView), (TextView) findViewById(R.id.p3lifeView));


        //EXTENTIONAL ELEMENTS//
        final ToggleButton stormButton = (ToggleButton) findViewById(R.id.stormButton);
        stormButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the toggle on?
                boolean on = ((ToggleButton) view).isChecked();

                if (on) {
                    // Turn on storm effect
                    lamps.top_light.setOnGoingEffect("top_storm");
                } else {
                    // Turn back normal lighting
                    lamps.top_light.setOnGoingEffect("top_normal");
                }
            }
        });

        final ToggleButton nightButton = (ToggleButton) findViewById(R.id.nightButton);
        nightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the toggle on?
                boolean on = ((ToggleButton) view).isChecked();

                if (on) {
                    // Turn on storm effect
                    lamps.top_light.setOnGoingEffect("top_night");
                    lamps.p1light.setOnGoingEffect("heart_night");
                    lamps.p2light.setOnGoingEffect("heart_night");
                    lamps.p3light.setOnGoingEffect("heart_night");
                } else {
                    // Turn back normal lighting
                    lamps.top_light.setOnGoingEffect("top_normal");
                }
            }
        });

        final Button sunsetButton = (Button) findViewById(R.id.nightSunsetButton);
        sunsetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                lamps.top_light.setOnGoingEffect("top_sunset");
                lamps.p1light.setOnGoingEffect("heart_sunset");
                lamps.p2light.setOnGoingEffect("heart_sunset");
                lamps.p3light.setOnGoingEffect("heart_sunset");
            }
        });

        final Button sunriseButton = (Button) findViewById(R.id.nightSunriseButton);
        sunriseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                lamps.top_light.setOnGoingEffect("top_sunrise");

                if(p1.isHeartBeat()) {
                    lamps.p1light.setOnGoingEffect("heart_beat");
                } else lamps.p1light.setOnGoingEffect("heart_normal");

                if(p2.isHeartBeat()) {
                    lamps.p2light.setOnGoingEffect("heart_beat");
                } else lamps.p2light.setOnGoingEffect("heart_normal");

                if(p3.isHeartBeat()) {
                    lamps.p3light.setOnGoingEffect("heart_beat");
                } else lamps.p3light.setOnGoingEffect("heart_normal");

                nightButton.setChecked(false);
            }
        });

        final RadioButton indian1check = (RadioButton) findViewById(R.id.indian1check);
        indian1check.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                lamps.ambi1light.setOnGoingEffect("ambi1_indian1");
                lamps.ambi2light.setOnGoingEffect("ambi2_indian1");
                lamps.ambi3light.setOnGoingEffect("ambi3_indian1");
            }
        });

        final RadioButton indian2check = (RadioButton) findViewById(R.id.indian2check);
        indian2check.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                lamps.ambi1light.setOnGoingEffect("ambi1_indian2");
                lamps.ambi2light.setOnGoingEffect("ambi2_indian2");
                lamps.ambi3light.setOnGoingEffect("ambi3_indian2");
            }
        });

        final RadioButton indian3check = (RadioButton) findViewById(R.id.indian3check);
        indian3check.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                lamps.ambi1light.setOnGoingEffect("ambi1_indian3");
                lamps.ambi2light.setOnGoingEffect("ambi2_indian3");
                lamps.ambi3light.setOnGoingEffect("ambi3_indian3");
            }
        });

        final RadioButton indian4check = (RadioButton) findViewById(R.id.indian4check);
        indian4check.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                lamps.ambi1light.setOnGoingEffect("ambi1_indian4");
                lamps.ambi2light.setOnGoingEffect("ambi2_indian4");
                lamps.ambi3light.setOnGoingEffect("ambi3_indian4");
            }
        });

        final ToggleButton indianButton = (ToggleButton) findViewById(R.id.indianButton);
        indianButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the toggle on?
                boolean on = ((ToggleButton) view).isChecked();

                if (on) {
                    // Turn on storm effect
                    lamps.ambi1light.setOnGoingEffect("ambi1_indian1");
                    lamps.ambi2light.setOnGoingEffect("ambi2_indian1");
                    lamps.ambi3light.setOnGoingEffect("ambi3_indian1");

                    indian1check.setChecked(true);
                } else {
                    // Turn back normal lighting
                    lamps.ambi1light.setOnGoingEffect("ambi1_normal");
                    lamps.ambi2light.setOnGoingEffect("ambi2_normal");
                    lamps.ambi3light.setOnGoingEffect("ambi3_normal");

                    indian1check.setChecked(false);
                    indian2check.setChecked(false);
                    indian3check.setChecked(false);
                    indian4check.setChecked(false);
                }
            }
        });

        final Button dynamiteButton = (Button) findViewById(R.id.indianDynamiteButton);
        dynamiteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                indian1check.setChecked(false);
                indian2check.setChecked(false);
                indian3check.setChecked(false);
                indian4check.setChecked(false);
                indianButton.setChecked(false);
            }
        });

        final Button arrowButton = (Button) findViewById(R.id.indianArrowButton);
        arrowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                indian1check.setChecked(false);
                indian2check.setChecked(false);
                indian3check.setChecked(false);
                indian4check.setChecked(false);
                indianButton.setChecked(false);
            }
        });

        //LIFE INC//
        Button p1incLifeButton = (Button) findViewById(R.id.p1incLifeButton);
        p1incLifeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.setLives(p1.getLives() + 1, lamps.p1light);
            }
        });
        Button p2incLifeButton = (Button) findViewById(R.id.p2incLifeButton);
        p2incLifeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p2.setLives(p2.getLives() + 1, lamps.p2light);
            }
        });
        Button p3incLifeButton = (Button) findViewById(R.id.p3incLifeButton);
        p3incLifeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p3.setLives(p3.getLives() + 1, lamps.p3light);
            }
        });

        //LIFE DEC//
        Button p1decLifeButton = (Button) findViewById(R.id.p1decLifeButton);
        p1decLifeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.setLives(p1.getLives() - 1, lamps.p1light);
            }
        });
        Button p2decLifeButton = (Button) findViewById(R.id.p2decLifeButton);
        p2decLifeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p2.setLives(p2.getLives() - 1, lamps.p2light);
            }
        });
        Button p3decLifeButton = (Button) findViewById(R.id.p3decLifeButton);
        p3decLifeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p3.setLives(p3.getLives() - 1, lamps.p3light);
            }
        });

        //ARROW INC//
        Button p1incArrowButton = (Button) findViewById(R.id.p1incArrowButton);
        p1incArrowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.setArrows(p1.getArrows() + 1);
            }
        });
        Button p2incArrowButton = (Button) findViewById(R.id.p2incArrowButton);
        p2incArrowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p2.setArrows(p2.getArrows() + 1);
            }
        });
        Button p3incArrowButton = (Button) findViewById(R.id.p3incArrowButton);
        p3incArrowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p3.setArrows(p3.getArrows() + 1);
            }
        });

        //ARROW DEC//
        Button p1decArrowButton = (Button) findViewById(R.id.p1decArrowButton);
        p1decArrowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.setArrows(p1.getArrows() - 1);
            }
        });
        Button p2decArrowButton = (Button) findViewById(R.id.p2decArrowButton);
        p2decArrowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p2.setArrows(p2.getArrows() - 1);
            }
        });
        Button p3decArrowButton = (Button) findViewById(R.id.p3decArrowButton);
        p3decArrowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p3.setArrows(p3.getArrows() - 1);
            }
        });

        //BEER//
        Button p1beer = (Button) findViewById(R.id.p1beer);
        p1beer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.gotBeer(lamps.p1light);
                //Todo Beer animation
            }
        });
        Button p2beer = (Button) findViewById(R.id.p2beer);
        p2beer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p2.gotBeer(lamps.p2light);
                //Todo Beer animation
            }
        });
        Button p3beer = (Button) findViewById(R.id.p3beer);
        p3beer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p3.gotBeer(lamps.p3light);
                //Todo Beer animation
            }
        });

        //SHOT//
        Button p1shot = (Button) findViewById(R.id.p1shot);
        p1shot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.gotShot(lamps.p1light);
                //Todo Shot animation
            }
        });
        Button p2shot = (Button) findViewById(R.id.p2shot);
        p2shot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p2.gotShot(lamps.p2light);
                //Todo Shot animation
            }
        });
        Button p3shot = (Button) findViewById(R.id.p3shot);
        p3shot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p3.gotShot(lamps.p3light);
                //Todo Shot animation
            }
        });

        //GGUN//
        Button p1ggun = (Button) findViewById(R.id.p1ggun);
        p1ggun.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.gotGgun(p2,p3, lamps.p2light, lamps.p3light);
            }
        });
        Button p2ggun = (Button) findViewById(R.id.p2ggun);
        p2ggun.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p2.gotGgun(p1,p3, lamps.p1light, lamps.p3light);
            }
        });
        Button p3ggun = (Button) findViewById(R.id.p3ggun);
        p3ggun.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p3.gotGgun(p1,p2, lamps.p1light, lamps.p2light);
            }
        });

        //RADIO BUTTONS//
        p1radioButton = (RadioButton) findViewById(R.id.p1radioButton);
        p1radioButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p1Radio();
            }
        });
        p2radioButton = (RadioButton) findViewById(R.id.p2radioButton);
        p2radioButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p2Radio();
            }
        });
        p3radioButton = (RadioButton) findViewById(R.id.p3radioButton);
        p3radioButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                p3Radio();
            }
        });
    }

    public void randomLights() {
        PHBridge bridge = phHueSDK.getSelectedBridge();

        List<PHLight> allLights = bridge.getResourceCache().getAllLights();
        Random rand = new Random();
        
        for (PHLight light : allLights) {
            PHLightState lightState = new PHLightState();
            lightState.setHue(rand.nextInt(MAX_HUE));
            // To validate your lightstate is valid (before sending to the bridge) you can use:  
            // String validState = lightState.validateState();
            bridge.updateLightState(light, lightState, listener);
            //  bridge.updateLightState(light, lightState);   // If no bridge response is required then use this simpler form.
        }
    }
    // If you want to handle the response from the bridge, create a PHLightListener object.
    PHLightListener listener = new PHLightListener() {
        
        @Override
        public void onSuccess() {  
        }
        
        @Override
        public void onStateUpdate(Map<String, String> arg0, List<PHHueError> arg1) {
           Log.w(TAG, "Light has updated");
        }
        
        @Override
        public void onError(int arg0, String arg1) {}

        @Override
        public void onReceivingLightDetails(PHLight arg0) {}

        @Override
        public void onReceivingLights(List<PHBridgeResource> arg0) {}

        @Override
        public void onSearchComplete() {}
    };
    
    @Override
    protected void onDestroy() {
        PHBridge bridge = phHueSDK.getSelectedBridge();
        if (bridge != null) {
            
            if (phHueSDK.isHeartbeatEnabled(bridge)) {
                phHueSDK.disableHeartbeat(bridge);
            }
            
            phHueSDK.disconnect(bridge);
            super.onDestroy();
        }
    }

    private OnClickListener p1Radio() {
        if(p1radioButton.isChecked()) {
            p2radioButton.setChecked(false);
            p3radioButton.setChecked(false);
        }

        return null;
    }

    private OnClickListener p2Radio() {
        if(p2radioButton.isChecked()) {
            p1radioButton.setChecked(false);
            p3radioButton.setChecked(false);
        }
        return null;
    }

    private OnClickListener p3Radio() {
        if(p3radioButton.isChecked()) {
            p1radioButton.setChecked(false);
            p2radioButton.setChecked(false);
        }
        return null;
    }

    public static MyApplicationActivity getInstance() {
        return instance;
    }
}
