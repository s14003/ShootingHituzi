package jp.ac.it_college.std.s14003.android.shootinghituzi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView startView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        startView = (TextView)findViewById(R.id.start_View);
        startView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (startView == v) {
            Intent it = new Intent(this, TapGame.class);
            startActivity(it);
        }
    }
}
