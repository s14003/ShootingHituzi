package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Canvas;
import android.graphics.Rect;


public class Bullet extends BaseObject {
    private static final float SIZE = 15f;
    public final float alignX;

    Bullet(float alignX, Rect rect) {
        this.alignX = alignX;
        yPosition = rect.centerY();
        xPosotion = rect.centerX();
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void move() {

    }
}
