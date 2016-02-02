package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Missile extends BaseObject {

    private final Paint paint = new Paint();
    public final Bitmap bitmap;
    public final float alignX;

    public Missile(Bitmap bitmap, int height, float alignX) {
        this.bitmap = bitmap;
        yPosition = 0;
        //noinspection SuspiciousNameCombination
        xPosotion = height;
        this.alignX = alignX;

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, xPosotion, yPosition, paint);
    }

    @Override
    public void move() {
        yPosition += 1 * MOVE_WEIGHT;
        xPosotion += alignX * MOVE_WEIGHT;


    }
}
