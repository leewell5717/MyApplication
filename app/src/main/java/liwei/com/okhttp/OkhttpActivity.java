package liwei.com.okhttp;

import android.Manifest;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import liwei.com.R;
import liwei.com.databinding.ActivityOkhttpBinding;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends Activity {
    private static final String Tag = OkhttpActivity.class.getSimpleName();
    private ActivityOkhttpBinding binding;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_okhttp);
        binding.setGetBtnName("get请求");
        binding.setPostBtnName("post请求");
        binding.setUploadBtnName("文件上传");
        binding.setDownloadBtnName("文件下载");
        binding.setProgressTip("请求中...");
        binding.setProgressContainerVisible(View.GONE);
        binding.setBtnClick(this);

        RxPermissions permissions = new RxPermissions(this);
        permissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if(permission.granted){
                    // 用户已经同意该权限
                    Log.e(Tag,"已经同意："+permission.name+"权限");
                    Toast.makeText(OkhttpActivity.this,"已经同意："+permission.name+"权限",Toast.LENGTH_SHORT).show();
                }else if(permission.shouldShowRequestPermissionRationale){
                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                    Log.e(Tag,"已拒绝："+permission.name+"权限,但下次还会提示");
                    Toast.makeText(OkhttpActivity.this,"已拒绝："+permission.name+"权限,但下次还会提示",Toast.LENGTH_SHORT).show();
                }else {
                    // 用户拒绝了该权限，并且选中『不再询问』
                    Log.d(Tag, "已拒绝："+permission.name + "且不再询问");
                    Toast.makeText(OkhttpActivity.this,"已拒绝："+permission.name + "且不再询问",Toast.LENGTH_SHORT).show();
                }
            }
        });

        init();
    }

    private void init(){
        File cacheFile = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(5000,TimeUnit.MILLISECONDS);
        builder.readTimeout(5000,TimeUnit.MILLISECONDS);
        builder.cache(new Cache(cacheFile,cacheSize));
        client = builder.build();
    }

    public void getBtnClick(View view){
        binding.setGetResult("这是get请求按钮点击");
        binding.setProgressContainerVisible(View.VISIBLE);
        Request.Builder builder = new Request.Builder();
        builder.url("http://www.baidu.com");
        builder.method("GET",null);
        Request request = builder.build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                binding.setGetResult("get请求失败"+e.getMessage());
                binding.setProgressContainerVisible(View.GONE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.cacheResponse() != null){
                    Log.e(Tag,response.cacheResponse().toString());
                }else{
                    Log.e(Tag,response.networkResponse().toString());
                }
                Log.e(Tag,"请求成功");
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.setGetResult(result);
                        Toast.makeText(OkhttpActivity.this,"数据加载成功",Toast.LENGTH_SHORT).show();
                        binding.setProgressContainerVisible(View.GONE);
                    }
                });
            }
        });
    }

    public void postBtnClick(View view){
        binding.setPostResult("这是post请求按钮点击");
        binding.setProgressContainerVisible(View.VISIBLE);
        RequestBody requestBody = new FormBody.Builder().add("rand","635840524184357321").build();
        Request request = new Request.Builder().url("http://ditu.amap.com/service/pl/pl.json")
                .post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                binding.setPostResult("post请求失败:"+e.getMessage());
                binding.setProgressContainerVisible(View.GONE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(Tag,"请求成功");
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.setPostResult(result);
                        Toast.makeText(OkhttpActivity.this,"数据加载成功",Toast.LENGTH_SHORT).show();
                        binding.setProgressContainerVisible(View.GONE);
                    }
                });

            }
        });
    }

    public void uploadBtnClick(View view){
        binding.setUploadResult("上传按钮点击");
        binding.setProgressContainerVisible(View.VISIBLE);
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");

        File file = new File(Environment.getExternalStorageDirectory(),"MyTest.txt");
        if(!file.exists()){
            binding.setUploadResult("没有此文件");
        }else{
            Request request = new Request.Builder()
                    .url("https://api.github.com/markdown/raw")
                    .post(RequestBody.create(mediaType,file))
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    binding.setUploadResult("上传失败："+e.getMessage());
                    binding.setProgressContainerVisible(View.GONE);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e(Tag,"请求成功");
                    final String result = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.setUploadResult(result);
                            Toast.makeText(OkhttpActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                            binding.setProgressContainerVisible(View.GONE);
                        }
                    });
                }
            });
        }
    }

    public void downloadBtnClick(View view){
        binding.setDownloadResult("下载按钮点击");
        binding.setProgressContainerVisible(View.VISIBLE);

        Request request = new Request.Builder()
                .url("http://img.bimg.126.net/photo/DCi7Q__VN3NJ_63cq7sosA==/3439905690381537164.jpg").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                binding.setDownloadResult("下载失败："+e.getMessage());
                binding.setProgressContainerVisible(View.GONE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(),"img.jpg"));
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1){
                    fileOutputStream.write(buffer,0,len);
                }
                fileOutputStream.flush();

                binding.setProgressContainerVisible(View.GONE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String filePath = "file://" + Environment.getExternalStorageDirectory() + "img.jpg";
                        File file = new File(Environment.getExternalStorageDirectory().getPath(),"img.jpg");
                        if(!file.exists()){
                            binding.setDownloadResult("没有下载此图片");
                        }else{
//                            Glide.with(OkhttpActivity.this).load(file)
                            Glide.with(OkhttpActivity.this).load(filePath)
                                    .placeholder(R.mipmap.error).error(R.mipmap.error).into(binding.img);
                            binding.setDownloadResult("图片加载完成");
                        }
                    }
                });
            }
        });
    }
}