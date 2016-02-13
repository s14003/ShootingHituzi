package jp.ac.it_college.std.s14003.android.shootinghituzi;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Choice extends AppCompatActivity implements View.OnClickListener {
    private GameView gameView;
    private Button maxButton;
    private Button normalButton;
    private Button easyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        maxButton = (Button)findViewById(R.id.LevelMax_button);
        maxButton.setOnClickListener(this);

        normalButton = (Button)findViewById(R.id.LevelNormal_button);
        normalButton.setOnClickListener(this);

        easyButton = (Button)findViewById(R.id.LevelEasy_button);
        easyButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (maxButton == v) {
            gameView = new GameView(this);
            setContentView(gameView);
            SharedPreferences preferences = getSharedPreferences("LevelData", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("MaxData", 100).apply();

        } else if (normalButton == v) {
            gameView = new GameView(this);
            setContentView(gameView);
            SharedPreferences preferences = getSharedPreferences("LevelData", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("NormalData", 50).apply();

        } else if (easyButton == v) {
            gameView = new GameView(this);
            setContentView(gameView);
            SharedPreferences preferences = getSharedPreferences("LevelData", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("MaxData", 25).apply();
        }
    }
}
