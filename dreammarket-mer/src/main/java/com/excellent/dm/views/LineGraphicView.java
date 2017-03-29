package com.excellent.dm.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.excellent.dm.R;

import java.util.ArrayList;


/**
 * Created by fenghui on 2017/3/17.
 * 描述：
 */
public class LineGraphicView extends View {
    /**
     * 公共部分
     */
    private static final int CIRCLE_SIZE = 10;
    private Context mContext;
    private Paint mPaint,mBottomTxtPaint;
    private Resources res;
    private DisplayMetrics dm;
    private int mWidth;
    private int mHeight;

    private float textSize = dipToPx(12);
    private int paddingLeft=dipToPx(20);
    private int paddingTop=dipToPx(20);

    //    private int canvasHeight; // LineGraphicView控件在布局中的高度
//    private int canvasWidth; // LineGraphicView控件在布局中的宽度
    private boolean isMeasure = true;

    /**
     * 曲线上总点数
     */
    private Point[] mPoints;
    /**
     * 纵坐标值
     */
    private ArrayList<Double> yRawData = new ArrayList<>();
    /**
     * 横坐标值
     */
    private ArrayList<String> xRawDatas = new ArrayList<>();

    public LineGraphicView(Context context) {
        this(context, null);
    }

    public LineGraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    int width = 0;
    int height = 0;

    private void initView() {
        this.res = mContext.getResources();
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //内容显示文字
        mBottomTxtPaint = new Paint();
        mBottomTxtPaint.setTextSize(textSize);
        mBottomTxtPaint.setColor(Color.WHITE);
        mBottomTxtPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mBottomTxtPaint.setTextAlign(Paint.Align.CENTER);
        dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // 像素
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

/*    int startX;
    int startY;
    int endX;
    int endY;
    private ArrayList<Integer> list_X = new ArrayList<Integer>();// 记录每个x的值
    private ArrayList<Integer> list_X_Copy = new ArrayList<Integer>();// 记录每个x的值
    private ArrayList<Integer> list_Y = new ArrayList<Integer>();// 记录每个y的值
    private ArrayList<Integer> list_Y_Copy = new ArrayList<Integer>();// 记录每个y的值*/

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(res.getColor(R.color.colorPrimaryDark));
        mPoints = getPointsTwo();
        mPaint.setColor(res.getColor(R.color.white));
        mPaint.setStrokeWidth(dip2px(2.5f));
        mPaint.setStyle(Paint.Style.STROKE);
        drawScrollLine(canvas);

        // 画点
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < yRawData.size(); i++) {
            canvas.drawCircle(mPoints[i].x, mPoints[i].y, CIRCLE_SIZE * 4 / 5, mPaint);
            // 25、20两个偏移量，是为了让文字显示在点的上方中央，最好是通过计算文字的宽度来设置位置
            drawText((int) yRawData.get(i).longValue() + "张", mPoints[i].x/* + 25*/, mPoints[i].y - 20, canvas);
        }

        // 画横坐标轴文字
        for (int i = 0; i < yRawData.size(); i++) {
            canvas.drawText(xRawDatas.get(i), mPoints[i].x, mHeight - paddingTop/8, mBottomTxtPaint); // 30偏移量，是为了让X坐标轴的文字显示在坐标轴的下面
        }
    }

    /**
     * 获取实际最大值
     *
     * @param yRawData
     * @return
     */
    public double getMax(ArrayList<Double> yRawData) {
        double max = 0;
        double min = 0;
        for (int i = 0; i < yRawData.size(); i++) {
            double a = yRawData.get(i);
            if (a > max) {
                max = a;
            }
            if (a < min) {
                min = a;
            }
            max = max + min;
            if (max == 0) {
                max = 1;
            }
        }
        return max;
    }

    /**
     * 画线
     *
     * @param canvas
     */
    private void drawScrollLine(Canvas canvas) {
        Point startp = null;
        Point endp = null;
        for (int i = 0; i < mPoints.length - 1; i++) {
            startp = mPoints[i];
            endp = mPoints[i + 1];
            int wt = (startp.x + endp.x) / 2;
            Point p3 = new Point();
            Point p4 = new Point();
            p3.y = startp.y;
            p3.x = wt;
            p4.y = endp.y;
            p4.x = wt;

            Path path = new Path();
            path.moveTo(startp.x, startp.y);
            path.cubicTo(p3.x, p3.y, p4.x, p4.y, endp.x, endp.y);
            canvas.drawPath(path, mPaint);
        }
    }

    private void drawText(String text, int x, int y, Canvas canvas) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setTextSize(dip2px(12));
        p.setColor(res.getColor(R.color.white));
//        p.setTextAlign(Paint.Align.LEFT);
        p.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, x, y, p);
    }

    private Point[] getPointsTwo() {
        Point[] points = new Point[yRawData.size()];
        if (yRawData.size() == 0) {
            return points;
        }
        int eX=(mWidth-2*paddingLeft)/(yRawData.size()-1);
        double ymax = getMax(yRawData);

        for (int i = 0; i < yRawData.size(); i++) {
            int y= (int) ((1.0f-(yRawData.get(i).longValue() / ymax) )* (mHeight - 2*paddingTop)+paddingTop);
            points[i] = new Point(eX*i+paddingLeft, y);
        }
        return points;
    }

    public void setData(ArrayList<Double> yRawData, ArrayList<String> xRawData) {
        this.mPoints = new Point[yRawData.size()];
        this.xRawDatas = xRawData;
        this.yRawData = yRawData;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        return (int) (dpValue * dm.density + 0.5f);
    }
    /**
     * dip 转换成px
     *
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
}