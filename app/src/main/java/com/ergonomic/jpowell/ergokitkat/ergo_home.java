package com.ergonomic.jpowell.ergokitkat;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.lang.Object;



@TargetApi(Build.VERSION_CODES.KITKAT)
@SuppressLint("NewApi")
public class ergo_home extends AppCompatActivity {

    Button btnStart, btnStop;
    TextView textViewTime;
    final CounterClass timer = new CounterClass(3600000, 1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergo_home);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);


        textViewTime.setText("01:00:00");


        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                timer.start();

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();

            }
        });
    }


    private void stopCounting() {

        timer.cancel();
    }

    private void startCounting(long totalTime) {
        final CounterClass timer2 = new CounterClass(totalTime, 1) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

            }
        };

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @SuppressLint("NewApi")
        @Override
        public void onTick(long millisUntilFinished) {


            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);


        }
        //Notify the user that time has ended


        @Override
        public void onFinish() {
            textViewTime.setText("Time to move.");
            Vibrator mVibrator;
            mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            mVibrator.vibrate(3000);

            startActivity(new Intent(ergo_home.this, Choice.class));


        }
    }
}






