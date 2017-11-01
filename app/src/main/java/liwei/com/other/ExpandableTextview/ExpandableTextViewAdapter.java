package liwei.com.other.ExpandableTextview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liwei.com.R;
import liwei.com.other.ExpandableTextview.view.ExpandableTextView;

/**
 * 可伸缩textview列表适配器
 */
public class ExpandableTextViewAdapter extends BaseAdapter {

    private List<String> datas;
    private LayoutInflater inflater;
    private Context context;

    public ExpandableTextViewAdapter(Context context,List<String> datas){
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public String getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_expand_collapse,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.expandableTextview.setText(datas.get(position), position);

        return convertView;
    }

    public class ViewHolder{
        @BindView(R.id.expandable_textview)
        public ExpandableTextView expandableTextview;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this,itemView);
        }
    }
}