package com.example.hoo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mark on 2018/4/1
 */

public class Scribbler {
    private ArrayList<dwPoint> poList;
    private ArrayList<Integer> colorList;
    private SurfaceHolder mHolder;
    private Paint paint;
    public static long initial_time;

    public Scribbler(SurfaceHolder holder) {
        mHolder = holder;
        paint = new Paint();
        poList = new ArrayList<>();
        colorList = new ArrayList<>();

        colorList.add(Color.WHITE);
        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.YELLOW);
    }

    public ArrayList<Integer> getColorList() {
        return colorList;
    }

    public void clear() {
        Canvas canvas = mHolder.lockCanvas();
        canvas.drawColor(Color.BLACK);
        mHolder.unlockCanvasAndPost(canvas);
        begin_record();
    }

    public void begin_record() {
        initial_time = System.currentTimeMillis();
        poList.clear();
    }

    public void record_down(int x, int y, int c_idx) {
        dwPoint po = new dwPoint(x, y, 0, colorList.get(c_idx).intValue());
        poList.add(po);
    }

    public void record_move(int x, int y, int c_idx) {
        dwPoint po = new dwPoint(x, y, 1, colorList.get(c_idx).intValue());
        poList.add(po);
        quick_draw();
    }

    public void play() {
        subThread th = new subThread(this);
        th.start();
    }

    public void draw() {
        long curr_time;
        long base_time = 0;
        int k = 0;
        dwPoint po;

        Iterator<dwPoint> it = poList.iterator();

        while (it.hasNext()) {
            po = it.next();
            if (k == 0) {
                base_time = System.currentTimeMillis();
            }
            k++;
            do {
                curr_time = System.currentTimeMillis() - base_time;
            } while (curr_time < po.m_timeSpan);

            paint_to(k);
        }
    }

    class subThread extends Thread {
        private Scribbler scr;

        subThread(Scribbler s) {
            scr = s;
        }

        public void run() {
            scr.draw();
        }
    }

    public void paint_to(int k) {
        Canvas canvas = mHolder.lockCanvas();
        canvas.drawColor(Color.BLACK);
        dwPoint po;
        int lastX = 0;
        int lastY = 0;
        Iterator<dwPoint> it = poList.iterator();
        for (int i = 0; i < k; i++) {
            po = it.next();
            if (po.m_type == 0) {
                lastX = po.m_x;
                lastY = po.m_y;
            } else {
                paint.setColor(po.m_color);
                paint.setStrokeWidth(2);
                canvas.drawLine(lastX, lastY, po.m_x, po.m_y, paint);
                lastX = po.m_x;
                lastY = po.m_y;
            }
        }
        mHolder.unlockCanvasAndPost(canvas);
    }

    public void quick_draw() {
        int size = poList.size();
        paint_to(size);
    }

    class dwPoint {
        public int m_x, m_y, m_type;
        public long m_timeSpan;
        public int m_color;

        public dwPoint() {
        }

        public dwPoint(int x, int y, int ty, int cc) {
            m_x = x;
            m_y = y;
            m_type = ty;
            m_color = cc;

            m_timeSpan = System.currentTimeMillis() - Scribbler.initial_time;
        }

    }
}
