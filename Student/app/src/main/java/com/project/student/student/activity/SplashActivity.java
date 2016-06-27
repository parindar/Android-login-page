package com.project.student.student.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.project.student.student.R;

/**
 * Created by parinda on 5/31/16.
 */
public class SplashActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this,LoginActicvity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

    }
}
