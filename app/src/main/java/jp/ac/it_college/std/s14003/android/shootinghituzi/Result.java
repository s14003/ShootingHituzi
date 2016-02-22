package jp.ac.it_college.std.s14003.android.shootinghituzi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {
//GameViewのスコアをSharedPreferencesに保存して取得。
    private Button button;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SharedPreferences preferences = getSharedPreferences("NewData", MODE_PRIVATE);
        Long score = preferences.getLong("LifeData", 0);
        Log.d("Result", score + "");

        TextView scoreView = (TextView)findViewById(R.id.score);
        scoreView.setText("" + score);

        SharedPreferences pref = getSharedPreferences("NewData", MODE_PRIVATE);
        Long enemy_life = pref.getLong("EnemyData", 0);
        Log.d("Result", enemy_life + "");

        TextView enemylife = (TextView)findViewById(R.id.enemy_life);
        enemylife.setText(enemy_life + "");
        Log.d("EnemyLife", enemy_life + "");

        TextView result = (TextView)findViewById(R.id.kekka);
        if (enemy_life > score) {
            result.setText("Your loser");
        } else {
            result.setText("Your winner");
        }

        button = (Button)findViewById(R.id.back_button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (button == v) {
            Intent it = new Intent(this, TapGame.class);
            startActivity(it);
        }
    }
}
