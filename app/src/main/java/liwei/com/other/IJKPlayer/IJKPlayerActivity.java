package liwei.com.other.IJKPlayer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.bumptech.glide.Glide;
import com.dl7.player.media.IjkPlayerView;

import liwei.com.R;

public class IJKPlayerActivity extends Activity {

    private IjkPlayerView player;
    private final String VideoUrl = "http://v.youku.com/v_show/id_XMzA1MTQ5MzI4OA==.html";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijkplayer);

        player = (IjkPlayerView)findViewById(R.id.player);
        Glide.with(this).load(R.mipmap.error).fitCenter().into(player.mPlayerThumb);
        player.init().setTitle("Hello").setSkipTip(1000 * 60 * 1)
                .enableOrientation()
                .setVideoPath(VideoUrl)
                .setVideoSource(null,VideoUrl,VideoUrl,VideoUrl,null)
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                .enableDanmaku()
                .setDanmakuSource(getResources().openRawResource(R.raw.bili))
                .start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        player.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(player.handleVolumeKey(keyCode)){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if(player.onBackPressed()){
            return;
        }
        super.onBackPressed();
    }
}