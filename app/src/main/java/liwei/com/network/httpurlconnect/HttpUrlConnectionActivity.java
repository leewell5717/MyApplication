package liwei.com.network.httpurlconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * HttpUrlConnection网络请求主页
 */
public class HttpUrlConnectionActivity extends Activity {

    @BindView(R.id.get_btn)
    public Button getBtn;
    @BindView(R.id.post_btn)
    public Button PostBtn;
    @BindView(R.id.download_btn)
    public Button downloadBtn;
    @BindView(R.id.get_result)
    public TextView getResult;
    @BindView(R.id.post_result)
    public TextView postResult;

    private static final String url = "http://www.baidu.com";
    private static final String url2 = "http://apicloud.mob.com/appstore/calendar/day";
    private static final String url3 = "http://sqdd.myapp.com/myapp/qqteam/tim/down/tim.apk";

    private static final int getWhat = 1;
    private static final int postWhat = 2;
    private static final int downloadWhat = 3;

    private static final String root  = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "liwei";
    private static final String savePath = root + File.separator + "images";
    private static final String fileName = "myImage001";
    //文件后缀名
    private static String prefix;

    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnect);
        ButterKnife.bind(this);

        myHandler = new MyHandler(this,getResult,postResult);
    }

    private static class MyHandler extends Handler{
        private WeakReference<Activity> reference;
        private TextView getTextView,postTextView;
        MyHandler(Activity activity,TextView getTextView,TextView postTextView){
            reference = new WeakReference<>(activity);
            this.getTextView = getTextView;
            this.postTextView = postTextView;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case getWhat:
                    String getResultStr = msg.obj.toString();
                    if(TextUtils.isEmpty(getResultStr)){
                        getTextView.setText("get请求返回数据是空的");
                    }else{
                        getTextView.setText(getResultStr);
                    }
                    break;
                case postWhat:
                    String postResultStr = msg.obj.toString();
                    if(TextUtils.isEmpty(postResultStr)){
                        postTextView.setText("post请求返回数据是空的");
                    }else{
                        postTextView.setText(postResultStr);
                    }
                    break;
                case downloadWhat:
                    int flag = msg.arg1;
                    if(flag == 1){ //下载成功
                        Toast.makeText(reference.get(),"下载成功",Toast.LENGTH_SHORT).show();
                    }else if(flag == 0){
                        Toast.makeText(reference.get(),"下载失败",Toast.LENGTH_SHORT).show();
                    }else if(flag == 2){
                        AlertDialog.Builder builder = new AlertDialog.Builder(reference.get());
                        builder.setTitle("提示");
                        builder.setMessage("检测到本地已经存在该文件，是否删除？");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                File file = new File(savePath + File.separator + fileName + prefix);
                                if(file.exists()){
                                    if(file.delete()){
                                        Toast.makeText(reference.get(),"删除成功",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(reference.get(),"删除失败",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                        builder.setNegativeButton("取消", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    break;
            }
        }
    }

    @OnClick({R.id.get_btn,R.id.post_btn,R.id.download_btn})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.get_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String getResultStr = getRequest(url);
                        Message message = myHandler.obtainMessage();
                        message.what = getWhat;
                        message.obj = getResultStr;
                        myHandler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.post_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String postResultStr = postRequest(url2);
                        Message message = myHandler.obtainMessage();
                        message.what = postWhat;
                        message.obj = postResultStr;
                        myHandler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.download_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = myHandler.obtainMessage();
                        message.what = downloadWhat;
                        message.arg1 = downloadRequest(url3,savePath,fileName);
                        myHandler.sendMessage(message);
                    }
                }).start();
                break;
        }
    }

    /**
     * get请求
     */
    private String getRequest(String urlPath){
        StringBuilder builder = null;
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            //创建URL对象
            URL url = new URL(urlPath); //Get请求可以在Url中带参数： ①url + "?bookname=" + name;②url="http://www.baidu.com?name=zhang&pwd=123";
            //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
            connection = (HttpURLConnection) url.openConnection();
            //设置连接超时
            connection.setConnectTimeout(10 * 1000);
            //设定请求方式(默认为get)
            connection.setRequestMethod("GET");
            //添加cookie——1、使用下面的方法（没有验证过）；2、使用CookieManager
//            connection.setRequestProperty("&quot;Cookie&quot;", "&quot;Cookie: &quot;" + "我的Cookie");
            //建立到远程对象的实际连接
            connection.connect();

            //返回打开连接读取的输入流，输入流转化为StringBuffer类型，这一套流程要记住，常用
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            builder = new StringBuilder();
//            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null){
                //转化为UTF-8的编码格式
                line = new String(line.getBytes("UTF-8"));
                builder.append(line);
            }
            //打印信息
            log("get返回数据："+builder.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }
        }
        return builder.toString();
    }

    /**
     * post请求
     */
    private String postRequest(String urlPath){
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        StringBuilder stringBuilder = null;
        try {
            //创建URL对象
            URL url = new URL(urlPath); //Get请求可以在Url中带参数： ①url + "?bookname=" + name;②url="http://www.baidu.com?name=zhang&pwd=123";
            //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
            connection = (HttpURLConnection) url.openConnection();
            //设置连接超时
            connection.setConnectTimeout(10 * 1000);
            //设定请求方式(默认为get)
            connection.setRequestMethod("POST");
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false;
            connection.setDoOutput(true);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            connection.setDoInput(true);
            // Post 请求不能使用缓存
            connection.setUseCaches(false);


            //开始设置请求头
            // 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            connection.setRequestProperty("Content-type", "application/x-java-serialized-object");
            //设置一般请求属性。
            //setRequestProperty(String key, String value)
            // 连接，从上述url.openConnection()至此的配置必须要在connect之前完成，
            connection.connect();

            //getOutputStream()里默认就有connect()了，可以不用写上面的连接
            //接下来我们设置post的请求参数，可以是JSON数据，也可以是普通的数据类型
            OutputStream outputStream = connection.getOutputStream();

            /*
             * JSON数据的请求
             * outputStream.write(stringJson.getBytes(), 0, stringJson.getBytes().length);
             * outputStream.close();
             * **/

            /*
             * 字符串数据的请求
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
             String content = "username=" + username + "&password=" + password;
             dataOutputStream.writeBytes(content);
             dataOutputStream.flush();
             dataOutputStream.close();
             * **/

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            StringBuilder builder = new StringBuilder();
            builder.append("key").append("=").append("1863a50c31d7c");
            dataOutputStream.writeBytes(builder.toString());
            dataOutputStream.flush();
            dataOutputStream.close();


            //读取返回的数据
            //返回打开连接读取的输入流，输入流转化为StringBuffer类型，这一套流程要记住，常用
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null){
                line = new String(line.getBytes("UTF-8"));
                stringBuilder.append(line);
            }
            log("post返回数据："+stringBuilder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 文件下载
     * @param urlPath 下载地址
     * @param filePath 文件路径
     * @param fileName 自定义文件名
     * @return 1-成功；0-失败；2-文件存在
     */
    private int downloadRequest(String urlPath,String filePath,String fileName){
        try{
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设定请求的方法，默认是GET
            connection.setRequestMethod("GET");
            // 设置字符编码
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            //不使用缓存
            connection.setUseCaches(false);
            //文件下载必须注释掉，不然会出现FileNotFoundException
//            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            connection.connect();

            int fileLength = connection.getContentLength();
            log("文件大小：" + fileLength);

            File saveFilePath = new File(filePath);
            if(!saveFilePath.exists()){
                saveFilePath.mkdirs();
            }

            //获取文件扩展名，带点（例如：.png）
            prefix = urlPath.substring(urlPath.lastIndexOf("."));
            //如果不带点，则使用下面的方法
//            String prefix = urlPath.substring(urlPath.lastIndexOf(".") + 1);
            log("文件扩展名：" + prefix);

            File file = new File(filePath + File.separator + fileName + prefix);
            if(file.exists()){
                return 2;
            }

            log("状态码：" + connection.getResponseCode());

            //开始下载
            InputStream inputStream = connection.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(file);
            int size;
            int length = 0;
            byte[] b = new byte[1024];
            while ((size = inputStream.read(b)) != -1){
                length += size;
                outputStream.write(b,0,size);
                //打印下载的百分比
                log("已经下载了：" + length);
                log("下载了：" + ((length * 100) / fileLength) + "%");
            }
            inputStream.close();
            outputStream.close();
            connection.disconnect();

            return 1;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void log(String msg){
        Log.e("XXX",msg);
    }
}