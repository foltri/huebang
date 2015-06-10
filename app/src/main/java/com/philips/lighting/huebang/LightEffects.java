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
    public final Effect shot;
    public final Effect top_storm;
    public final Effect top_normal;
    public final Effect top_night;
    public final Effect top_sunset;
    public final Effect top_sunrise;

    public LightEffects() {
        this.heart_normal = new Effect();
        this.heart_beat = new Effect();
        this.shot = new Effect();
        this.top_normal = new Effect();
        this.top_storm = new Effect();
        this.top_night = new Effect();
        this.top_sunset = new Effect();
        this.top_sunrise = new Effect();
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
                    break;
                case "heart_beat.txt":
                    effects.heart_beat.frames = tmp;
                    effects.heart_beat.name = "heart_beat";
                    break;
                case "shot.txt":
                    effects.shot.frames = tmp;
                    effects.shot.name = "shot";
                    break;
                case "top_storm.txt":
                    effects.top_storm.frames = tmp;
                    effects.top_storm.name = "top_storm";
                    break;
                case "top_normal.txt":
                    effects.top_normal.frames = tmp;
                    effects.top_normal.name = "top_normal";
                    break;
                case "top_night.txt":
                    effects.top_night.frames = tmp;
                    effects.top_night.name = "top_night";
                    break;
                case "top_sunset.txt":
                    effects.top_sunset.frames = tmp;
                    effects.top_sunset.name = "top_sunset";
                    break;
                case "top_sunrise.txt":
                    effects.top_sunrise.frames = tmp;
                    effects.top_sunrise.name = "top_sunrise";
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
