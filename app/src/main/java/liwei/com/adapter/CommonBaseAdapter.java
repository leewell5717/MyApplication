package liwei.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonBaseAdapter<T> extends BaseAdapter {

    private List<T> mDatas;
    private Context mContext;
    private int mLayoutId;

    /**
     * @param context 上下文对象
     * @param mDatas 数据集合
     * @param layoutId 布局文件
     */
    public CommonBaseAdapter(Context context, List<T> mDatas, int layoutId) {
        this.mDatas = mDatas;
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    /**
     * 得到内部数据集合
     */
    public List<T> getDatas() {
        return mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder(mContext, parent, mLayoutId,position);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        T bean = mDatas.get(position);
        onConvert(holder, bean, position);
        return holder.getConvertView();
    }

    /**
     * 设置条目中控件显示的信息
     * @param holder 封装adapter中item的复用操作代码的对象
     * @param bean 数据Bean
     * @param position 当前条目位置
     */
    public abstract void onConvert(ViewHolder holder, T bean, int position);
}