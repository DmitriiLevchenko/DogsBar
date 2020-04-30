package com.example.dogbargame.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.dogbargame.R;

public class MainActivity extends AppCompatActivity  {
    public static String LOGNAME = "GameLog";
    private Button start, rule, exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InizializateComponents();
    }

    private void InizializateComponents() {
        start = findViewById(R.id.start);
        rule = findViewById(R.id.rule);
        exit = findViewById(R.id.exit);
        //
        start.setOnClickListener(CreatesetOnclickListener());
        rule.setOnClickListener(CreatesetOnclickListener());
        exit.setOnClickListener(CreatesetOnclickListener());

    }

    private View.OnClickListener CreatesetOnclickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.start:
                        CreateStartIntent();
                        break;
                    case R.id.exit:
                        CreateExitIntent();
                        break;
                    case R.id.rule:
                        CreateRuletIntent();
                        break;
                }
            }
        };
        return onClickListener;
    }

    private void CreateStartIntent() {
        startActivity(new Intent(MainActivity.this, GameStarter.class));
    }

    private void CreateExitIntent() {
        this.finish();
    }

    private void CreateRuletIntent() {

    }


}
