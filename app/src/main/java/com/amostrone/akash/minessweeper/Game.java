package com.amostrone.akash.minessweeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Game extends View {

    Paint backgroundPaint, mTextPaint;

    Paint blockPaint = new Paint();
    Paint minePaint = new Paint();
    Paint safePaint = new Paint();

    RectF blockArray[][] = new RectF[8][8];//Assume these have been drawn in your draw method.

    public Game(Context context) {
        super(context);

        blockPaint.setStrokeWidth(10);
        blockPaint.setColor(Color.WHITE);
        blockPaint.setStyle(Paint.Style.FILL);
        minePaint.setStrokeWidth(10);
        minePaint.setColor(Color.RED);
        minePaint.setStyle(Paint.Style.FILL);
        safePaint.setStrokeWidth(10);
        safePaint.setColor(Color.GREEN);
        safePaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(pxFromDp(context, 24));

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.BLACK);


        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);


        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int padding=250;
        for(int i=1;i<=8;i++) {
            for (int j = 0; j < 8; j++) {
                blockArray[i-1][j] = new RectF(j * 125 + 40, i*150+padding, j * 125 + 125, i*150+100+padding);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPaint(backgroundPaint);

        for(int i=1;i<=8;i++) {
            for (int j = 0; j < 8; j++) {
                canvas.drawRect(blockArray[i-1][j],blockPaint);
            }
        }


        canvas.drawText("Score : 0", 30, 100, mTextPaint);
        canvas.drawText("High Score : 1", 600, 100, mTextPaint);

    }


    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

                for(int i=1;i<=8;i++) {
                    for (int j = 0; j < 8; j++) {
                        if(blockArray[i-1][j].contains(touchX,touchY)) {
                            Log.i("Clicked", "Clicked Block i="+(i-1)+" j="+j);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Sliding your finger around on the screen.");
                break;
        }
        return true;
    }

}

