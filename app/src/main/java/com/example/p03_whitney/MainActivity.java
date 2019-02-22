package com.example.p03_whitney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        asteroidView = new AsteroidView(this);
        setContentView(asteroidView);
    }

    class AsteroidView extends SurfaceView implements Runnable
    {
        Thread gamethread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing;
        boolean paused = true;
        Canvas canvas;
        Paint paint;
        int y;
        int posx, posy;
        int dx, dy;
        int height, width;
        boulder b[];

        private long thisTimeFrame;

        public AsteroidView(Context context)
        {
            super(context);
            ourHolder = getHolder();
            paint = new Paint();
        }

        @Override
        public void run()
        {
            Random r = new Random();
            b = new boulder[5];
            posx = 50;
            posy = 50;
            dx = 20;
            dy = 45;
            for(int i = 0; i < 5; ++i)
            {
                b[i] = new boulder();
                b[i].x = r.nextInt(50);
                b[i].y = r.nextInt(50);
                b[i].dx = r.nextInt(30) - 15;
                b[i].dy = r.nextInt(30) - 15;
                b[i].diameter = 95;
            }

            while(playing)
            {
                if(!paused)
                {
                    update();
                }
                draw();
                try
                {
                    Thread.sleep(50);
                }
                catch(InterruptedException e)
                {

                }
            }
        }

        public void update()
        {
            y = y+5;
            if(y > 200)
            {
                y = 5;
            }
            posx += dx;
            posy += dy;
            if((posx > width) || posx( < 0))
            {
                dx = -dx;
            }
            if((posy > height) || (posy < 0))
            {
                dy = -dy;
            }
            for(int i = 0; i < 5; ++i)
            {
                b[i].update();
            }
        }

        public void draw()
        {
            
        }
    }
}
