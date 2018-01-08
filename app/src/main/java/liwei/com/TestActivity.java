package liwei.com;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

public class TestActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button openDialog = (Button)findViewById(R.id.open_dialog);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyDialog(TestActivity.this);
            }
        });
    }

    private void showMyDialog(Context context){
        final Dialog dialog = new Dialog(context,R.style.MyDialog);
        dialog.show();
        LayoutInflater inflater = LayoutInflater.from(this);
        View viewDialog = inflater.inflate(R.layout.activity_sliding_menu, null);
        Display display = this.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        //设置dialog的宽高为屏幕的宽高
        ViewGroup.LayoutParams layoutParams = new  ViewGroup.LayoutParams(width, height);
        dialog.setContentView(viewDialog, layoutParams);

        LinearLayout root = (LinearLayout) viewDialog.findViewById(R.id.root);
        final WebView webview = (WebView) viewDialog.findViewById(R.id.webview);
        webview.loadUrl("http://test.17byh.com/src/components/personCenter.html");
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(webview,"translationX",0f,-1600f);
        animator1.setDuration(10).start();
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }
            @Override
            public void onAnimationEnd(Animator animation) {
                webview.setVisibility(View.VISIBLE);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(webview,"translationX",-1600f,0f);
                animator2.setDuration(500).start();
            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(webview,"translationX",-0f,-1600f);
                animator2.setDuration(500).start();
                animator2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        dialog.dismiss();
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });
    }
}