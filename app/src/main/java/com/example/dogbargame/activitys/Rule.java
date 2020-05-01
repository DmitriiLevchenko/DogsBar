package com.example.dogbargame.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dogbargame.R;

public class Rule extends AppCompatActivity {
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        Inizializatecompinents();
    }
   private void  Inizializatecompinents()
    {
        back = findViewById(R.id.back);

        back.setOnClickListener(CreatesetOnclickListener());
    }
    private View.OnClickListener CreatesetOnclickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.back:
                        Activityfinish();break;
                }
            }
        };
        return onClickListener;
    }
    private  void Activityfinish()
    {
        this.finish();
    }
}
