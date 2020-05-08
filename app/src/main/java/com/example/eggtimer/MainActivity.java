package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar seekBar;
    Button controllerButton;
    Boolean counterIsActive=false;
    CountDownTimer countDownTimer;
    public  void  updateTimer(int secondsleft){
        int minutes,seconds;
        minutes=secondsleft/60;
        seconds=secondsleft%60;
        String sMinutes = String.format("%02d",seconds);
        timerTextView.setText(Integer.toString(minutes)+":"+sMinutes);

    }
    public  void  controlTimer(View view){
        if(counterIsActive==false) {
            counterIsActive = true;
            seekBar.setEnabled(false);
            controllerButton.setText("Stop");

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();

                }
            }.start();
        }else{
            timerTextView.setText("0:30");
            seekBar.setProgress(30);
            controllerButton.setText("Go!");
            countDownTimer.cancel();
            seekBar.setEnabled(true);
            counterIsActive=false;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
         timerTextView= (TextView) findViewById(R.id.timerTextView);
         controllerButton = (Button)findViewById(R.id.controllerButton);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
