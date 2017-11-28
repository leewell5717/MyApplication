package liwei.com.other.picker.lineartimepicker.materialiconlib.ui;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import liwei.com.R;
import liwei.com.other.picker.lineartimepicker.materialiconlib.MaterialDrawableBuilder;

public class ImageAdapter extends BaseAdapter {

    List<MaterialDrawableBuilder.IconValue> icons;

    public ImageAdapter(@NonNull List<MaterialDrawableBuilder.IconValue> iconin){
        this.icons = iconin;
    }

    @Override
    public int getCount() {
        return icons.size();
    }

    @Override
    public Object getItem(int i) {
        return icons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_material_icon_lib, null, false);
            view.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
        }
        FrameLayout host = (FrameLayout) view;
        ImageView v = (ImageView) host.getChildAt(0);
        TextView tv = (TextView)  host.getChildAt(1);
        v.setImageDrawable(
                MaterialDrawableBuilder.with(viewGroup.getContext())
                    .setIcon(icons.get(i))
                    .setColor(Color.BLACK)
                    .setSizePx(viewGroup.getWidth() / 5)
                    .build()
        );
        tv.setText(icons.get(i).name());
        return view;
    }
}
