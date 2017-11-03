package liwei.com.other.TakePictuer;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import liwei.com.R;

/**
 * 拍照/选择相册中的图片adapter
 */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.MyViewHolder>{

    private List<Bitmap> imageList = new ArrayList<>();
    private Context context;

    public ImageRecyclerAdapter(Context context,List<Bitmap> imageList){
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_picture,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = imageList.get(position);
        bitmap.compress(Bitmap.CompressFormat.PNG, 75, baos);
        byte[] bytes=baos.toByteArray();

        Glide.with(context).load(bytes).asBitmap().placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.error).override(100,100).fitCenter().into(holder.imageView);
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDeleteDialog(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        private MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image_picture);
        }
    }

    public void addImage(Bitmap bitmap){
        imageList.add(bitmap);
        notifyDataSetChanged();
    }

    /**
     * 显示删除对话框
     */
    private void showDeleteDialog(final int pos){
        final Dialog dialog = new Dialog(context,R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_setting);
        TextView content = (TextView)dialog.findViewById(R.id.content);
        Button confirmBtn = (Button)dialog.findViewById(R.id.confirm_btn);
        Button cancelBtn = (Button)dialog.findViewById(R.id.cancel_btn);
        content.setText("确认要删除图片吗？");
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                imageList.remove(pos);
                notifyDataSetChanged();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
