package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Droid droid;
    private static final int MISSILE_LAUNCH_WEIGHT = 50;
    private final Random random = new Random();
    private final List<BaseObject> missileList = new ArrayList<>();
    private static final long FPS = 60;

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

    private DrawThread drawThread;

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
        getHolder().addCallback(this);
    }

    private void drawGame(Canvas canvas) {

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.drawColor(Color.WHITE);
        if (droid == null) {
            Bitmap DroidBitmap =
                    BitmapFactory.decodeResource(getResources(),R.drawable.hitujiinu);
            droid = new Droid(DroidBitmap, width, height);
        }

        drawObjectList(canvas, missileList, width, height);
        
        if (random.nextInt(MISSILE_LAUNCH_WEIGHT) == 0) {

                launchMissile();
        }
        droid.draw(canvas);

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
