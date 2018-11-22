package minhc.freakingmathremake;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import minhc.model.Level;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtFomular;
    private TextView txtResult;
    private TextView txtScore;
    private ImageButton btnCorrect;
    private ImageButton btnInCorrect;
    private ConstraintLayout layout;

    private int score = 0;
    private Level level;

    private Timer timer;
    private TimerTask timerTask;
    private final int TIME_LIMIT = 2000;

    private ProgressBar prbCount;

    private CountDownTimer count;

    private MediaPlayer mp = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_play);
        addControls();
        addEvents();
        createGame();
        createTimeTask();

    }



    public void addEvents() {
        btnCorrect.setOnClickListener(this);
        btnInCorrect.setOnClickListener(this);
    }
    public void createGame(){
        level.nextLevel(score);
        setLayout();
        setLabel();
        createCountDownTimer();

    }

    public void addControls(){
        level = new Level();

        txtFomular = (TextView) findViewById(R.id.txtFomular);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/Amaranth-Bold.ttf");
        txtFomular.setTypeface(typeface);
        txtFomular.setTextColor(Color.WHITE);

        txtResult = (TextView) findViewById(R.id.txtResult);
        txtResult.setTypeface(typeface);
        txtResult.setTextColor(Color.WHITE);


        txtScore = (TextView) findViewById(R.id.txtScore);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(),"font/Allan-Bold.ttf");
        txtScore.setTypeface(typeface1);
        txtScore.setTextColor(Color.WHITE);

        btnCorrect = (ImageButton) findViewById(R.id.btnCorrect);
        btnInCorrect = (ImageButton) findViewById(R.id.btnInCorrect);
        btnCorrect.setSoundEffectsEnabled(false);
        btnInCorrect.setSoundEffectsEnabled(false);

        prbCount = (ProgressBar) findViewById(R.id.prbCount);
        prbCount.setMax(1000);

        layout = (ConstraintLayout) findViewById(R.id.mainLayout);
    }
    public void setLayout(){
        ArrayList<String> arrColor = new ArrayList<String>();
        String[] dsColor = new String[]{"#FF00FF","#0000CC","#EE0000","#009900","#FF3366","#FF66FF","#000044","#0099CC","#FF3333"};
        arrColor.addAll(Arrays.asList(dsColor));
        Random rd = new Random();
        int index = rd.nextInt(arrColor.size());
        String color = arrColor.get(index);
        layout.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCorrect:{
                if(level.isCorrectAnswer()){
                    mp = MediaPlayer.create(this,R.raw.correct_answer);
                    mp.start();
                    cancelTime();
                    next();
                }
                else{
                    finish();
                    showGameOver();
                }
                break;
            }
            case R.id.btnInCorrect:{
                if(!level.isCorrectAnswer()){
                    mp = MediaPlayer.create(this,R.raw.correct_answer);
                    mp.start();
                    cancelTime();
                    next();
                }
                else{
                    finish();
                    showGameOver();
                }
                break;
            }
        }
    }

    public void showGameOver() {
        mp = MediaPlayer.create(this,R.raw.incorrect_answer);
        mp.start();
        count.cancel();
        cancelTime();
        Intent i = new Intent(this,GameOverActivity.class);
        i.putExtra("SCORE",score);
        startActivity(i);
    }
    public void next(){
        count.cancel();
        prbCount.setProgress(0);
        createCountDownTimer();
        this.score++;
        level.nextLevel(score);
        setLayout();
        setLabel();
        createTimeTask();
    }
    public void setLabel(){
        txtFomular.setText(level.getStrQuestion());
        txtResult.setText(level.getStrAnswer());
        txtScore.setText("Your Score: "+score);
    }
    public void createTimeTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                showGameOver();
            }
        };
        timer.schedule(timerTask,TIME_LIMIT);
    }
    public void cancelTime(){
        timerTask.cancel();
        timer.cancel();
    }
    public void createCountDownTimer(){
        count = new CountDownTimer(2000,10) {
            @Override
            public void onTick(long millisUntilFinished) {
                int current = prbCount.getProgress();
                prbCount.setProgress((current+10));
            }

            @Override
            public void onFinish() {
                Toast.makeText(PlayActivity.this,"Hết giờ",Toast.LENGTH_SHORT).show();

            }
        };
        count.start();
    }
}
