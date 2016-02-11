package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class TapGame extends AppCompatActivity {
    private TapCharacter tapCharacter;
    private Canvas canvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        if (tapCharacter == null) {
            Bitmap inu = BitmapFactory.decodeResource(getResources(),R.drawable.hitujiinu);
            tapCharacter = new TapCharacter(inu, width, height);
        }
    }

}
