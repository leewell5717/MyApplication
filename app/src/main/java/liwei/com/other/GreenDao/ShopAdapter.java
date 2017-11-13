package liwei.com.other.GreenDao;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liwei.com.R;

public class ShopAdapter extends BaseAdapter{

    private List<Shop> shops;
    private LayoutInflater inflater;
    private Context context;

    ShopAdapter(Context context,List<Shop> shops){
        this.shops = shops;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return shops.size();
    }

    @Override
    public Shop getItem(int position) {
        return shops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_shop,parent,false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (MyViewHolder) convertView.getTag();
        }
        Shop shop = shops.get(position);
        holder.shopName.setText(shop.getName());
        holder.shopPrice.setText("$" + shop.getPrice() + "");
        holder.shopPriceDiscount.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.shopPriceDiscount.setText("$" + (Float.parseFloat(shop.getPrice()) - 12.50f) + "");
        holder.shopSellNum.setText("已售" + shop.getSell_num() + "件");
        Glide.with(context).load(shop.getImage_url()).placeholder(R.mipmap.ic_launcher).into(holder.shopImage);
        return convertView;
    }

    public class MyViewHolder{
        @BindView(R.id.shop_image)
        public ImageView shopImage;
        @BindView(R.id.shop_name)
        public TextView shopName;
        @BindView(R.id.shop_price)
        public TextView shopPrice;
        @BindView(R.id.shop_price_discount)
        public TextView shopPriceDiscount;
        @BindView(R.id.shop_sell_num)
        public TextView shopSellNum;

        MyViewHolder(View rootView){
            ButterKnife.bind(this,rootView);
        }
    }

    void addDatas(List<Shop> datas){
        shops.addAll(datas);
        notifyDataSetChanged();
    }

    void addDatas(Shop shop){
        shops.add(shop);
        notifyDataSetChanged();
    }

    void deleteDatas(int pos){
        shops.remove(pos);
        notifyDataSetChanged();
    }

    void deleteAllDatas(){
        shops.clear();
        notifyDataSetChanged();
    }
}