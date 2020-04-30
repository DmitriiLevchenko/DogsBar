package com.example.dogbargame.util;

import com.example.dogbargame.R;

public class DishesConverter {
    private int Imagerecurceid;

    public int getImagerecurceid(DishesEnum dishesEnum) {
        switch (dishesEnum)
        {
            case JUICE:
                Imagerecurceid = R.drawable.juice;break;
            case SODA:
                Imagerecurceid = R.drawable.soda;break;
            case PIECE:
                Imagerecurceid = R.drawable.peas;break;
            case SALAD:
                Imagerecurceid = R.drawable.salad;break;
            case STEAK:
                Imagerecurceid = R.drawable.steak;break;
            case ORANGE:
                Imagerecurceid = R.drawable.orange;break;
            case PANCAKE:
                Imagerecurceid = R.drawable.pancake;break;
            default: Imagerecurceid = 0;break;
        }
        return Imagerecurceid;
    }
}
