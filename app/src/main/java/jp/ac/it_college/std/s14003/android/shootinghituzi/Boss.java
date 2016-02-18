package jp.ac.it_college.std.s14003.android.shootinghituzi;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Boss extends BaseObject {
    private String TAG = "Boss";
    private final Paint paint = new Paint();
    private final Bitmap bitmaps;
    public final Rect rect;

    public Boss(Bitmap bitmap, int width, int height) {
        this.bitmaps = bitmap;
        int top = (height + bitmap.getHeight()) / 20;
        int left =  (width - bitmap.getWidth()) / 2;
        int right = left + bitmap.getWidth();
        int bottom = top - bitmap.getHeight();

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
        if (object.getType() == Type.Missile) {

            if (status == STATUS_DESTROYED) {

                Log.d(TAG, "STATUS_DESTROYED True");
                return false;

            } else {
                Log.d(TAG, "droid is hit!!");
                return rect.contains(Math.round(object.xPosition), Math.round(object.yPosition));
            }
        } else {
            return false;
        }
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

    @Override
    public boolean isAvailable(int width, int height) {
        return true;
    }
}
