package com.example.coinflip;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    MediaPlayer m1;
    Random myradom = new Random();
    private int curSide = R.drawable.heads;
    private ImageView coinImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coinImage = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        m1 = MediaPlayer.create(this, R.raw.coin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m1.start();
                int myran = myradom.nextInt(2) + 1;
                switch (myran) {
                    case 1: boolean stayTheSame = (curSide == R.drawable.heads);
                        long timeOfAnimation = animateCoin(stayTheSame);
                        curSide = R.drawable.tails;
                        Toast.makeText(MainActivity.this, "This is Heads", Toast.LENGTH_SHORT).show();
                        break;

                    case 2: stayTheSame = (curSide == R.drawable.tails);
                        timeOfAnimation = animateCoin(stayTheSame);
                        curSide = R.drawable.heads;
                        Toast.makeText(MainActivity.this, "This is Tails", Toast.LENGTH_SHORT).show();
                        break;
                }


            }

        });

    }
        private long animateCoin ( boolean stayTheSame){

            Rotation3D animation;

            if (curSide == R.drawable.heads)
                {
                animation = new Rotation3D(coinImage, R.drawable.heads, R.drawable.tails, 0, 180, 0, 0, 0, 0);
                }
            else
                {
                animation = new Rotation3D(coinImage, R.drawable.tails, R.drawable.heads, 0, 180, 0, 0, 0, 0);
                }
                if (stayTheSame)
                    {
                    animation.setRepeatCount(5); // must be odd (5+1 = 6 flips so the side will stay the same)
                    }
                else
                    {
                    animation.setRepeatCount(6); // must be even (6+1 = 7 flips so the side will not stay the same)
                    }
            animation.setDuration(130);
            animation.setInterpolator(new LinearInterpolator());
            coinImage.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                    button.setEnabled(false);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    button.setEnabled(true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            return animation.getDuration();
        }


}
