package com.pgr.yellow;

/**
 * Created by BMS0020 on 6/23/2015.
 */
import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        try {
            //Parse.initialize(this, ApplicationID, ClientKey); // Your Application ID and Client Key are defined elsewhere

           Parse.enableLocalDatastore(this);
           Parse.initialize(this, "mluS4CbVgOSQ5hOrpGZqgzEi1Uceu4TO9jEMuXNa", "XTlI1NqXAtGuhEZ9lG5Six38fpFOctosOjV2pgxG");


           // Parse.enableLocalDatastore(this);
            //Parse.initialize(this, "7n23RgTUsd06ntqMyWuRcfBclIGrPfAIrRXtBOtD", "ogTdjemI05pmUbPonqJdFtWag57oHbWyn8hFjG2B");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}