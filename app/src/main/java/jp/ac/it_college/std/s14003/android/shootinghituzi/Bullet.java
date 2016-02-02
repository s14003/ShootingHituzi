package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class Bullet extends BaseObject {
    private static final float SIZE = 15f;
    public final float alignX;
    private final Paint paint = new Paint();
    private final Bitmap bitmap;
    private Rect rect;

    Bullet(Bitmap bitmap,float alignX, Rect rect) {
        this.bitmap = bitmap;
        this.alignX = alignX;
        yPosition = rect.centerY();
        xPosition = rect.centerX();
    }

    @Override
    public Type getType() {
        return Type.Bullet;
    }

    @Override
    public boolean isHit(BaseObject object) {
        return object.getType() == Type.Missile && (calcDistance(this, object) < SIZE);
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
