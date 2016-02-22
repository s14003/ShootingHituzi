package jp.ac.it_college.std.s14003.android.shootinghituzi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Choice extends AppCompatActivity implements View.OnClickListener,GameView.Callback {
    private GameView gameView;
    private Button maxButton;
    private Button normalButton;
    private Button easyButton;
    private int Max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        SharedPreferences preferences = getSharedPreferences("MaxData", MODE_PRIVATE);
        Max = preferences.getInt("MaxChangeKids", Max);

        if (Max == 1000) {
            maxButton = (Button)findViewById(R.id.LevelMax_button);
            maxButton.setOnClickListener(this);
            normalButton = (Button)findViewById(R.id.LevelNormal_button);
            normalButton.setOnClickListener(this);
            easyButton = (Button) findViewById(R.id.LevelEasy_button);
            easyButton.setOnClickListener(this);

        } else if (Max == 500 ) {
            normalButton = (Button)findViewById(R.id.LevelNormal_button);
            normalButton.setOnClickListener(this);
            easyButton = (Button) findViewById(R.id.LevelEasy_button);
            easyButton.setOnClickListener(this);
        } else if (Max == 50){
            easyButton = (Button) findViewById(R.id.LevelEasy_button);
            easyButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        //Max呼び出して判定
        SharedPreferences preferences = getSharedPreferences("MaxData", MODE_PRIVATE);
        Max = preferences.getInt("MaxChangeKids", Max);

        if (maxButton == v) {
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
        edit.putLong("EnemyData", life).apply();

        Intent it = new Intent(this, Result.class);
        startActivity(it);
    }
}
