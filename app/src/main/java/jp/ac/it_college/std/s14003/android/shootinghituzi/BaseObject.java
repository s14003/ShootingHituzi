package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Canvas;

/**
 * Created by s14003 on 16/01/23.
 */
public abstract class BaseObject {

    static final float MOVE_WEIGHT = 3.0f;

    float yPosition;
    float xPosotion;

    public abstract void draw(Canvas canvas);
    public boolean isAvailable(int width, int height) {
        if (yPosition < 0 || xPosotion < 0 || yPosition > height || xPosotion > width) {
            return false;
        }
        return true;
    }
    public abstract void move();
}
