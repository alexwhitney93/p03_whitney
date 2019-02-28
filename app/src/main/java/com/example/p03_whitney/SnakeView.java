package com.example.p03_whitney;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.Random;

public class SnakeView extends SurfaceView implements Runnable
{
    private Thread gameThread = null;
    private SurfaceHolder ourHolder;
    private volatile boolean playing;
    private boolean paused = true;
    private Canvas canvas;
    private Paint paint;
    private Context context;
    //Sound
    private SoundPool soundPool;
    private int mouseSound = -1;
    private int deathSound = -1;
    private int[] snakeX, snakeY;
    private int snakeLength, score;
    private int mouseX, mouseY;
    private int height, width;

    private long nextFrameTime;
    private final long FPS = 10;            // frames per second
    private final long MSPS = 1000;         // milliseconds per second
    private int blockSize;
    private final int NUM_BLOCKS_WIDE = 40;
    private int numBlocksHigh;              // determined dynamically

    public SnakeView(Context c, Point p)
    {
        super(c);
        ourHolder = getHolder();
        paint = new Paint();
        context = c;
        snakeX = new int[200];
        snakeY = new int[200];
        height = p.y;
        width = p.x;
        blockSize = width/NUM_BLOCKS_WIDE;
        numBlocksHigh = height/blockSize;
        //loadSound();
        startGame();
    }

    @Override
    public void run()
    {
        while(playing)
        {
            if(checkForUpdate())
            {
                update();
                draw();
            }
        }
    }

    public void update()
    {

    }

    public void draw()
    {

    }

    public void pause()
    {
        playing = false;
        try
        {
            gameThread.join();
        }
        catch (InterruptedException e)
        {
            Log.e("Error: ", "joining thread" );
        }
    }

    public void resume()
    {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void startGame()
    {
        snakeLength = 1;                        // start with just a snake made of a single block
        snakeX[0] = NUM_BLOCKS_WIDE / 2;        // start with the snake in the middle of the screen
        snakeY[0] = numBlocksHigh / 2;
        score = 0;
        spawnMouse();                           // spawn a mouse in
        nextFrameTime = System.currentTimeMillis();     // nextFrameTime = now, so update is constantly triggered
    }

    public void loadSound()
    {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try
        {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;
            descriptor = assetManager.openFd("mouse_sound.ogg");
            mouseSound = soundPool.load(descriptor, 0);
            descriptor = assetManager.openFd("death_sound.ogg");
            deathSound = soundPool.load(descriptor, 0);
        }
        catch(IOException e)
        {
            Log.e("Error: ", "opening audio file" );
        }
    }

    public void spawnMouse()
    {
        Random random = new Random();
        mouseX = random.nextInt(NUM_BLOCKS_WIDE - 1) + 1;
        mouseY = random.nextInt(numBlocksHigh - 1) + 1;
    }

    private void eatMouse()
    {
        snakeLength++;
        spawnMouse();
        score++;
        soundPool.play(mouseSound, 1, 1, 0, 0, 1);
    }

    private void moveSnake()
    {
        
    }
}
