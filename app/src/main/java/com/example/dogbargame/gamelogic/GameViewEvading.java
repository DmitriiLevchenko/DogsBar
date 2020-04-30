package com.example.dogbargame.gamelogic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.dogbargame.R;
import com.example.dogbargame.activitys.GameStarter;
import com.example.dogbargame.activitys.MainActivity;

import java.util.ArrayList;
import java.util.logging.Handler;

public class GameViewEvading extends SurfaceView implements Runnable {
    private boolean checker = false;
    public static int maxX = 36;
    public static int maxY = 6;
    public static float unitW = 0;
    public static float unitH = 0;
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private Context context;
    public static SurfaceHolder surfaceHolder;
    private ArrayList<Dishes> dishes = new ArrayList<>();
    private Bitmap bitmap;
    private  int CREATEDISHTIME = 50;
    private int currentTime = 0;

    public GameViewEvading(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        this.context = context;
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while (gameRunning) {
            draw();
            update();
            control();
            checkIfNewDish();
            checktouch();
        }
    }
    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            if (firstTime) {
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX;
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;
            }
            canvas = surfaceHolder.lockCanvas();
            Bitmap cBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.board);
            if (!checker) {
                checker = true;
                bitmap = Bitmap.createScaledBitmap(
                        cBitmap, surfaceHolder.getSurfaceFrame().width(), surfaceHolder.getSurfaceFrame().height(), false);
            }
            canvas.drawBitmap(bitmap, 0, 0, null);//фо
            for (Dishes dishe : dishes) {
                dishe.drow(paint, canvas);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
            //Log.d(MainActivity.LOGNAME, "there");
        }
    }
    private void update() {
        if (!firstTime) {
            for (Dishes dishe : dishes) {
                dishe.update();
            }
        }
    }

    private void checkIfNewDish() {
        if (currentTime >= CREATEDISHTIME)
        {
            Dishes dish = new Dishes(getContext());
            dishes.add(dish);
            currentTime = 0;
        } else {
            currentTime++;
        }
    }

    private void control() {
        try {
            gameThread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void checktouch()
    {
        ArrayList<Dishes> removeDishes = new ArrayList<>();
        if(GameStarter.checkOnclickListener)
        {
            GameStarter.checkOnclickListener = false;
            for (Dishes dishe : dishes) {
                if(dishe.checkcolision(GameStarter.X,GameStarter.Y))
                {
                    removeDishes.add(dishe);
                }

            }
            for (Dishes dishe : removeDishes) {
                if(dishe.checkcolision(GameStarter.X,GameStarter.Y))
                {
                    dishes.remove(dishe);
                }

            }
            removeDishes.clear();
        }
    }

}
