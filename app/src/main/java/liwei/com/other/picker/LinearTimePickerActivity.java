package liwei.com.other.picker;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.Toast;

import liwei.com.R;
import liwei.com.other.mybase.MyBaseActivity;
import liwei.com.other.picker.lineartimepicker.ColorAdapter;
import liwei.com.other.picker.lineartimepicker.GradientColorAdapter;
import liwei.com.other.picker.lineartimepicker.adapter.DateAdapter;
import liwei.com.other.picker.lineartimepicker.adapter.LinearPickerAdapter;
import liwei.com.other.picker.lineartimepicker.adapter.TimeAdapter;
import liwei.com.other.picker.lineartimepicker.dialog.LinearDatePickerDialog;
import liwei.com.other.picker.lineartimepicker.dialog.LinearTimePickerDialog;
import liwei.com.other.picker.lineartimepicker.view.LinearPickerView;

public class LinearTimePickerActivity extends MyBaseActivity {

    private int backgroundDark;
    private int foregroundDark;

    @Override
    public int getLayout() {
        return R.layout.activity_linear_time_picker;
    }

    @Override
    public void doAction() {

    }

    @Override
    public void initViews() {
        actionBtn.setVisibility(View.GONE);
        title.setText("LinearTimePicker");
        backgroundDark = ResourcesCompat.getColor(getResources(), R.color.background_dark, getTheme());
        foregroundDark = ResourcesCompat.getColor(getResources(), R.color.foreground_dark, getTheme());

        LinearPickerView v = new LinearPickerView(this);
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);

        LinearPickerAdapter dateAdap = new DateAdapter(this, textPaint);
        LinearPickerAdapter timeAdap = new TimeAdapter(this, textPaint);
        LinearPickerAdapter colorAdap = new ColorAdapter(this, textPaint);
        LinearPickerAdapter graColorAdap = new GradientColorAdapter(this, textPaint);
        v.setAdapter(dateAdap);
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        v.setLineColor(Color.GRAY);
        v.setActiveLineColor(Color.WHITE);
        v.setHandleBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        findViewById(R.id.ltp_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearTimePickerDialog dialog = LinearTimePickerDialog.Builder.with(LinearTimePickerActivity.this)
                        .setDialogBackgroundColor(foregroundDark)
                        .setPickerBackgroundColor(backgroundDark)
                        .setLineColor(Color.argb(64, 255, 255, 255))
                        .setTextColor(Color.WHITE)
                        .setShowTutorial(true)
                        .setTextBackgroundColor(Color.argb(16, 255, 255, 255))
                        .setButtonCallback(new LinearTimePickerDialog.ButtonCallback() {
                            @Override
                            public void onPositive(DialogInterface dialog, int hour, int minutes) {
                                Toast.makeText(LinearTimePickerActivity.this, "" + hour + ":" + minutes, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNegative(DialogInterface dialog) {

                            }
                        })
                        .build();
                dialog.show();
            }
        });
        findViewById(R.id.ltp_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearDatePickerDialog.Builder.with(LinearTimePickerActivity.this)
                        .setDialogBackgroundColor(foregroundDark)
                        .setPickerBackgroundColor(backgroundDark)
//                        .setLineColor(Color.argb(64, 255, 255, 255))
                        .setTextColor(Color.WHITE)
//                        .setTextBackgroundColor(Color.argb(16, 255, 255, 255))
                        .setYear(2017)
                        .setMinYear(2000)
                        .setMaxYear(2030)
                        .setShowTutorial(true)
                        .setButtonCallback(new LinearDatePickerDialog.ButtonCallback() {
                            @Override
                            public void onPositive(DialogInterface dialog, int year, int month, int day) {
                                Toast.makeText(LinearTimePickerActivity.this, "" + year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNegative(DialogInterface dialog) {

                            }
                        })
                        .build()
                        .show();
            }
        });
    }
}