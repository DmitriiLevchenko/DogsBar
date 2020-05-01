package com.example.dogbargame.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dogbargame.R;
import com.example.dogbargame.gamelogic.GameViewEvading;
import com.example.dogbargame.util.ChangeImage;
import com.example.dogbargame.util.DishesConverter;
import com.example.dogbargame.util.DishesEnum;
import com.example.dogbargame.util.IngradientType;
import com.example.dogbargame.util.Win;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameStarter extends AppCompatActivity implements View.OnTouchListener, ChangeImage, Win {
    private LinearLayout gameLayout;
    private ImageView item1, item2, item3, item4, dog;
    private TextView currenttime;
    public static boolean checkOnclickListener = false;
    private int TIMER = 15;
    private Timer timer;
    public static ArrayList<IngradientType> ingradientarray = new ArrayList<>();
    private DishesEnum currentImageId;
    public static int quantatyofingradiens = 0;
    public static float X, Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_starter);
        InizializateCompinents();


    }

    private void InizializateCompinents() {
        ingradientarray.clear();
        quantatyofingradiens = 0;
        ingradientarray.clear();
        GameViewEvading gameView = new GameViewEvading(this, this, this);
        GameViewEvading.gameRunning = true;
        GameViewEvading.gamewin = false;
        gameLayout = findViewById(R.id.gamelayout);
        gameLayout.addView(gameView);
        gameLayout.setOnTouchListener(this);
        currenttime = findViewById(R.id.time);
        currenttime.setText(TIMER + "");
        dog = findViewById(R.id.dog);


        item1 = findViewById(R.id.boarditem1);
        item2 = findViewById(R.id.boarditem2);
        item3 = findViewById(R.id.boarditem3);
        item4 = findViewById(R.id.boarditem4);

        item1.setImageResource(GenerateIngredients());
        IngradientType ingradientType1 = new IngradientType(currentImageId, item1, false);
        item2.setImageResource(GenerateIngredients());
        IngradientType ingradientType2 = new IngradientType(currentImageId, item2, false);
        item3.setImageResource(GenerateIngredients());
        IngradientType ingradientType3 = new IngradientType(currentImageId, item3, false);
        item4.setImageResource(GenerateIngredients());
        IngradientType ingradientType4 = new IngradientType(currentImageId, item4, false);

        ingradientarray.add(ingradientType1);
        ingradientarray.add(ingradientType2);
        ingradientarray.add(ingradientType3);
        ingradientarray.add(ingradientType4);
        Usetip();
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
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (TIMER > 0 && GameViewEvading.gameRunning) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TIMER--;
                            currenttime.setText(TIMER + "");
                        }
                    });
                }
                else {
                    if (!GameViewEvading.gamewin) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                createDialogFinish();
                            }
                        });
                    }
                    GameViewEvading.gameRunning = false;
                    cancel();
                }


            }
        }, 2000, 1000);
    }

    private int GenerateIngredients() {
        int pick = new Random().nextInt(DishesEnum.values().length);
        currentImageId = DishesEnum.values()[pick];
        DishesConverter dishesConverter = new DishesConverter();
        return dishesConverter.getImagerecurceid(DishesEnum.values()[pick]);
    }

    @Override
    public void ChangeImage(int index) {
        final int indesl = index;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ingradientarray.get(indesl).getImageView().setImageResource(R.drawable.ic_check_box_black_24dp);
                switch (GameStarter.quantatyofingradiens) {
                    case 1:
                        dog.setImageResource(R.drawable.dog_states_1);
                        break;
                    case 2:
                        dog.setImageResource(R.drawable.dog_states_2);
                        break;
                    case 3:
                        dog.setImageResource(R.drawable.dog_states_3);
                        break;
                    case 4:
                        dog.setImageResource(R.drawable.dog_states_4);
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GameViewEvading.gameRunning = false;
        timer.cancel();
    }

    private void createDialogFinish() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameStarter.this);
        builder.setTitle(R.string.dialog_about_title_gameover);
        builder.setMessage(R.string.dialog_about_message_gameover);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                recreateActivity();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Activityfinish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showDialogWin() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createDialogWin();
            }
        });
    }

    private void createDialogWin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameStarter.this);
        builder.setTitle(R.string.dialog_about_title_win);
        builder.setMessage(R.string.dialog_about_message_win);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                recreateActivity();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void recreateActivity() {
        recreate();
    }

    private void Activityfinish() {
        finish();
    }
}


