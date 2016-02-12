package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


public class TapGame extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);
        drawGame();
    }
    private void drawGame() {
        Bitmap inu = BitmapFactory.decodeResource(getResources(),R.drawable.hitujiinu);
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.setImageBitmap(inu);

    }

}
