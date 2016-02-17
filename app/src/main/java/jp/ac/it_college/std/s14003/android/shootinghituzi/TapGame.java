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
    private Bitmap Scarecrow;
    private ImageView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        SharedPreferences prefe = getSharedPreferences("ExpData", MODE_PRIVATE);
        Exp = prefe.getInt("ExpAdd", Exp);

        button = (Button) findViewById(R.id.choice_Button);
        button.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.exp_Bar);

        SharedPreferences preferences = getSharedPreferences("MaxData", MODE_PRIVATE);
        Max = preferences.getInt("MaxChangeKids", Max);

        progressBar.setMax(Max);
        Log.d(TAG, "Maxの値" + Max);

        progressBar.setProgress(Exp);

        Scarecrow = BitmapFactory.decodeResource(getResources(), R.drawable.kakasi);
        sv = (ImageView) findViewById(R.id.kakasi);
        sv.setImageBitmap(Scarecrow);
        sv.setOnClickListener(this);

        if (Max == 50) {
            if (Exp < Max) {
                Log.d(TAG,"Exp < Max");
                Log.d(TAG, Max + "");
                inu = BitmapFactory.decodeResource(getResources(), R.drawable.tapkittychange);
                iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
                iv.setImageBitmap(inu);
                Max = 50;
                SharedPreferences preference = getSharedPreferences("MaxData",MODE_PRIVATE);
                SharedPreferences.Editor Max_editor = preference.edit();
                Max_editor.putInt("MaxChangeKids", Max).apply();
            }else {
                Log.d(TAG,"" + Max);
                Log.d(TAG, "Exp > Max");
                inu = BitmapFactory.decodeResource(getResources(), R.drawable.hitujikids);
                iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
                iv.setImageBitmap(inu);
            }
        } else if (Max == 500) {
            inu = BitmapFactory.decodeResource(getResources(), R.drawable.hitujikids);
            iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
            iv.setImageBitmap(inu);

        } else if (Max == 1000) {
            inu = BitmapFactory.decodeResource(getResources(),R.drawable.tapbig);
            iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
            iv.setImageBitmap(inu);
        }


    }


    @Override
    public void onClick(View v) {
        //noinspection EqualsBetweenInconvertibleTypes,StatementWithEmptyBody

        if (sv == v) {

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

            if (Max == 50) {

                if (Exp < Max) {
                    inu = BitmapFactory.decodeResource(getResources(), R.drawable.tapkittychange);
                    iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
                    iv.setImageBitmap(inu);

                }else {
                    inu = BitmapFactory.decodeResource(getResources(), R.drawable.hitujikids);
                    iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
                    iv.setImageBitmap(inu);

                    Max += 450;
                    progressBar.setProgress(0);
                    progressBar.setMax(Max);
                    SharedPreferences preference = getSharedPreferences("MaxData",MODE_PRIVATE);
                    SharedPreferences.Editor Max_editor = preference.edit();
                    Max_editor.putInt("MaxChangeKids", Max).apply();
                    Log.d(TAG,"Kids Max" + Max);

                }
            } else if (Max == 500) {
                if (Max > Exp) {

                    inu = BitmapFactory.decodeResource(getResources(), R.drawable.hitujikids);
                    iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
                    iv.setImageBitmap(inu);
                } else {

                    inu = BitmapFactory.decodeResource(getResources(), R.drawable.tapbig);
                    iv = (ImageView) findViewById(R.id.Hitsuzi_Button);
                    iv.setImageBitmap(inu);

                    Max += 500;
                    progressBar.setProgress(0);
                    progressBar.setMax(Max);
                    SharedPreferences preference = getSharedPreferences("MaxData",MODE_PRIVATE);
                    SharedPreferences.Editor Max_editor = preference.edit();
                    Max_editor.putInt("MaxChangeKids", Max).apply();
                    Log.d(TAG,"Change Max" + Max);
                }
            }
        } else if (button == v) {
            Intent it = new Intent(this, Choice.class);
            startActivity(it);
        }
    }

}
