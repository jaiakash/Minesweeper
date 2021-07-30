package com.amostrone.akash.minessweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Game extends View {

    Paint backgroundPaint, mTextPaint;

    Paint blockPaint = new Paint();
    Paint minePaint = new Paint();
    Paint safePaint = new Paint();
    int score=0;

    RectF[][] blockArray = new RectF[8][8];//Assume these have been drawn in your draw method.
    boolean[][] isOpened = new boolean[8][8];
    boolean[][] isMine = {
            {true,false,false,false,false,false,false,false},//1
            {false,true,false,false,false,false,false,false},//2
            {false,false,true,false,false,false,false,false},//3
            {false,false,false,true,false,false,false,false},//4
            {false,false,false,false,true,false,false,false},//5
            {false,false,false,false,false,true,false,false},//6
            {false,false,false,false,false,false,true,false},//7
            {false,false,false,false,false,false,false,true} //8
            };

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

        restart();
    }

    public void restart(){
        int padding=250;
        for(int i=1;i<=8;i++) {
            for (int j = 0; j < 8; j++) {
                isOpened[i-1][j]=false;
                blockArray[i-1][j] = new RectF(j * 125 + 40, i*150+padding, j * 125 + 125, i*150+100+padding);
            }
        }

        //Setting High Score

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPaint(backgroundPaint);

        for(int i=1;i<=8;i++) {
            for (int j = 0; j < 8; j++){
                if(!isOpened[i-1][j]){
                    canvas.drawRoundRect(blockArray[i-1][j],10,10,blockPaint);
                }
                else{
                    if(isMine[i-1][j]){
                        canvas.drawRoundRect(blockArray[i-1][j],10,10,minePaint);
                    }
                    else {
                        canvas.drawRoundRect(blockArray[i-1][j],10,10,safePaint);
                    }
                }
            }
        }

        canvas.drawText("Score : "+score, 30, 100, mTextPaint);
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
                            if(isMine[i-1][j]){
                                Toast.makeText(getContext(), "Game Over, Your score is "+score, Toast.LENGTH_SHORT).show();
                                isOpened[i-1][j]=true;

                                // Vibration
                                Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                v.vibrate(400);

                                // Setting score and high score
                                score=0;
                            }
                            else {
                                // Increasing Score and marked block as open
                                score++;
                                isOpened[i-1][j]=true;
                            }
                            invalidate();
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

