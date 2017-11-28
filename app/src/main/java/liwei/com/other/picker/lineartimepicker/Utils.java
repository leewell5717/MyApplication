package liwei.com.other.picker.lineartimepicker;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;

public class Utils {
    public static int dpToPx(Context c, int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, c.getResources().getDisplayMetrics());
    }

    public static void getTextBounds(Paint paint, String text, Rect bounds) {
        paint.getTextBounds(text, 0, text.length(), bounds);
    }
}