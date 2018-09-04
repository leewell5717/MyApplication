package liwei.com.other.Float;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import liwei.com.R;

import static liwei.com.other.Float.FloatView.imgUrl;

public class MyCustomDialogFragment extends DialogFragment implements View.OnClickListener{

    private ImageView imageView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.MyDialog_Float);
        dialog.setContentView(R.layout.view_dialog_float);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.START);
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        params.width = (int) (displayMetrics.widthPixels * 0.5);
        params.height = (int) (displayMetrics.heightPixels * 0.99);
        window.setAttributes(params);

        Button button = (Button)dialog.findViewById(R.id.loadImageBtn);
        imageView = (ImageView)dialog.findViewById(R.id.showMyImage);
        button.setOnClickListener(this);

        return dialog;
    }

    @Override
    public void onClick(View v) {
        Glide.with(this).load(imgUrl).into(imageView);
    }

}