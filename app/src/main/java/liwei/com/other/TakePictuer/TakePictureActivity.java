package liwei.com.other.TakePictuer;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import liwei.com.R;

/**
 * 拍照/选择系统相册
 */
public class TakePictureActivity extends Activity {
    //拍照
    private static final int TakePic = 1;
    //选择相册
    private static final int SelectPic = 2;
    //裁剪相册
    private static final int CutPic = 3;

    //根目录
    private static final String rootPath = Environment.getExternalStorageDirectory().getPath() + "/takePic/";

    @BindView(R.id.take_picture_btn)
    public Button takePictureBtn;
    @BindView(R.id.select_album_btn)
    public Button selectAlbumBtn;
    @BindView(R.id.picture)
    public ImageView picture;

    //相机拍照图片保存地址
    private Uri takeImageUri;
    //参见完成后图片保存地址
    private Uri cutImageUri;
    //打开相册选择照片的路径
    private String selectImagePath;
    //当前拍照图片的文件名
    private String currentFileName;

    //是否是拍照裁剪
    private boolean isClickCamera = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        ButterKnife.bind(this);

        //权限申请
        RxPermissions permissions = new RxPermissions(this);
        permissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if(permission.granted){
                    // 用户已经同意该权限
                    Toast.makeText(TakePictureActivity.this,"已经同意："+permission.name+"权限",Toast.LENGTH_SHORT).show();
                }else if(permission.shouldShowRequestPermissionRationale){
                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                    Toast.makeText(TakePictureActivity.this,"已拒绝："+permission.name+"权限,但下次还会提示",Toast.LENGTH_SHORT).show();
                }else {
                    // 用户拒绝了该权限，并且选中『不再询问』
                    Toast.makeText(TakePictureActivity.this,"已拒绝："+permission.name + "且不再询问",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick({R.id.take_picture_btn,R.id.select_album_btn})
    public void click(View v){
        switch (v.getId()){
            case R.id.take_picture_btn:
                takePicture();
                break;
            case R.id.select_album_btn:
                selectPicture();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TakePic: //拍照
                if(resultCode == RESULT_OK){
                    cutPicture(takeImageUri);
                }
                break;
            case SelectPic: //选择相册
                if (Build.VERSION.SDK_INT >= 19) {
                    // 4.4及以上系统使用这个方法处理图片
                    handleImageOnKitKat(data);
                } else {
                    // 4.4以下系统使用这个方法处理图片
                    handleImageBeforeKitKat(data);
                }
                break;
            case CutPic: //裁剪
                Bitmap bitmap;
                try {
                    if (isClickCamera) {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(cutImageUri));
                    } else {
                        bitmap = BitmapFactory.decodeFile(selectImagePath);
                    }

                    picture.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 拍照
     */
    private void takePicture(){
        currentFileName = System.currentTimeMillis() + "";
        File outputImageFile = new File(rootPath,currentFileName + ".jpg");
        if(!outputImageFile.exists()){
            try {
                if(!outputImageFile.createNewFile()){
                    Log.e("XXX","拍照文件时，创建文件失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(Build.VERSION.SDK_INT < 24){
            Log.e("XXX","android版本小于24");
            takeImageUri = Uri.fromFile(outputImageFile);
        }else{
            Log.e("XXX","android版本大于等于24");
            //Android 7.0系统开始 使用本地真实的Uri路径不安全,使用FileProvider封装共享Uri
            //参数二:fileprovider绝对路径 com.dyb.testcamerademo：项目包名
            takeImageUri = FileProvider.getUriForFile(TakePictureActivity.this,"liwei.com",outputImageFile);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, takeImageUri);
        startActivityForResult(intent,TakePic);
    }

    /**
     * 选择相册
     */
    private void selectPicture(){
        isClickCamera = false;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, SelectPic);
    }

    /**
     * 裁剪图片
     */
    private void cutPicture(Uri uri){
        isClickCamera = true;

        File file = new File(rootPath,currentFileName + "_cut.jpg");
        try {
            if(file.exists()){
                if(!file.delete()){
                    Log.e("XXX","裁剪图片时，删除文件失败");
                }
            }
            if(file.createNewFile()){
                Log.e("XXX","裁剪图片时，创建文件失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cutImageUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //android版本号大于等于24（7.0）
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        //裁剪图片的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("crop", "true");//可裁剪
        // 裁剪后输出图片的尺寸大小
        //intent.putExtra("outputX", 400);
        //intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);//支持缩放
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cutImageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
        intent.putExtra("noFaceDetection", true);//取消人脸识别
        startActivityForResult(intent, CutPic);
    }

    /**
     * 操作4.4以上版本的选择相册
     */
    private void handleImageOnKitKat(Intent data) {
        selectImagePath = null;
        Uri uri = data.getData();
        Log.d("XXX", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                selectImagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                selectImagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            selectImagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            selectImagePath = uri.getPath();
        }
        cutPicture(uri);
    }

    /**
     * 操作4.4以下版本的选择相册
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        selectImagePath = getImagePath(uri, null);
        cutPicture(uri);
    }

    /**
     * 获取图片路径
     */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}