package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;


public class Droid extends BaseObject {
    private final String TAG = "Droid";
    private final Paint paint = new Paint();
    private final Bitmap bitmap;
    public final Rect rect;

    public Droid(Bitmap bitmap, int width, int height) {
        this.bitmap = bitmap;

        int left = (width - bitmap.getWidth()) / 2;
        int top = height - bitmap.getHeight();
        int right = left + bitmap.getWidth();
        int bottom = top + bitmap.getHeight();
        rect = new Rect(left, top, right, bottom);

        yPosition = rect.centerY();
        xPosition = rect.centerX();
        paint.setColor(Color.RED);
    }

    @Override
    public Type getType() {
        return Type.Droid;
    }

    @Override
    public boolean isHit(BaseObject object) {
        if (object.getType() == Type.Missile) {
            if (status == STATUS_DESTROYED) {
                Log.d(TAG, "STATUS_DESTROYED True");
                return false;
            } else {
                Log.d(TAG, "droid is hit!!");
                return rect.contains(Math.round(object.xPosition), Math.round(object.yPosition));
            }
        }
        return false;

    }

    @Override
    public void draw(Canvas canvas) {
        if (status == STATUS_NORMAL) {
            canvas.drawBitmap(bitmap, rect.left, rect.top, paint );
        }
    }

    @Override
    public void move() {

    }

    @Override
    public boolean isAvailable(int width, int height) {
        return true;
    }
}

