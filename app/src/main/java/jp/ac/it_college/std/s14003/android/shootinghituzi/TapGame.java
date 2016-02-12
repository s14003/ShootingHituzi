package jp.ac.it_college.std.s14003.android.shootinghituzi;

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

    private final String TAG = "TapGame";
    private Bitmap inu;
    private int exp = 5;
    private ImageView iv;
    private ProgressBar progressBar;
    private Button button;
    int Exp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        inu = BitmapFactory.decodeResource(getResources(),R.drawable.hitujikids);
        iv = (ImageView)findViewById(R.id.Hituzi_Button);
        iv.setImageBitmap(inu);
        iv.setOnClickListener(this);

        button = (Button)findViewById(R.id.choice_Button);
        button.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.exp_Bar);

        progressBar.setMax(5000);

        SharedPreferences prefe = getSharedPreferences("ExpData", MODE_PRIVATE);
        Exp = prefe.getInt("ExpAdd", Exp);

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


        }
    }

}
