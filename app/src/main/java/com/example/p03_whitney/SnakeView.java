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
    //private int SoundPool soundPool;
    //private int mouseSound = -1;
    //private int deathSound = -1;
    private int[] snakeX, snakeY;
    private int snakeLength, score;
    private int mouseX, mouseY;
    private int height, width;

    private long nextFrameTime;
    private final long FPS = 10;            // frames per second
    private final long MSPS = 1000;         // milliseconds per second
    private int blockSize;
    private final int NUM_BLOCKS_WIDE = 40l;
    private int numBlocksHigh;              // determined dynamically

    

}
