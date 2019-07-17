package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼图
 *
 * @author kxmc
 */
public class Practice11PieChartView extends View {

    private static final String TAG = "Practice11PieChartView";

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 是否最大的饼图显示在左上角第一个
     */
    private static final boolean SHOW_MAX_PART_POSITION_0 = false;

    /**
     * 饼图开始绘制起始角度
     */
    private static final float START_ANGLE = -180;
    /**
     * 最大扇形与其他扇形的间距
     */
    private static final float SPACING = 10;
    /**
     * 饼图半径
     */
    private static final float RADIUS = 150;
    private final float originLeft = 200;
    private final float originTop = 80;
    private final float originRight = originLeft + RADIUS * 2;
    private final float originBottom = originTop + RADIUS * 2;
    /**
     * 涂料
     */
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<Part> list = new ArrayList<>();

    {

        list.add(new Part("Lollipop", 50, Color.RED));
        list.add(new Part("Marshmallow", 100, Color.BLUE));
        list.add(new Part("Froyo", 60, Color.YELLOW));
        list.add(new Part("Gingerbread", 120, Color.GREEN));
        list.add(new Part("Ice Cream Sandwich", 10, Color.WHITE));
        list.add(new Part("Jelly Bean", 80, Color.MAGENTA));
        list.add(new Part("KitKat", 30, Color.LTGRAY));

    }

    double moveRadius = Math.sqrt(SPACING * SPACING * 2);
    private Part maxPart;

    {
        //
        float sum = 0;
        maxPart = list.get(0);
        int maxPosition = 0;
        for (int i = 0, size = list.size(); i < size; i++) {
            //找出最大值
            Part part = list.get(i);
            //标记比例最大的扇形
            if (part.num > maxPart.num) {
                maxPart = part;
                maxPosition = i;
            }
            //计算所有Part的总和，后面用于根据百分比划分整个圆
            sum = sum + part.num;
        }
        if (SHOW_MAX_PART_POSITION_0) {
            //将最大的扇形移动到第一个
            list.set(0, list.set(maxPosition, list.get(0)));
        }
        for (int i = 0, size = list.size(); i < size; i++) {
            Part part = list.get(i);
            if (i == 0) {
                //全图从-180度开始顺时针绘制
                part.startAngle = START_ANGLE;
            } else {
                //前一个扇形绘制结束的位置就是下一个扇形开始绘制的位置
                part.startAngle = list.get(i - 1).startAngle + list.get(i - 1).sweepAngle;
            }
            part.sweepAngle = part.num / sum * 360;

            //扇形中心角度
            part.midAngle = (part.sweepAngle / 2 + part.startAngle);
            //求角度对应的三角函数对应的x轴的值
            double anger = Math.toRadians(part.midAngle);
            //
            if (part == maxPart) {
                //求三角函数值，求突出显示的最大扇形在两个x、y需要移动的距离
                float hMove = (float) (moveRadius * Math.cos(anger));
                float vMove = (float) (moveRadius * Math.sin(anger));
                Log.d(TAG, "instance initializer: hMove: " + hMove);
                Log.d(TAG, "instance initializer: vMove: " + vMove);
                //由于最大的扇形显示在第一位的左上角，全图从-180度开始顺时针绘制
                part.rectf = new RectF(originLeft + hMove, originTop + vMove, originRight + hMove, originBottom + vMove);
            } else {
                part.rectf = new RectF(originLeft, originTop, originRight, originBottom);

            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        Log.d(TAG, "onDraw: ");


        float circleMidx = originLeft + RADIUS;
        float circleMidy = originTop + RADIUS;
        Log.d(TAG, "onDraw circleMidX: " + circleMidx);
        Log.d(TAG, "onDraw circleMidY: " + circleMidy);

        for (Part part : list) {
            paint.setStrokeWidth(2);
            paint.setColor(part.color);
            canvas.drawArc(part.rectf, part.startAngle, part.sweepAngle, true, paint);

            Log.d(TAG, "onDraw: ----------------------------------------------------");
            //圆心
            //起始点
            float startx;
            float starty;
            if (part == maxPart) {
                startx = circleMidx + (float) ((RADIUS + moveRadius) * Math.cos(Math.toRadians(part.midAngle)));
                starty = circleMidy + (float) ((RADIUS + moveRadius) * Math.sin(Math.toRadians(part.midAngle)));
            } else {
                startx = circleMidx + (float) (RADIUS * Math.cos(Math.toRadians(part.midAngle)));
                starty = circleMidy + (float) (RADIUS * Math.sin(Math.toRadians(part.midAngle)));
            }
            Log.d(TAG, "onDraw startX: " + startx);
            Log.d(TAG, "onDraw startY: " + starty);
            //结束点
            float endx = startx + (float) (40 * Math.cos(Math.toRadians(part.midAngle)));
            float endy = starty + (float) (40 * Math.sin(Math.toRadians(part.midAngle)));
            Log.d(TAG, "onDraw endx: " + endx);
            Log.d(TAG, "onDraw endy: " + endy);
            //开始标注点
            float startWordx = endx + (float) (50 * Math.cos(Math.toRadians(part.midAngle)));
            float startWordy = endy;
            //
            paint.setColor(Color.WHITE);
            canvas.drawLine(startx, starty, endx, endy, paint);
            canvas.drawLine(endx, endy, startWordx, startWordy, paint);
            float textOffset;
            if (Math.cos(Math.toRadians(part.midAngle)) < 0) {
                textOffset = paint.measureText(part.name) + 5;
            } else {
                textOffset = -5;
            }
            canvas.drawText(part.name, startWordx - textOffset, startWordy, paint);
        }
    }

    private static class Part {
        /**
         * 名称
         */
        private String name;
        /**
         * 数值
         */
        private int num;
        private RectF rectf;
        /**
         * 开始角度
         */
        private float startAngle;
        /**
         * 扇形中心角
         */
        private float midAngle;
        /**
         * 扇形形状角度
         */
        private float sweepAngle;
        /**
         * 扇形颜色
         */
        @ColorInt
        private int color;


        private Part(String name, int num, @ColorInt int color) {
            this.name = name;
            this.num = num;
            this.color = color;
        }
    }
}
