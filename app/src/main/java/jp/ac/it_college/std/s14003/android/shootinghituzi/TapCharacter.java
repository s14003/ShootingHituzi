package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class TapCharacter extends BaseObject{
    private final Paint paint = new Paint();
    private final Bitmap bitmap;
    public final Rect rect;

    public TapCharacter(Bitmap bitmap, int width, int height) {
        this.bitmap = bitmap;

        int left = (width - bitmap.getWidth()) / 2;
        int top = height - bitmap.getHeight();
        int right = left + bitmap.getWidth();
        int bottom = top + bitmap.getHeight();
        rect = new Rect(left, top, right, bottom);

        yPosition = rect.centerY();
        xPosition = rect.centerX();

    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public boolean isHit(BaseObject object) {
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
}
