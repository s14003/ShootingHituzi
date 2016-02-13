package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private static final long SCORE_LEVEL = 100;
    private Droid droid;
    private final List<BaseObject> bulletList = new ArrayList<>();
    private final List<BaseObject> missileList = new ArrayList<>();
    private static final int MISSILE_LAUNCH_WEIGHT = 50;
    private final Random random = new Random();
    private static final long FPS = 60;
    private Handler handler;
    private static final float SCORE_TEXT_SIZE = 50.0f;
    private final Paint paint = new Paint();
    private long score;
    private Callback callback;
    private int num = 10;
    private DrawThread drawThread;


    private class DrawThread extends Thread {
        boolean isFinished;
        @Override
        public void run() {
            SurfaceHolder holder = getHolder();

            //noinspection StatementWithEmptyBody
            while (!isFinished) {
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    drawGame(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }
                try {
                    sleep(1000 / FPS);
                } catch (InterruptedException e) {
                    Log.d("GameView_run", "Error");
                }
            }
        }
    }


    public void startDrawThread() {
        stopDrawThread();
        drawThread = new DrawThread();
        drawThread.start();
    }

    public boolean stopDrawThread() {
        if (drawThread == null) {
            return false;
        }
        drawThread.isFinished = true;
        drawThread = null;
        return true;
    }


    public GameView(Context context) {
        super(context);

        paint.setColor(Color.BLACK);
        paint.setTextSize(SCORE_TEXT_SIZE);
        paint.setAntiAlias(true);
        handler = new Handler();
        getHolder().addCallback(this);
    }

    private void drawGame(Canvas canvas) {

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.drawColor(Color.WHITE);
        if (droid == null) {
            Bitmap DroidBitmap =
                    BitmapFactory.decodeResource(getResources(),R.drawable.gameinu);
            droid = new Droid(DroidBitmap, width, height);
        }

        drawObjectList(canvas, missileList, width, height);
        drawObjectList(canvas, bulletList, width, height);

        for (int i = 0; i < missileList.size(); i++) {
            BaseObject missile = missileList.get(i);
            for (int j = 0; j < bulletList.size(); j++) {
                BaseObject bullet = bulletList.get(j);

                if (missile.isHit(bullet)) {
                    missile.hit();
                    bullet.hit();
                    score += 10;
                }
            }
        }

        for (int i = 0; i < missileList.size(); i++) {
            BaseObject missile = missileList.get(i);
            if (missile.isHit(droid)) {
                missile.hit();
                droid.hit();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onGameOver(score);
                    }
                });
                break;
            }
        }

        //難易度
        if (random.nextInt(MISSILE_LAUNCH_WEIGHT) == 0) {
            long count = score / SCORE_LEVEL + 1;
            for (int i = 0; i < count; i++) {
                launchMissile();
            }
        }

        droid.draw(canvas);
        canvas.drawText("Score:" + score, 0, SCORE_TEXT_SIZE, paint);
    }

    private void launchMissile() {

        int toX = random.nextInt(getWidth());
        int fromX = random.nextInt(getWidth());
        float alignX = (toX - fromX) / (float) getHeight();

        Bitmap MissileBitmap =
                BitmapFactory.decodeResource(getResources(), R.drawable.inukyuu);
        //noinspection SuspiciousNameCombination
        Missile missile = new Missile(MissileBitmap, fromX, alignX);
        missileList.add(missile);

    }
    private static void drawObjectList(
            Canvas canvas, List<BaseObject> objectList, int width, int height) {
        for (int i = 0; i < objectList.size(); i++) {
            BaseObject object = objectList.get(i);
            if (object.isAvailable( width, height)) {
                object.move();
                object.draw(canvas);
            } else {
                objectList.remove(object);
                i--;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fire(event.getY(),event.getX());
                break;
        }
        return super.onTouchEvent(event);
    }
    private void fire(float y, float x) {
        float alignX = (x - droid.rect.centerX()) / Math.abs(y - droid.rect.centerY());
        Bullet bullet = new Bullet(alignX, droid.rect);
        bulletList.add(0, bullet);
    }

    public interface Callback {
        void onGameOver (long score);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startDrawThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopDrawThread();
    }
}
