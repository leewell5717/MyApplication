package liwei.com.other.PasswordView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.EditText;

import liwei.com.R;

public class PasswordView extends EditText {

    private final static float ALPHA_ENABLED_DARK = 0.54f;
    private final static float ALPHA_DISABLED_DARK = 0.38f;
    private final static float ALPHA_ENABLED_LIGHT = 1.0f;
    private final static float ALPHA_DISABLED_LIGHT = 0.5f;
    private Drawable eye;
    private Drawable eyeWithStrike;
    private int alphaEnabled;
    private int alphaDisabled;
    private boolean visible = false;
    private boolean useStrikeThrough = false;
//    private Typeface typeface;
    private boolean drawableClick;

    public PasswordView(Context context) {
        super(context);
        init(null);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.PasswordView,
                    0, 0);
            try {
                useStrikeThrough = a.getBoolean(R.styleable.PasswordView_useStrikeThrough, false);
            } finally {
                a.recycle();
            }
        }

        int enabledColor = resolveAttr(android.R.attr.textColorPrimary);
        boolean isIconDark = isDark(enabledColor);
        alphaEnabled = (int) (255 * (isIconDark ? ALPHA_ENABLED_DARK : ALPHA_ENABLED_LIGHT));
        alphaDisabled = (int) (255 * (isIconDark ? ALPHA_DISABLED_DARK : ALPHA_DISABLED_LIGHT));

        eye = getToggleDrawable(getContext(), R.drawable.ic_eye, enabledColor);
        eyeWithStrike = getToggleDrawable(getContext(), R.drawable.ic_eye_strike, enabledColor);
        eyeWithStrike.setAlpha(alphaEnabled);
        setup();
    }

    private @ColorInt int resolveAttr(@AttrRes int attrRes) {
        TypedValue ta = new TypedValue();
        getContext().getTheme().resolveAttribute(attrRes, ta, true);
        return ContextCompat.getColor(getContext(), ta.resourceId);
    }

    private Drawable getToggleDrawable(Context context, @DrawableRes int drawableResId, @ColorInt int tint) {
        // Make sure to mutate so that if there are multiple password fields, they can have
        // different visibilities.
        Drawable drawable = getVectorDrawableWithIntrinsicBounds(context, drawableResId).mutate();
        DrawableCompat.setTint(drawable, tint);
        return drawable;
    }

    private Drawable getVectorDrawableWithIntrinsicBounds(Context context, @DrawableRes int drawableResId) {
        Drawable drawable = getVectorDrawable(context, drawableResId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

    private Drawable getVectorDrawable(Context context, @DrawableRes int drawableResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getDrawable(drawableResId);
        } else {
            return VectorDrawableCompat.create(context.getResources(), drawableResId, context.getTheme());
        }
    }

    protected void setup() {
        int start = getSelectionStart();
        int end = getSelectionEnd();
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        setSelection(start, end);
        Drawable drawable = useStrikeThrough && !visible ? eyeWithStrike : eye;
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
        eye.setAlpha(visible && !useStrikeThrough ? alphaEnabled : alphaDisabled);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        int drawableRightX = getWidth() - getPaddingRight();
        int drawableLeftX = drawableRightX - getCompoundDrawables()[2].getBounds().width();
        boolean eyeClicked = event.getX() >= drawableLeftX && event.getX() <= drawableRightX;

        if (event.getAction() == MotionEvent.ACTION_DOWN && eyeClicked) {
            drawableClick = true;
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (eyeClicked && drawableClick) {
                drawableClick = false;
                visible = !visible;
                setup();
                invalidate();
                return true;
            } else {
                drawableClick = false;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override public void setInputType(int type) {
//        this.typeface = getTypeface();
        super.setInputType(type);
//        setTypeface(typeface);
    }

    @Override public void setError(CharSequence error) {
        throw new RuntimeException("Please use TextInputLayout.setError() instead!");
    }

    @Override public void setError(CharSequence error, Drawable icon) {
        throw new RuntimeException("Please use TextInputLayout.setError() instead!");
    }

    public void setUseStrikeThrough(boolean useStrikeThrough) {
        this.useStrikeThrough = useStrikeThrough;
    }

    private static boolean isDark(float[] hsl) {
        return hsl[2] < 0.5f;
    }

    public static boolean isDark(@ColorInt int color) {
        float[] hsl = new float[3];
        android.support.v4.graphics.ColorUtils.colorToHSL(color, hsl);
        return isDark(hsl);
    }
}