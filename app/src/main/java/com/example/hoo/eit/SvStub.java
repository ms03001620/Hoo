package com.example.hoo.eit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.hoo.Scribbler;

/**
 * Created by mark on 2018/4/1
 */

public abstract class SvStub extends SurfaceView implements SurfaceHolder.Callback, IScribbleColor {
    protected SurfaceHolder mHolder;
    private int color_idx;

    public SvStub(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        color_idx = 0;
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        begin_record();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            record_down((int) event.getX(), (int) event.getY(), color_idx);
        } else if (action == MotionEvent.ACTION_MOVE) {
            record_move((int) event.getX(), (int) event.getY(), color_idx);
        }
        return true;
    }

    public abstract void begin_record();

    public abstract void record_down(int x, int y, int c_idx);

    public abstract void record_move(int x, int y, int c_idx);

    public void change_color(int i){
        color_idx = i;
    }
}
