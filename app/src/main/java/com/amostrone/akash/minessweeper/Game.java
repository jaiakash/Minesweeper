package com.amostrone.akash.minessweeper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

public class Game extends View {

    Paint backgroundPaint, mTextPaint;

    Paint blockPaint = new Paint();
    Paint minePaint = new Paint();
    Paint safePaint = new Paint();

    public Game(Context context) {
        super(context);

        blockPaint.setStrokeWidth(10);
        blockPaint.setStyle(Paint.Style.FILL);
        minePaint.setStrokeWidth(10);
        minePaint.setColor(Color.RED);
        minePaint.setStyle(Paint.Style.FILL);
        safePaint.setStrokeWidth(10);
        safePaint.setColor(Color.GREEN);
        safePaint.setStyle(Paint.Style.FILL);


        mTextPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(pxFromDp(context, 24));

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.YELLOW);


        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);


        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPaint(backgroundPaint);

        int padding=250;
        for(int i=1;i<=8;i++) {
            for (int j = 0; j < 8; j++) {
                canvas.drawRoundRect(j * 125 + 40, i*150+padding, j * 125 + 125, i*150+100+padding, 10, 10, blockPaint);
            }
        }


        canvas.drawText("Minesweeper", 10, 50, mTextPaint);

    }


    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

}

