package com.example.hoo.eit;

import android.view.SurfaceHolder;

import com.example.hoo.Scribbler;

/**
 * Created by mark on 2018/4/1
 */

public class ScriProxy implements IScribble {

    private Scribbler scr;

    public ScriProxy(SurfaceHolder holder, IScribbleColor sv) {
        scr = new Scribbler(holder, sv);
    }

    @Override
    public void begin_record() {
        scr.begin_record();
    }

    @Override
    public void record_down(int x, int y, int cx) {
        scr.record_down(x, y, cx);
    }

    @Override
    public void record_move(int x, int y, int cx) {
        scr.record_move(x, y, cx);
    }

    @Override
    public void clear() {
        scr.clear();
    }

    @Override
    public void play() {
        scr.play();
    }
}
