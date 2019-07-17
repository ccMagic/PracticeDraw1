package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 坐标轴线条宽度
     */
    private static final int lineWidth = 2;
    /**
     * 字体大小
     */
    private static final int textSize = 18;
    /**
     * 直方图矩形的宽度
     */
    private static final int arctWidth = 60;
    /**
     * 直方图矩形之间的间距
     */
    private static final int arctSpacing = 20;
    private Paint paint = new Paint();
    private Paint textPaint = new Paint(ANTI_ALIAS_FLAG);
    /**
     * 坐标轴
     */
    private float[] originPoint = new float[]{100, 400};
    private Path linePath = new Path();
    private Path rectPath = new Path();

    {
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(textSize);
    }

    {
        linePath.moveTo(originPoint[0], 20);
        linePath.lineTo(originPoint[0], originPoint[1]);
        linePath.lineTo(originPoint[0] + 600, originPoint[1]);
    }


    private List<VersionData> list = new ArrayList<>();

    {
        //添加版本数据
        list.add(new VersionData("Froyo", 2));
        list.add(new VersionData("GB", 10));
        list.add(new VersionData("ICS", 10));
        list.add(new VersionData("JB", 100));
        list.add(new VersionData("kitKat", 170));
        list.add(new VersionData("L", 200));
        list.add(new VersionData("M", 90));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        //画坐标轴
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);
        canvas.drawPath(linePath, paint);
        //
        for (int i = 0; i < list.size(); i++) {
            VersionData versionData = list.get(i);
            //矩形
            float left = originPoint[0] + arctSpacing * (i + 1) + arctWidth * i;
            float bottom = originPoint[1] - lineWidth;
            float top = bottom - versionData.getNum();
            float right = left + arctWidth;

            rectPath.moveTo(left, top);
            rectPath.addRect(left, top, right, bottom, Path.Direction.CW);
            //文本宽度
            float textWid = textPaint.measureText(versionData.getName());

            canvas.drawText(versionData.getName(), left + (arctWidth - textWid) / 2, bottom + textSize, textPaint);
        }
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(rectPath, paint);
    }

    /**
     * 每个版本市场占比
     */
    private static class VersionData {

        VersionData(String name, float num) {
            this.name = name;
            this.num = num;
        }

        /**
         * 名称
         */
        private String name;
        /**
         * 比例
         */
        private float num;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getNum() {
            return num;
        }

        public void setNum(float num) {
            this.num = num;
        }

    }
}
