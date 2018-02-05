package liwei.com;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class TestCustomView extends TextView {

    private Paint paint;

    public TestCustomView(Context context){
        super(context);
        init();
    }

    public TestCustomView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(20);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec){
        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureSize = MeasureSpec.getSize(widthMeasureSpec);
        int result = 100;
        switch (measureMode){
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.EXACTLY:
                result = measureSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result,measureSize);
                break;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec){
        int measureMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureSize = MeasureSpec.getSize(heightMeasureSpec);
        int result = 100;
        switch (measureMode){
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.EXACTLY:
                result = measureSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result,measureSize);
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("Hello",0,0,paint);
    }
}