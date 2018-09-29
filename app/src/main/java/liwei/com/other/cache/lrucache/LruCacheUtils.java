package liwei.com.other.cache.lrucache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * LruCache工具类
 */
public class LruCacheUtils {
    // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常
    private static final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    // 使用最大可用内存值的1/8作为缓存的大小。
    private static final int cacheSize = maxMemory / 8;

    private LruCache<String,Bitmap> lruCache;

    public LruCacheUtils(){
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    /**
     * 添加缓存
     */
    public void addToMemoryCache(String key, Bitmap bitmap){
        if(getFromMemoryCache(key) == null){
            lruCache.put(key,bitmap);
        }
    }

    /**
     * 获取缓存
     */
    public Bitmap getFromMemoryCache(String key){
        return lruCache.get(key);
    }
}