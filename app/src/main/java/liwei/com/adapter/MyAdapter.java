package liwei.com.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import liwei.com.R;

public class MyAdapter extends CommonBaseAdapter<String> {
    /**
     * @param context  上下文对象
     * @param mDatas   数据集合
     * @param layoutId 布局文件
     */
    public MyAdapter(Context context, List<String> mDatas, int layoutId) {
        super(context, mDatas, layoutId);
    }

    @Override
    public void onConvert(ViewHolder holder, String bean, int position) {
        TextView textView = holder.getView(R.id.text);
        textView.setText(bean);
    }
}