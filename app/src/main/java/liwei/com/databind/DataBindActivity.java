package liwei.com.databind;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liwei.com.R;
import liwei.com.databinding.ActivityDataBindBinding;

public class DataBindActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_data_bind);
        binding.setBtnName("My Button");

        List<String> list = new ArrayList<>();
        list.add("abx");
        list.add("b");
        Map<Integer,String> map = new HashMap<>();
        map.put(0,"xxxx");
        map.put(1,"VVV");
        map.put(2,"OOO...");


        final UserBean userBean = new UserBean();
        userBean.setName("Lee");
        binding.setUser(userBean);
        binding.setHandle(new HandleEvent());
        binding.setList(list);
        binding.setMap(map);

        binding.edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userBean.setName(s.toString());
            }
        });
    }
}