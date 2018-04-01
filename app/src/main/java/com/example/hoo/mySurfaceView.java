package com.example.hoo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by mark on 2018/4/1
 */

public class mySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Scribbler scr;
    private int color_idx;

    mySurfaceView(Context context) throws InterruptedException {
        super(context);
        init();
    }

    public mySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        color_idx = 0;
        mHolder = getHolder();
        mHolder.addCallback(this);
        scr = new Scribbler(mHolder);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        scr.begin_record();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int w, int h) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            scr.record_down((int) event.getX(), (int) event.getY(), color_idx);
        } else if (action == MotionEvent.ACTION_MOVE) {
            scr.record_move((int) event.getX(), (int) event.getY(), color_idx);
        }
        return true;
    }

    public void change_color(int c) {
        color_idx = c;
    }

    public void clear() {
        scr.clear();
    }

    public void play() {
        scr.play();
    }

    public ArrayList<Integer> getColorList() {
        return scr.getColorList();
    }
}
