package com.example.hoo.eit;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * Created by mark on 2018/4/1
 */

public class mySV extends SvStub {

    private ScriProxy scr;
    private ArrayList<Integer> colorList;

    public mySV(Context context, AttributeSet attrs) {
        super(context, attrs);
        scr = new ScriProxy(mHolder, this);
    }

    @Override
    public void begin_record() {
        scr.begin_record();
    }

    @Override
    public void record_down(int x, int y, int c_idx) {
        scr.record_down(x, y, c_idx);
    }

    @Override
    public void record_move(int x, int y, int c_idx) {
        scr.record_move(x, y, c_idx);
    }

    public void clear() {
        scr.clear();
    }

    public void play() {
        scr.play();
    }

    public ArrayList<Integer> getColorList(){
        return colorList;
    }

    @Override
    public ArrayList<Integer> getScribbleColors() {
        colorList = new ArrayList<>();
        colorList.add(Color.WHITE);
        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.YELLOW);
        colorList.add(Color.CYAN);
        return colorList;
    }
}
