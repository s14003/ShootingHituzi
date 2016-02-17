package jp.ac.it_college.std.s14003.android.shootinghituzi;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Boss extends BaseObject {
    private final Paint paint = new Paint();
    private final Bitmap bitmaps;
    public final Rect rect;

    public Boss(Bitmap bitmap, int width, int height) {
        this.bitmaps = bitmap;
        int left =  (width - bitmap.getWidth()) / 2;
        int right = left + Math.round(bitmap.getWidth());
        int bottom = height + bitmap.getHeight() ;
        int top = bottom - Math.round(bitmap.getHeight());

        rect = new Rect(left, top, right, bottom);
        yPosition = rect.centerY();
        xPosition = rect.centerX();


    }
    @Override
    public Type getType() {
        return Type.Boss;
    }

    @Override
    public boolean isHit(BaseObject object) {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        if (status == STATUS_NORMAL) {
            canvas.drawBitmap(bitmaps, rect.left, rect.top, paint );
        }
    }

    @Override
    public void move() {

    }
}
