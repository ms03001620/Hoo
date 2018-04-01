package com.example.hoo.eit;

/**
 * Created by mark on 2018/4/1
 */

public interface IScribble {
    void begin_record();

    void record_down(int x, int y, int cx);

    void record_move(int x, int y, int cx);

    void clear();

    void play();
}
