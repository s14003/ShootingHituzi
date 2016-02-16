package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class TapGame extends AppCompatActivity implements View.OnClickListener {
    String TAG = "TapGame";
    int Exp;
    int Max = 50;
    private Bitmap inu;
    private ImageView iv;
    private ProgressBar progressBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        SharedPreferences prefe = getSharedPreferences("ExpData", MODE_PRIVATE);
        Exp = prefe.getInt("ExpAdd", Exp);

        if (Exp < Max) {
            Log.d(TAG,"Exp < Max");
            Log.d(TAG, Exp + "");
            inu = BitmapFactory.decodeResource(getResources(), R.drawable.tapkittychange);
            iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
            iv.setImageBitmap(inu);
            iv.setOnClickListener(this);
        }else {
            Log.d(TAG,"Exp > Max");
            inu = BitmapFactory.decodeResource(getResources(), R.drawable.tapbig);
            iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
            iv.setImageBitmap(inu);
            iv.setOnClickListener(this);
        }


        button = (Button) findViewById(R.id.choice_Button);
        button.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.exp_Bar);

        progressBar.setMax(Max);


        progressBar.setProgress(Exp);


    }


    @Override
    public void onClick(View v) {
        //noinspection EqualsBetweenInconvertibleTypes,StatementWithEmptyBody
        if (iv == v) {

            SharedPreferences pref = getSharedPreferences("ExpData", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("ExpAdd", Exp).apply();
            Log.d(TAG, "Exp Add" + Exp);

            Exp += 5;

            SharedPreferences preferences = getSharedPreferences("ExpData", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putInt("ExpAdd", Exp).apply();
            Log.d(TAG, "Total Exp :" + Exp);


            SharedPreferences prefe = getSharedPreferences("ExpData", MODE_PRIVATE);
            Exp = prefe.getInt("ExpAdd", 0);
            Log.d(TAG, Exp + "");

            progressBar.setProgress(Exp);

            if (Exp < Max) {
                inu = BitmapFactory.decodeResource(getResources(), R.drawable.tapkittychange);
                iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
                iv.setImageBitmap(inu);

                iv.setOnClickListener(this);
            }else {
                inu = BitmapFactory.decodeResource(getResources(), R.drawable.tapbig);
                iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
                iv.setImageBitmap(inu);
                iv.setOnClickListener(this);
            }

        } else if (button == v) {
            Intent it = new Intent(this, Choice.class);
            startActivity(it);

        }
    }

}
