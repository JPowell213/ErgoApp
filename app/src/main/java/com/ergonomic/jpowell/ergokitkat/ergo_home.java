package com.ergonomic.jpowell.ergokitkat;


import android.annotation.SuppressLint;
        import android.annotation.TargetApi;
        import android.os.Build;
        import android.os.CountDownTimer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import java.sql.Time;
        import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.KITKAT)
@SuppressLint("NewApi")
public class ergo_home extends AppCompatActivity {

    Button btnStart, btnStop;
    TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergo_home);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        textViewTime.setText("00:01:00");

        final CounterClass timer = new CounterClass(180000, 1000);
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

        @Override
        public void onFinish() {
            textViewTime.setText("Time to move.");

        }
    }
}
//Show ergo excersises
//Allow user to reset timer or stop it.
