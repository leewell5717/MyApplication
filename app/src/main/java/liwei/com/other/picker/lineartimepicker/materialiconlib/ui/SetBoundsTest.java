package liwei.com.other.picker.lineartimepicker.materialiconlib.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import liwei.com.R;
import liwei.com.other.picker.lineartimepicker.materialiconlib.MaterialDrawableBuilder;

/**
 * TODO: document your custom view class.
 */
public class SetBoundsTest extends View {

    private Drawable mDrawable;
    private Drawable mDrawable2;

    public SetBoundsTest(Context context) {
        super(context);
        init(null, 0);
    }

    public SetBoundsTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SetBoundsTest(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(final AttributeSet attrs, final int defStyle) {
        mDrawable = MaterialDrawableBuilder.with(getContext())
                    .setIcon(MaterialDrawableBuilder.IconValue.NUMERIC_7_BOX)
                    .setColor(Color.RED)
                    .setSizeDp(1)
                .build();

        mDrawable2 = getResources().getDrawable(R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.BLACK);

        Rect bounds = new Rect(0, 100, getMeasuredHeight(), getMeasuredHeight());

        canvas.drawRect(bounds, p);
        mDrawable.setBounds(bounds);
        mDrawable.draw(canvas);
    }
}
