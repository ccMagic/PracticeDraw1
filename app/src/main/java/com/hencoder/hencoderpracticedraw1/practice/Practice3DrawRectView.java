package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice3DrawRectView extends View {

    public Practice3DrawRectView(Context context) {
        super(context);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint = new Paint();
    private RectF rectF = new RectF(300, 100, 400, 200);
    private Rect rect = new Rect(100, 300, 200, 400);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        练习内容：使用 canvas.drawRect() 方法画矩形
        canvas.drawRect(100, 100, 200, 200, paint);
        canvas.drawRect(rectF, paint);
        canvas.drawRect(rect, paint);
    }
}
