package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GameView.Callback {

    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        gameView.setCallback(this);
        setContentView(gameView);
    }

    @Override
    public void onGameOver(long score) {
        gameView.stopDrawThread();
        Toast.makeText(this, "Game Over スコア" + score, Toast.LENGTH_LONG).show();

    }
}
