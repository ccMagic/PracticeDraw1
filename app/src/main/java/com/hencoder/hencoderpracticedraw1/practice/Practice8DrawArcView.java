package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint = new Paint();
    private RectF rectF = new RectF(150, 150, 350, 300);
    private RectF rectF2 = new RectF(150, 180, 350, 300);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF, -180, 50, false, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF, -120, 110, true, paint);
        canvas.drawArc(rectF2, 30, 120, false, paint);


    }
}
