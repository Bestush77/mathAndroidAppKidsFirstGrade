package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Chapter2 extends AppCompatActivity {
    private SoundPool sound;
    private int soundCorrect, soundError, soundQuestion1, soundQuestion2, soundQuestion3, soundQuestion4;
    private boolean correctAnswer= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            sound = new SoundPool.Builder()
                    .setMaxStreams(9)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            sound = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        soundCorrect = sound.load(this, R.raw.sound_correct, 1);
        soundError = sound.load(this, R.raw.sound_error, 1);

        soundQuestion1 = sound.load(this, R.raw.q1, 1);
        soundQuestion2 = sound.load(this, R.raw.q2, 1);
        soundQuestion3 = sound.load(this, R.raw.q3, 1);
        soundQuestion4 = sound.load(this, R.raw.q4, 1);




    }
    public void playSound(View v) {



        if((v.getId() == R.id.correct_answer_1 ||v.getId() == R.id.correct_answer_2 ||
                v.getId() == R.id.correct_answer_3
                ||v.getId() == R.id.correct_answer_4)
                && correctAnswer == false) {
            sound.play(soundCorrect, 1, 1, 0, 0, 1);
            v.setBackgroundColor(Color.GREEN);

        }


        else {
            sound.play(soundError, 1, 1, 0, 0, 1);
            v.setBackgroundColor(Color.RED);
        }
    }

    public void playQuestion(View v) {
        switch(v.getId()){
            case R.id.question1:sound.play(soundQuestion1, 1, 1, 0, 0, 1);
                break;
            case R.id.question2:sound.play(soundQuestion2, 1, 1, 0, 0, 1);
                break;
            case R.id.question3:sound.play(soundQuestion3, 1, 1, 0, 0, 1);
                break;
            case R.id.question4:sound.play(soundQuestion4, 1, 1, 0, 0, 1);
                break;}
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        sound.release();
        sound= null;
    }

    public void goHome(View v){

        Intent goHome = new Intent(Chapter2.this, MainActivity.class);
        startActivity(goHome);
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

}