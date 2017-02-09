package com.fira.noraersjef;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    ImageView sjefen;
    Button sjefenKnapp;
    TextView sjefenTekst;
    MediaPlayer mp;
    int AutoclickValue = 0;
    int Value = 0;
    int clickValue = 1;
    int upgradePrice1 = 100;
    TextView score;
    Boolean paused = false;
    Boolean upgradesExtended = false;
    Button upgradeButton1;
    Button upgradesButton;
    Handler handlerAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sjefen = (ImageView) findViewById(R.id.sjefen);
        sjefenKnapp = (Button) findViewById(R.id.sjefenKnapp);
        sjefenTekst = (TextView) findViewById(R.id.sjefenTekst);
        score = (TextView) findViewById(R.id.Score);
        sjefen.setVisibility(View.GONE);
        sjefenTekst.setVisibility(View.GONE);
        score.setVisibility(View.GONE);
        upgradeButton1 = (Button) findViewById(R.id.upgradeButton1);
        upgradeButton1.setText("+1 sjef per sekund (" + Integer.toString(upgradePrice1) + (")"));
        upgradeButton1.setVisibility(View.GONE);
        upgradesButton = (Button) findViewById(R.id.upgradesButton);
        upgradesButton.setVisibility(View.GONE);



    }

    private void startAutoclick() {
        handlerAuto = new Handler();
        handlerAuto.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!paused) {
                    Value = Value + AutoclickValue;
                    score.setText(Integer.toString(Value));
                    startAutoclick();
                }
            }
        }, 1000);
    }

    public void noraErSjef(View view) {
        sjefen.setVisibility(View.VISIBLE);
        sjefenKnapp.setVisibility(View.GONE);
        mp = MediaPlayer.create(this, R.raw.lost);
        mp.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sjefenTekst.setVisibility(View.VISIBLE);
            }
        }, 1000);
        score.setVisibility(View.VISIBLE);
        upgradesButton.setVisibility(View.VISIBLE);
        startAutoclick();

    }

    public void sjefCLicked(View view) {
        Value = Value + clickValue;
        score.setText(Integer.toString(Value));
    }

    public void purchaseUpgrades (View view) {
        if (upgradesExtended) {
            upgradesExtended = false;
            upgradeButton1.setVisibility(View.GONE);
            upgradesButton.setText("Oppgraderinger");
        }else {
            upgradesExtended = true;
            upgradeButton1.setVisibility(View.VISIBLE);
            upgradesButton.setText("Lukk");


        }
    }

    public void purchaseUpgrade1 (View view) {
        if (Value >= upgradePrice1) {
            Value = Value - upgradePrice1;
            score.setText(Integer.toString(Value));
            AutoclickValue = AutoclickValue + 1;
            upgradePrice1 = upgradePrice1 + 100;
            upgradeButton1.setText("+1 sjef per sekund (" + Integer.toString(upgradePrice1) + (")"));
        }
    }


    @Override
    protected void onPause() {
        paused = true;
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        paused = false;
    }
}
