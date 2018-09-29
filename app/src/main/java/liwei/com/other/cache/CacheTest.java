package liwei.com.other.cache;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.other.cache.disklrucache.DiskLruCache;
import liwei.com.other.cache.lrucache.LruCacheUtils;

/**
 * 缓存测试（LruCache、DiskLruCache）
 */

public class CacheTest extends Activity {
    @BindView(R.id.use_lrucache_btn)
    public Button useLrucacheBtn;
    @BindView(R.id.use_disklrucache_btn)
    public Button useDisklrucacheBtn;
    @BindView(R.id.lrucache_iamge)
    public ImageView lrucacheIamge;
    @BindView(R.id.disklrucache_iamge)
    public ImageView disklrucacheIamge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.use_lrucache_btn,R.id.use_disklrucache_btn})
    public void myOnClick(View view){
        switch (view.getId()){
            case R.id.use_lrucache_btn:
                userLruCache(CacheTest.this);
                break;
            case R.id.use_disklrucache_btn:
                useDiskLruCache(CacheTest.this);
                break;
        }
    }

    /**
     * 使用LruCache
     */
    private void userLruCache(Context context){
        String imageUrl = "http://img0.imgtn.bdimg.com/it/u=195510469,1464334217&fm=27&gp=0.jpg";
        String name = "lrucache";
        DownloadTask.getInstance(context).startLruCache(imageUrl);

        LruCacheUtils cacheUtils = new LruCacheUtils();
        lrucacheIamge.setImageBitmap(cacheUtils.getFromMemoryCache(name));
    }

    /**
     * 使用DiskLruCache
     */
    private void useDiskLruCache(Context context){
        String imageUrl = "http://img4.imgtn.bdimg.com/it/u=1926421234,1074130284&fm=27&gp=0.jpg";
        String name = "disklrucache";
        DownloadTask.getInstance(context).startDiskLruCache(imageUrl);

        try {
            DiskLruCache diskLruCache = DiskLruCache.open(DownloadTask.getInstance(context).getDiskCacheDir(context,MD5.getMD5String(name)),1,1,10*1024*1024);
            DiskLruCache.Snapshot snapShot = diskLruCache.get(name);
            if(snapShot != null){
                InputStream inputStream = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                disklrucacheIamge.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}