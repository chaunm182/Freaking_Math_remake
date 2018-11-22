package minhc.model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Level {
    private int a;
    private int b;
    private boolean correctAnswer;

    private final int LIMIT_EASY_SCORE = 10;
    private final int LIMIT_MEDIUM_SCORE = 30;

    private final int LIMIT_EASY_NUMBER = 20;
    private final int LIMIT_MEDIUM_NUMBER = 40;
    private final int LIMIT_HARD_NUMBER = 60;


    private String strQuestion;
    private String strAnswer;

    public Level() {
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public String getStrQuestion() {
        return strQuestion;
    }

    public String getStrAnswer() {
        return strAnswer;
    }

    public void nextLevel(int score) {
        Random rd = new Random();
        if(score<=LIMIT_EASY_SCORE){
            a = rd.nextInt(LIMIT_EASY_NUMBER)+1;
            b = rd.nextInt(LIMIT_EASY_NUMBER)+1;
            correctAnswer = rd.nextBoolean();
            createQuestion(score);
        }
        else if(score>LIMIT_EASY_SCORE || score<=LIMIT_MEDIUM_SCORE){
            a = rd.nextInt(LIMIT_MEDIUM_NUMBER)+1;
            b = rd.nextInt(LIMIT_MEDIUM_NUMBER)+1;
            correctAnswer = rd.nextBoolean();
            createQuestion(score);
        }
        else if(score>LIMIT_MEDIUM_SCORE){
            a = rd.nextInt(LIMIT_HARD_NUMBER)+1;
            b = rd.nextInt(LIMIT_HARD_NUMBER)+1;
            correctAnswer = rd.nextBoolean();
            createQuestion(score);
        }

    }

    private void createQuestion(int score) {
        strQuestion = a+" + "+b;
        if(correctAnswer){
            int result = a+b;
            strAnswer = "= "+result;
        }
        else{
            int result = 0;
            do {
                Random rd = new Random();
                if(score<=LIMIT_EASY_SCORE){
                    result = rd.nextInt(LIMIT_EASY_NUMBER*2)+1;
                }
                else if (score>LIMIT_EASY_SCORE || score<=LIMIT_MEDIUM_SCORE){
                    result = rd.nextInt(LIMIT_MEDIUM_NUMBER*2)+1;
                }
                else if(score>LIMIT_MEDIUM_SCORE){
                    result = rd.nextInt(LIMIT_HARD_NUMBER*2)+1;
                }
            }while (result== a+b);
            strAnswer = "= "+result;
        }
    }
}
