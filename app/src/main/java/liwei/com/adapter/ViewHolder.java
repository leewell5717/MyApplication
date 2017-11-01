package liwei.com.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 封装adapter中item的复用操作代码<br>
 * 使用方法：<br>
 * 1、通过ViewHolder.getInstance(...)方法传入必要的参数得到ViewHolder对象<br>
 * ，该过程已被封装到ConnomAdapter的内部<br>
 * 2、通过ViewHolder对象的方法getView(...)传入View的id得到对应控件<br>
 * 3、通过ViewHolder对象的方法getConvertView()得到条目复用对象convertView<br>
 */
public class ViewHolder {
    private final SparseArray<View> mViews;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position){
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        //setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     */
    public static ViewHolder get(Context context,View convertView,
            ViewGroup parent,int layoutId,int position){
        if(convertView == null){
            return new ViewHolder(context,parent,layoutId,position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }
}