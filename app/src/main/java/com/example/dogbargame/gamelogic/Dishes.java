package com.example.dogbargame.gamelogic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.dogbargame.R;
import com.example.dogbargame.activitys.MainActivity;
import com.example.dogbargame.util.DishesConverter;
import com.example.dogbargame.util.DishesEnum;

import java.util.Random;

public class Dishes {
    protected float x; // координаты
    protected float y;
    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка
    private int radius = 2; // радиус
    private float Speed = (float) 0.15; // максимальная скорость

    void init(Context context) { // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int) (size * GameViewEvading.unitW), (int) (size * GameViewEvading.unitH), false);
        cBitmap.recycle();
    }

    void drow(Paint paint, Canvas canvas) { // рисуем картинку
        canvas.drawBitmap(bitmap, x * GameViewEvading.unitW, y * GameViewEvading.unitH, paint);
    }

    public Dishes(Context context) {
        int pick = new Random().nextInt(DishesEnum.values().length);
        DishesConverter dishesConverter = new DishesConverter();
        bitmapId = dishesConverter.getImagerecurceid(DishesEnum.values()[pick]);
        y = 1;
        x = GameViewEvading.maxX - radius;
        Log.d(MainActivity.LOGNAME, "x = " + x);
        size = radius * 2;
        speed = Speed;
        init(context);
    }

    public void update() {
        x -= speed;
    }

    public boolean isEnd() {
        //Log.d(MainActivity.LOGNAME, String.valueOf(this.y*blocksize+size/2*blocksize) + "<" + height);
        if (this.x >= GameViewEvading.surfaceHolder.getSurfaceFrame().width()) return true;
        else return false;
    }

    public boolean checkcolision(float x, float y) {
        x = x / GameViewEvading.unitW;
        y = y / GameViewEvading.unitH;
        int GameStarX = (int) this.x;
        int GameStarY = (int) this.y;
        int GameStarR = this.radius;
        int maxX = GameStarX + GameStarR;
        int minX = GameStarX - GameStarR;
        int maxY = GameStarY + GameStarR;
        int minY = GameStarY - GameStarR;
        if (x < maxX && x > minX) {
            return true;
        }
        return false;
    }
}
