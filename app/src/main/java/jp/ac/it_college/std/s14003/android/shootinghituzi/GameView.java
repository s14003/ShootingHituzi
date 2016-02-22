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

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private Droid droid;
    private Boss boss;
    private final List<BaseObject> bulletList = new ArrayList<>();
    private final List<BaseObject> missileList = new ArrayList<>();
    private static final int MISSILE_LAUNCH_WEIGHT = 50;
    private final Random random = new Random();
    private static final long FPS = 60;
    private Handler handler;
    private static final float SCORE_TEXT_SIZE = 30.0f;
    private final Paint paint = new Paint();
    private long score;
    private Callback callback;
    private DrawThread drawThread;
    int Level;
    int life = 30;
    int enemy_life;


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

    public GameView(Context context, int hoge) {
        super(context);

        paint.setColor(Color.BLACK);
        paint.setTextSize(SCORE_TEXT_SIZE);
        paint.setAntiAlias(true);
        handler = new Handler();
        getHolder().addCallback(this);
        Level += hoge;
        enemy_life += Level;
        String TAG = "GameView";
        Log.d(TAG,"enemy_life = " + enemy_life);

    }

    private void drawGame(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.drawColor(Color.WHITE);

        String TAG = "GameView";
        if (droid == null) {
            //Maxから値とってきて画像判定する
            if (Level == 100) {
                Log.d(TAG,"Level" + Level);
                Bitmap DroidBitmap =
                        BitmapFactory.decodeResource(getResources(),R.drawable.gameinu);
                droid = new Droid(DroidBitmap, width, height);
                enemy_life += 2900;
                life += 20;
            } else if (Level == 200) {
                Log.d(TAG,"Level" + Level);
                Bitmap DroidBitmap =
                        BitmapFactory.decodeResource(getResources(),R.drawable.gameinumini);
                droid = new Droid(DroidBitmap, width, height);
                enemy_life += 1800;
                life += 10;
            } else if(Level == 300) {
                Log.d(TAG,"Level" + Level);
                Bitmap DroidBitmap =
                        BitmapFactory.decodeResource(getResources(),R.drawable.gameinubaby);
                droid = new Droid(DroidBitmap, width, height);
                enemy_life += 700;
            }
        }

        if (boss == null) {
            Log.d(TAG, "Boss == null");
            Bitmap BossBitmap =
                    BitmapFactory.decodeResource(getResources(),R.drawable.hituzineko);
            boss = new Boss(BossBitmap, width, height);

        }

        drawObjectList(canvas, missileList, width, height);
        drawObjectList(canvas, bulletList, width, height);

        for (int i = 0; i < missileList.size(); i++) {
            BaseObject missile = missileList.get(i);
            for (int j = 0; j < bulletList.size(); j++) {
                BaseObject bullet = bulletList.get(j);

                if (bullet.isHit(missile)) {
                    if (missile.isHit(bullet)) {
                        missile.hit();
                        bullet.hit();
                        score += 10;
                    }

                }
            }
        }
        //Boss
        for (int i = 0; i < bulletList.size(); i++) {
            BaseObject bullet = bulletList.get(i);
            if (bullet.isHit(boss)) {
                Log.d(TAG, "Bullet isHit boss is ok");
                    bullet.hit();
                    score += 10;
                    enemy_life -= 20;
                    Log.d(TAG,"Score += 100");
                    Log.d(TAG, "enemy_life = " + enemy_life);
                if (enemy_life == 0) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            boss.hit();
                            callback.onGameOver(life, enemy_life);
                        }
                    });
                    break;
                }
            }
        }
        //Player
        for (int i = 0; i < missileList.size(); i++) {
            BaseObject missile = missileList.get(i);
            if (missile.isHit(droid)) {
                Log.d(TAG,"missile isHit  droid is true");
                    Log.d(TAG, "droid isHit  missile is true");
                    missile.hit();
                    life -= 10;
                    Log.d(TAG,"life = " + life);
                    if (life == 0) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                droid.hit();
                                callback.onGameOver(life, enemy_life);
                            }
                        });
                        break;
                    }

            }
        }

        //難易度
        if (random.nextInt(MISSILE_LAUNCH_WEIGHT) == 0) {
            long count = score / Level + 1;
            for (int i = 0; i < count; i++) {
                launchMissile();
            }
        }
        boss.draw(canvas);
        droid.draw(canvas);
        canvas.drawText("Your life:" + life, 0, SCORE_TEXT_SIZE, paint);
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
    private static void drawObjectList (
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
        void onGameOver (int life, int enemy_life);
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
