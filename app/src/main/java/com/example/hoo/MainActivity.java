package com.example.hoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private mySurfaceView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mv = findViewById(R.id.surface);

        findViewById(R.id.btn_new).setOnClickListener(this);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_exit).setOnClickListener(this);

        mv.change_color(0);

        ArrayList<Integer> list = mv.getColorList();
        LinearLayout opt = findViewById(R.id.layout_color);
        for (int i = 0; i < list.size(); i++) {
            final int index = i;
            int color = list.get(index);
            Button btn = new Button(this);
            btn.setBackgroundColor(color);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mv.change_color(index);
                }
            });
            opt.addView(btn);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_new:
                mv.clear();
                break;
            case R.id.btn_play:
                mv.play();
                break;
            case R.id.btn_exit:
                finish();
                break;
        }

    }
}

