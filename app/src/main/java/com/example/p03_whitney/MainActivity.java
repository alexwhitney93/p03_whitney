package com.example.p03_whitney;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Point;
import android.view.Display;

public class MainActivity extends AppCompatActivity
{

    SnakeView snakeView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();       // get dimensions of screen
        Point size = new Point();
        display.getSize(size);                                          // load resolution into a Point object
        snakeView = new SnakeView(this, size);                          // create a view based on snakeView class
        setContentView(snakeView);                                      // set the snakeView as the current view
    }

    // This method executes when the player starts the game
    @Override
    protected void onResume()
    {
        super.onResume();
        snakeView.resume();                                             // tell the gameView to resume method to execute
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause()
    {
        super.onPause();
        snakeView.pause();                                              // tell the gameView to resume method to execute
    }
}
