package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class Droid extends BaseObject {
    private final Paint paint = new Paint();
    private final Bitmap bitmap;
    private final Rect rect;

    public Droid(Bitmap bitmap, int width, int height) {
        this.bitmap = bitmap;

        int left = (width - bitmap.getWidth()) / 2;
        int top = height - bitmap.getHeight();
        int right = left + bitmap.getWidth();
        int bottom = height + bitmap.getHeight();
        rect = new Rect(left, top, right, bottom);

        yPosition = rect.centerY();
        xPosotion = rect.centerX();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, rect.left, rect.top, paint);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean isAvailable(int width, int height) {
        return true;
    }
}

