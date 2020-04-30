package com.example.dogbargame.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dogbargame.R;
import com.example.dogbargame.gamelogic.GameViewEvading;

import java.util.Timer;
import java.util.TimerTask;

public class GameStarter extends AppCompatActivity implements View.OnTouchListener {
    private LinearLayout gameLayout;
    private ImageView item1,item2,item3,item4;
    private TextView currenttime;
    public static boolean checkOnclickListener = false;
    private int TIMER = 15;
    public static float X, Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_starter);
        InizializateCompinents();

        //Usetip();
    }
    private void InizializateCompinents()
    {
        GameViewEvading gameView = new GameViewEvading(this);
        gameLayout = findViewById(R.id.gamelayout);
        gameLayout.addView(gameView);
        gameLayout.setOnTouchListener(this);
        currenttime = findViewById(R.id.time);
        currenttime.setText(TIMER + "");


        item1 = findViewById(R.id.boarditem1);
        item2 = findViewById(R.id.boarditem2);
        item3 = findViewById(R.id.boarditem3);
        item4 = findViewById(R.id.boarditem4);

        
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        event.getX();
        event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                GameStarter.X = event.getX();
                checkOnclickListener = true;
                GameStarter.Y = event.getY();
                break;

        }
        return true;
    }

    private void Usetip() {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override

            public void run() {
                while (Integer.getInteger(currenttime.getText().toString()).intValue() != 0) {
                    int time = Integer.getInteger(currenttime.getText().toString()) - 1;
                    currenttime.setText(time + "");
                }
                cancel();
            }
        }, 100, 1000);

    }
}
