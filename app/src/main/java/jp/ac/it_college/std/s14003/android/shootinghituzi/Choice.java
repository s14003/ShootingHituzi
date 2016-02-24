package jp.ac.it_college.std.s14003.android.shootinghituzi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Choice extends AppCompatActivity implements View.OnClickListener,GameView.Callback {
    private GameView gameView;
    private ImageView maxView;
    private ImageView normalButton;
    private ImageView easyButton;
    private int Max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        SharedPreferences preferences = getSharedPreferences("MaxData", MODE_PRIVATE);
        Max = preferences.getInt("MaxChangeKids", Max);

        if (Max == 1000) {
            maxView = (ImageView)findViewById(R.id.Max_button);
            maxView.setOnClickListener(this);
            normalButton = (ImageView)findViewById(R.id.Normal_button);
            normalButton.setOnClickListener(this);
            easyButton = (ImageView) findViewById(R.id.Easy_button);
            easyButton.setOnClickListener(this);

        } else if (Max == 500 ) {
            normalButton = (ImageView)findViewById(R.id.Normal_button);
            normalButton.setOnClickListener(this);
            easyButton = (ImageView) findViewById(R.id.Easy_button);
            easyButton.setOnClickListener(this);
        } else if (Max == 50){
            easyButton = (ImageView) findViewById(R.id.Easy_button);
            easyButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        //Max呼び出して判定
        SharedPreferences preferences = getSharedPreferences("MaxData", MODE_PRIVATE);
        Max = preferences.getInt("MaxChangeKids", Max);

        if (maxView == v) {
            gameView = new GameView(this,100);
            setContentView(gameView);
            gameView.setCallback(this);


        } else if (normalButton == v) {
            gameView = new GameView(this,200);
            setContentView(gameView);
            gameView.setCallback(this);


        } else if (easyButton == v) {
            gameView = new GameView(this,300);
            setContentView(gameView);
            gameView.setCallback(this);

        }
    }

    @Override
    public void onGameOver(int life, int enemy_life) {
        //SharedPreferencesにスコアを保存させる
        gameView.stopDrawThread();
        //Toast.makeText(this, "Game Over スコア" + score, Toast.LENGTH_LONG).show();

        SharedPreferences data = getSharedPreferences("NewData", MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putLong("LifeData", life).apply();

        SharedPreferences pref = getSharedPreferences("NewData", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putLong("EnemyData", enemy_life).apply();

        Intent it = new Intent(this, Result.class);
        startActivity(it);
    }
}
