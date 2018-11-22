package minhc.freakingmathremake;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {
    private ConstraintLayout layout;
    private TextView txtLabel;
    private TextView txtResult;
    private Button btnReplay;
    private Button btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_game_over);
        addControls();
        addEvents();

    }

    private void addControls() {
        layout = (ConstraintLayout) findViewById(R.id.GameOverLayout);
        layout.setBackgroundColor(Color.parseColor("#2D3E50"));

        txtLabel = (TextView) findViewById(R.id.txtLabelGameOver);
        txtResult = (TextView) findViewById(R.id.txtResult);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/Amaranth-Bold.ttf");
        txtLabel.setTypeface(typeface);
        txtLabel.setTextColor(Color.WHITE);
        typeface = Typeface.createFromAsset(getAssets(),"font/Amaranth-Italic.ttf");
        txtResult.setTypeface(typeface);
        txtResult.setTextColor(Color.WHITE);
        txtResult.setText("Your score: "+getIntent().getExtras().getInt("SCORE"));

        btnHome = (Button) findViewById(R.id.btnHome);
        btnReplay = (Button) findViewById(R.id.btnReplay);
    }
    private void addEvents(){
        btnReplay.setOnClickListener(this);
        btnHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnHome:{
                finish();
                break;
            }
            case R.id.btnReplay:{
                Intent i = new Intent(this,PlayActivity.class);
                finish();
                startActivity(i);
                break;
            }
        }
    }
}
