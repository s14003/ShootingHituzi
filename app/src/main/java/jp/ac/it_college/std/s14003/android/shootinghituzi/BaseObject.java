package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Canvas;


public abstract class BaseObject {
    static final int STATUS_NORMAL = -1;
    static final int STATUS_DESTROYED = 0;
    int status = STATUS_NORMAL;
    static final float MOVE_WEIGHT = 3.0f;
    float yPosition;
    float xPosition;

    enum Type {
        Droid,
        Bullet,
        Missile,
        Boss,
    }

    public abstract Type getType();

    public abstract boolean isHit(BaseObject object);

    static double calcDistance(BaseObject object1, BaseObject object2) {
        float distX = object1.xPosition - object2.xPosition;
        float distY = object1.yPosition - object2.yPosition;
        return Math.sqrt(Math.pow(distX, 2) +  Math.pow(distY, 2));
    }

    public void hit() {
        status = STATUS_DESTROYED;

    }

    public abstract void draw(Canvas canvas);

    public boolean isAvailable(int width, int height) {
        if (yPosition < 0 || xPosition < 0 || yPosition > height || xPosition > width) {
            return false;
        }
        return true;
    }
    public abstract void move();
}
