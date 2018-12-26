package liwei.com.other.titlebaralphagradient.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liwei.com.R;

/**
 * listview数据适配器
 */
public class MyListViewAdapter extends BaseAdapter {

    private List<String> mData;
    private LayoutInflater inflater;

    public MyListViewAdapter(Context context, List<String> mData) {
        this.mData = mData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_title_bar_alpha_gradient, parent, false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(mData.get(position));
        return convertView;
    }

    public class MyViewHolder {
        @BindView(R.id.show_some_text)
        public TextView text;

        public MyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}