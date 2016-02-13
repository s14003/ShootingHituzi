package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private TapGame tapGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);


    }
/*
    @Override
    public void onGameOver(long score) {
        //SharedPreferencesにスコアを保存させる
        gameView.stopDrawThread();
        //Toast.makeText(this, "Game Over スコア" + score, Toast.LENGTH_LONG).show();

        SharedPreferences data = getSharedPreferences("NewData", MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putLong("ScoreData", score).apply();
        Intent it = new Intent(this, Result.class);
        startActivity(it);
    }
    */

    @Override
    public void onClick(View v) {
        if (button == v) {
            Intent it = new Intent(this, TapGame.class);
            startActivity(it);
        }
    }
}
