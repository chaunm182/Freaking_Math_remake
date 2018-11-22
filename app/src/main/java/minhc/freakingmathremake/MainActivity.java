package minhc.freakingmathremake;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnStartGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    public void addEvents() {
        btnStartGame.setOnClickListener(this);
    }

    public void addControls() {
        btnStartGame = (ImageButton) findViewById(R.id.btnStartGame);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnStartGame:{
                Intent i = new Intent(this,PlayActivity.class);
                startActivity(i);
                break;
            }
        }
    }
}
