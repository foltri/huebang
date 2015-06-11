package com.philips.lighting.huebang;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by folti on 06/06/15.
 */
public class LightEffects {
    public final Effect heart_normal;
    public final Effect heart_beat;
    public final Effect heart_night;
    public final Effect heart_sunset;
    public final Effect heart_arrow;
    public final Effect heart_indian2;
    public final Effect heart_indian3;
    public final Effect heart1_indian4;
    public final Effect heart2_indian4;
    public final Effect heart3_indian4;
    public final Effect shot;
    public final Effect top_storm;
    public final Effect top_normal;
    public final Effect top_night;
    public final Effect top_sunset;
    public final Effect top_sunrise;
    public final Effect top_indian2;
    public final Effect top_indian3;
    public final Effect top_indian4;
    public final Effect ambi1_normal;
    public final Effect ambi2_normal;
    public final Effect ambi3_normal;
    public final Effect ambi1_indian1;
    public final Effect ambi2_indian1;
    public final Effect ambi3_indian1;
    public final Effect ambi1_indian2;
    public final Effect ambi2_indian2;
    public final Effect ambi3_indian2;
    public final Effect ambi1_indian3;
    public final Effect ambi2_indian3;
    public final Effect ambi3_indian3;
    public final Effect ambi1_indian4;
    public final Effect ambi2_indian4;
    public final Effect ambi3_indian4;
    public final Effect dynamite;


    public LightEffects() {
        this.heart_normal = new Effect();
        this.heart_beat = new Effect();
        this.heart_night = new Effect();
        this.heart_sunset = new Effect();
        this.heart_arrow = new Effect();
        this.heart_indian2 = new Effect();
        this.heart_indian3 = new Effect();
        this.heart1_indian4 = new Effect();
        this.heart2_indian4 = new Effect();
        this.heart3_indian4 = new Effect();
        this.shot = new Effect();
        this.top_normal = new Effect();
        this.top_storm = new Effect();
        this.top_night = new Effect();
        this.top_sunset = new Effect();
        this.top_sunrise = new Effect();
        this.top_indian2 = new Effect();
        this.top_indian3 = new Effect();
        this.top_indian4 = new Effect();
        this.ambi1_normal = new Effect();
        this.ambi2_normal = new Effect();
        this.ambi3_normal = new Effect();
        this.ambi1_indian1 = new Effect();
        this.ambi2_indian1 = new Effect();
        this.ambi3_indian1 = new Effect();
        this.ambi1_indian2 = new Effect();
        this.ambi2_indian2 = new Effect();
        this.ambi3_indian2 = new Effect();
        this.ambi1_indian3 = new Effect();
        this.ambi2_indian3 = new Effect();
        this.ambi3_indian3 = new Effect();
        this.ambi1_indian4 = new Effect();
        this.ambi2_indian4 = new Effect();
        this.ambi3_indian4 = new Effect();
        this.dynamite = new Effect();
    }

    public LightEffects init() {
        LightEffects effects = new LightEffects();
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/Hue animations/bang");
        dir.mkdirs();

        File[] filelist = dir.listFiles();
        String[] theNamesOfFiles = new String[filelist.length];
        for (int i = 0; i < theNamesOfFiles.length; i++) {
            theNamesOfFiles[i] = filelist[i].getName();
        }

        for(String name:theNamesOfFiles) {
            File dir1 = new File(sdCard.getAbsolutePath() + "/Hue animations/bang/" + name);
            dir.mkdirs();

            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(dir1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<ControlFrame> tmp = new ArrayList<ControlFrame>();
            try {
                tmp = readJsonStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (name) {
                case "heart_normal.txt":
                    effects.heart_normal.frames = tmp;
                    effects.heart_normal.name = "heart_normal";
                    effects.heart_normal.looping = false;
                    break;
                case "heart_beat.txt":
                    effects.heart_beat.frames = tmp;
                    effects.heart_beat.name = "heart_beat";
                    effects.heart_beat.looping = true;
                    break;
                case "heart_night.txt":
                    effects.heart_night.frames = tmp;
                    effects.heart_night.name = "heart_night";
                    effects.heart_night.looping = true;
                    break;
                case "heart_sunset.txt":
                    effects.heart_sunset.frames = tmp;
                    effects.heart_sunset.name = "heart_sunset";
                    effects.heart_sunset.looping = false;
                    break;
                case "heart_arrow.txt":
                    effects.heart_arrow.frames = tmp;
                    effects.heart_arrow.name = "heart_arrow";
                    effects.heart_arrow.looping = false;
                    break;
                case "heart_indian2.txt":
                    effects.heart_indian2.frames = tmp;
                    effects.heart_indian2.name = "heart_indian2";
                    effects.heart_indian2.looping = false;
                    break;
                case "heart_indian3.txt":
                    effects.heart_indian3.frames = tmp;
                    effects.heart_indian3.name = "heart_indian3";
                    effects.heart_indian3.looping = true;
                    break;
                case "heart1_indian4.txt":
                    effects.heart1_indian4.frames = tmp;
                    effects.heart1_indian4.name = "heart1_indian4";
                    effects.heart1_indian4.looping = true;
                    break;
                case "heart2_indian4.txt":
                    effects.heart2_indian4.frames = tmp;
                    effects.heart2_indian4.name = "heart2_indian4";
                    effects.heart2_indian4.looping = true;
                    break;
                case "heart3_indian4.txt":
                    effects.heart3_indian4.frames = tmp;
                    effects.heart3_indian4.name = "heart3_indian4";
                    effects.heart3_indian4.looping = true;
                    break;
                case "shot.txt":
                    effects.shot.frames = tmp;
                    effects.shot.name = "shot";
                    effects.shot.looping = false;
                    break;
                case "top_storm.txt":
                    effects.top_storm.frames = tmp;
                    effects.top_storm.name = "top_storm";
                    effects.top_storm.looping = true;
                    break;
                case "top_normal.txt":
                    effects.top_normal.frames = tmp;
                    effects.top_normal.name = "top_normal";
                    effects.top_normal.looping = false;
                    break;
                case "top_night.txt":
                    effects.top_night.frames = tmp;
                    effects.top_night.name = "top_night";
                    effects.top_night.looping = false;
                    break;
                case "top_sunset.txt":
                    effects.top_sunset.frames = tmp;
                    effects.top_sunset.name = "top_sunset";
                    effects.top_sunset.looping = false;
                    break;
                case "top_sunrise.txt":
                    effects.top_sunrise.frames = tmp;
                    effects.top_sunrise.name = "top_sunrise";
                    effects.top_sunrise.looping = false;
                    break;
                case "top_indian2.txt":
                    effects.top_indian2.frames = tmp;
                    effects.top_indian2.name = "top_indian2";
                    effects.top_indian2.looping = true;
                    break;
                case "top_indian3.txt":
                    effects.top_indian3.frames = tmp;
                    effects.top_indian3.name = "top_indian3";
                    effects.top_indian3.looping = true;
                    break;
                case "top_indian4.txt":
                    effects.top_indian4.frames = tmp;
                    effects.top_indian4.name = "top_indian4";
                    effects.top_indian4.looping = true;
                    break;
                case "ambi1_normal.txt":
                    effects.ambi1_normal.frames = tmp;
                    effects.ambi1_normal.name = "ambi1_normal";
                    effects.ambi1_normal.looping = false;
                    break;
                case "ambi1_indian1.txt":
                    effects.ambi1_indian1.frames = tmp;
                    effects.ambi1_indian1.name = "ambi1_indian1";
                    effects.ambi1_indian1.looping = true;
                    break;
                case "ambi1_indian2.txt":
                    effects.ambi1_indian2.frames = tmp;
                    effects.ambi1_indian2.name = "ambi1_indian2";
                    effects.ambi1_indian2.looping = true;
                    break;
                case "ambi1_indian3.txt":
                    effects.ambi1_indian3.frames = tmp;
                    effects.ambi1_indian3.name = "ambi1_indian3";
                    effects.ambi1_indian3.looping = true;
                    break;
                case "ambi1_indian4.txt":
                    effects.ambi1_indian4.frames = tmp;
                    effects.ambi1_indian4.name = "ambi1_indian4";
                    effects.ambi1_indian4.looping = true;
                    break;
                case "ambi2_normal.txt":
                    effects.ambi2_normal.frames = tmp;
                    effects.ambi2_normal.name = "ambi2_normal";
                    effects.ambi2_normal.looping = false;
                    break;
                case "ambi2_indian1.txt":
                    effects.ambi2_indian1.frames = tmp;
                    effects.ambi2_indian1.name = "ambi2_indian1";
                    effects.ambi2_indian1.looping = true;
                    break;
                case "ambi2_indian2.txt":
                    effects.ambi2_indian2.frames = tmp;
                    effects.ambi2_indian2.name = "ambi2_indian2";
                    effects.ambi2_indian2.looping = true;
                    break;
                case "ambi2_indian3.txt":
                    effects.ambi2_indian3.frames = tmp;
                    effects.ambi2_indian3.name = "ambi2_indian3";
                    effects.ambi2_indian3.looping = true;
                    break;
                case "ambi2_indian4.txt":
                    effects.ambi2_indian4.frames = tmp;
                    effects.ambi2_indian4.name = "ambi2_indian4";
                    effects.ambi2_indian4.looping = true;
                    break;
                case "ambi3_normal.txt":
                    effects.ambi3_normal.frames = tmp;
                    effects.ambi3_normal.name = "ambi3_normal";
                    effects.ambi3_normal.looping = false;
                    break;
                case "ambi3_indian1.txt":
                    effects.ambi3_indian1.frames = tmp;
                    effects.ambi3_indian1.name = "ambi3_indian1";
                    effects.ambi3_indian1.looping = true;
                    break;
                case "ambi3_indian2.txt":
                    effects.ambi3_indian2.frames = tmp;
                    effects.ambi3_indian2.name = "ambi3_indian2";
                    effects.ambi3_indian2.looping = true;
                    break;
                case "ambi3_indian3.txt":
                    effects.ambi3_indian3.frames = tmp;
                    effects.ambi3_indian3.name = "ambi3_indian3";
                    effects.ambi3_indian3.looping = true;
                    break;
                case "ambi3_indian4.txt":
                    effects.ambi3_indian4.frames = tmp;
                    effects.ambi3_indian4.name = "ambi3_indian4";
                    effects.ambi3_indian4.looping = true;
                    break;
                case "dynamite.txt":
                    effects.dynamite.frames = tmp;
                    effects.dynamite.name = "dynamite";
                    effects.dynamite.looping = false;
                    break;
                default:
                    break;
            }
        }
        return effects;
    }

    public ArrayList<ControlFrame> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        ArrayList<ControlFrame> frames = new ArrayList<ControlFrame>();
        try {
            reader.beginArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        while (reader.hasNext()) {
            ControlFrame message = gson.fromJson(reader, ControlFrame.class);
            frames.add(message);
        }
        try {
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frames;
    }
}
