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

public class Game extends View {

    Paint mPaint, otherPaint, outerPaint, mTextPaint;
    RectF mRectF;
    int mPadding;

    public Game(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);

        mTextPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(pxFromDp(context, 24));

        otherPaint = new Paint();
        outerPaint = new Paint();
        outerPaint.setStyle(Paint.Style.FILL);
        outerPaint.setColor(Color.YELLOW);

        mPadding = 100;

        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);


        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        mRectF = new RectF(screenWidth / 4, screenHeight / 4, screenWidth / 6, screenHeight /6);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        otherPaint.setColor(Color.RED);
        otherPaint.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(mRectF, 10, 10, otherPaint);
        canvas.clipRect(mRectF, Region.Op.DIFFERENCE);
        canvas.drawPaint(outerPaint);

        canvas.drawRect(0,0,50,50,outerPaint);

        canvas.drawRect(mPadding, mPadding+150, getWidth() - mPadding, getHeight() - mPadding, mPaint);

        canvas.drawText("Minesweeper", (float) (getWidth() * 0.3), (float) (getHeight() * 0.8), mTextPaint);

    }


    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

}

