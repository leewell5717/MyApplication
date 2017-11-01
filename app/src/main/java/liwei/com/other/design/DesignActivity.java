package liwei.com.other.design;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

public class DesignActivity extends Activity {
    @BindView(R.id.show_snack_bar)
    public Button showSnackBar;
    @BindView(R.id.input)
    public EditText input;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.show_snack_bar})
    public void myOnClick(View v){
        switch (v.getId()){
            case R.id.show_snack_bar:
                Snackbar.make(v,"这是SnackBar",Snackbar.LENGTH_SHORT)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showCalendar(DesignActivity.this);
                            }
                        })
                        .show();
                break;
        }
    }

    private void showCalendar(Context context){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                input.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
            }
        }, year, month, day);
        datePickerDialog.setMessage("生日选择");
        datePickerDialog.show();
    }
}