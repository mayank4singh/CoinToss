package com.example.coinflip;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    MediaPlayer m1;
    Random myradom = new Random();
    int NewDirection,lastDirection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        m1 = MediaPlayer.create(this,R.raw.coin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m1.start();
                int myran = myradom.nextInt(2)+1;
                switch (myran){
                    case 1:
                        imageView.setImageResource(R.drawable.heads);
                        Toast.makeText(MainActivity.this,"This is heads",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.tails);
                        Toast.makeText(MainActivity.this,"This is Tails",Toast.LENGTH_SHORT).show();
                        break;
                }

            }

        });

    }
}