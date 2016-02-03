package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Missile extends BaseObject {
    private static final String TAG = "Missile";
    private static final float SIZE = 10f;
    private final Paint paint = new Paint();
    public final Bitmap bitmap;
    public final float alignX;
    private Rect rect;

    public Missile(Bitmap bitmap, int height, float alignX) {
        this.bitmap = bitmap;
        yPosition = 0;
        xPosition = height;
        this.alignX = alignX;

    }

    @Override
    public Type getType() {
        return Type.Missile;
    }

    @Override
    public boolean isHit(BaseObject object) {
        if (object.getType() == Type.Missile) {
            Log.d(TAG,"Type.Missile is True!");
            return false;
        }

        if (status == STATUS_DESTROYED) {
            Log.d(TAG,"STATUS_DESTROYED True");
            return false;
        } else {
            Log.d(TAG, "Type.Missile is false");
            return (calcDistance(this, object) < SIZE);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (status == STATUS_NORMAL) {
            canvas.drawBitmap(bitmap, xPosition, yPosition, paint);
        }
    }

    @Override
    public void move() {
        yPosition += 1 * MOVE_WEIGHT;
        xPosition += alignX * MOVE_WEIGHT;


    }
}
