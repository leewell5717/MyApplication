package liwei.com.other.webview.jsbridge;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

/**
 * 自定义ProgressBar，带流光效果
 */
public class CustomProgressBar extends ProgressBar {

    private int mWidth;
    private int mHeight;

    private ValueAnimator valueAnimator;

    private Path shaderPath;
    private Paint shaderPaint;

    private int mSuccessAnimOffset;

    public CustomProgressBar(Context context){
        super(context);
    }

    public CustomProgressBar(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    private void init(){
        int width = (int) TypedValue.applyDimension(1, 40.0F, this.getResources().getDisplayMetrics());

        this.shaderPaint.setShader(new LinearGradient(0, 0, width, 0,
                new int[] { Color.TRANSPARENT, Color.BLACK},
                null, Shader.TileMode.CLAMP));

        this.shaderPath = new Path();
        this.shaderPath.moveTo(0.0f,0.0f);
        this.shaderPath.lineTo((float)width,0.0f);
        this.shaderPath.lineTo((float)(width),(float)this.mHeight);
        this.shaderPath.lineTo((float)0,(float)this.mHeight);

        this.shaderPath.close();

        valueAnimator = ValueAnimator.ofInt(new int[]{0,this.mWidth + width}).setDuration(2500l);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSuccessAnimOffset = ((Integer)animation.getAnimatedValue()).intValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        init();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.shaderPath != null){
            canvas.translate((float)this.mSuccessAnimOffset,0.0f);
            canvas.drawPath(this.shaderPath,this.shaderPaint);
        }
    }

    public void startAnimation(){
        valueAnimator.start();
    }
}