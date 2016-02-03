package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;


public class Bullet extends BaseObject {
    private static final String TAG = "Bullet";
    private static final float SIZE = 15f;
    public final float alignX;
    private final Paint paint = new Paint();

    Bullet(float alignX, Rect rect) {
        this.alignX = alignX;
        yPosition = rect.centerY();
        xPosition = rect.centerX();
        paint.setColor(Color.RED);
    }

    @Override
    public Type getType() {
        return Type.Bullet;
    }

    @Override
    public boolean isHit(BaseObject object) {
        if (object.getType() != Type.Missile) {
            return false;
        }
        Log.d(TAG,"Bullet is hit!!!");
        return (calcDistance(this, object) < SIZE);
    }

    @Override
    public void draw(Canvas canvas) {
        if (status == STATUS_NORMAL) {
            canvas.drawCircle(xPosition, yPosition, SIZE, paint);
        }
    }

    @Override
    public void move() {
        yPosition -= 1 * MOVE_WEIGHT;
        xPosition += alignX * MOVE_WEIGHT;
    }
}
