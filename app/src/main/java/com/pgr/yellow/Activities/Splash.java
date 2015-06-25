package com.pgr.yellow.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

import com.pgr.yellow.R;
import android.os.Handler;


public class Splash extends Activity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        //ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

        //if (cd.isConnectingToInternet()) {
            //Create a thread to wait for few seconds
            Thread splashThread = new Thread() {
                public void run() {
                    try {
                        sleep(5 * 1000);
                        //Create an intent to display login screen
                        Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(loginIntent);
                        //Remove the splash activity
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            splashThread.start();
        //}
        /*else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Splash.this);
            //builder.setTitle("No Internet");
            builder.setMessage("Please check your network settings and try again later");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Splash.super.onBackPressed();
                    Splash.this.finish();
                    dialog.dismiss();
                }
            });
            AlertDialog showDialog = builder.create();
            showDialog.show();
        }*/

     /*   new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            //textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(loginIntent);
                        //Remove the splash activity
                        finish();
                    }
                }
            }
        }).start();*/


    }
}
