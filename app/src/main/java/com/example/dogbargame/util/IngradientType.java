package com.example.dogbargame.util;

import android.widget.ImageView;

public class IngradientType {
    private DishesEnum ingradientType;
    private ImageView imageView;
    private boolean uses;

    public boolean isUses() {
        return uses;
    }

    public void setUses(boolean uses) {
        this.uses = uses;
    }

    public IngradientType(DishesEnum ingradientType, ImageView imageView, boolean uses) {
        this.ingradientType = ingradientType;
        this.imageView = imageView;
        this.uses = uses;
    }

    public DishesEnum getIngradientType() {
        return ingradientType;
    }

    public void setIngradientType(DishesEnum ingradientType) {
        this.ingradientType = ingradientType;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
