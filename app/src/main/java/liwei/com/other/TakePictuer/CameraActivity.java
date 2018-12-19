package liwei.com.other.TakePictuer;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import liwei.com.R;
import liwei.com.utils.Utils;

/**
 * @author liwei
 * @Desc 拍照、获取系统相册图片Activity（兼容7.0系统）
 * @Desc 参考博客：http://blog.csdn.net/hansion3333/article/details/78399592
 */
public class CameraActivity extends Activity implements OnClickListener {
	//拍照
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	//选择相册
	private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
	//拍照并裁剪
	private static final int CAPTURE_IMAGE_CUT_ACTIVITY_REQUEST_CODE = 300;
	//选择相册并裁剪
	private static final int PICK_IMAGE_CUT_ACTIVITY_REQUEST_CODE = 400;
	//裁剪结果
	private static final int CUT_PHOTO_RESOULT = 500;

	private String picFileFullName;
	//相机拍照图片保存地址
	private Uri takeImageUri;
	//是否拒绝授权
	private boolean isRefused = false;
	//是否已授权
	private boolean isGranted = false;

	//图片adapter
	private ImageRecyclerAdapter adapter;
	//图片列表
	private List<Bitmap> imgList = new ArrayList<>();

	private ImageView selectImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_picture);
		checkPermission();
		init();
	}

	/**
	 * 权限申请
	 */
	private void checkPermission(){
		RxPermissions permissions = new RxPermissions(this);
		permissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.CAMERA).subscribe(new Consumer<Permission>() {
			@Override
			public void accept(Permission permission) throws Exception {
				if(permission.granted){
					// 用户已经同意该权限
					isRefused = false;
					isGranted = true;
				}else if(permission.shouldShowRequestPermissionRationale){
					// 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
					isRefused = false;
					isGranted = false;
				}else {
					// 用户拒绝了该权限，并且选中『不再询问』
					Toast.makeText(CameraActivity.this,"已拒绝权限，若再次使用，请在设置中开启",Toast.LENGTH_SHORT).show();
					isRefused = true;
					isGranted = false;
				}
			}
		});
	}

	private void init() {
		Button openCamera = (Button) findViewById(R.id.open_camera);
		Button pickImage = (Button) findViewById(R.id.pick_image);
		Button openCameraCut = (Button) findViewById(R.id.open_camera_cut);
		Button pickImageCut = (Button) findViewById(R.id.pick_image_cut);
		Button uploadImageBtn = (Button) findViewById(R.id.upload_image_btn);
		selectImg = (ImageView) findViewById(R.id.select_img);
		RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
		openCamera.setOnClickListener(this);
		pickImage.setOnClickListener(this);
		openCameraCut.setOnClickListener(this);
		pickImageCut.setOnClickListener(this);
		uploadImageBtn.setOnClickListener(this);

		adapter = new ImageRecyclerAdapter(this,imgList);
		LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
		recycler.setLayoutManager(llm);
		recycler.setAdapter(adapter);
	}

	/**
	 * 显示对话框
	 */
	private void showSettingDialog(){
		final Dialog dialog = new Dialog(CameraActivity.this,R.style.MyDialog);
		dialog.setContentView(R.layout.dialog_setting);
		TextView content = (TextView)dialog.findViewById(R.id.content);
		Button confirmBtn = (Button)dialog.findViewById(R.id.confirm_btn);
		Button cancelBtn = (Button)dialog.findViewById(R.id.cancel_btn);
		content.setText("是否跳转到设置页面？");
		confirmBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent intent = new Intent();
				intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
				intent.setData(Uri.fromParts("package", getPackageName(), null));
				startActivity(intent);
				CameraActivity.this.finish();
			}
		});
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				CameraActivity.this.finish();
			}
		});
		dialog.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.open_camera: // 拍照
				if(isGranted && !isRefused){
					takePicture(false);
				}else if(!isGranted && !isRefused){
					checkPermission();
				}else if(!isGranted && isRefused){
					showSettingDialog();
				}
				break;
			case R.id.open_camera_cut: // 拍照裁剪
				if(isGranted && !isRefused){
					takePicture(true);
				}else if(!isGranted && !isRefused){
					checkPermission();
				}else if(!isGranted && isRefused){
					showSettingDialog();
				}
				break;
			case R.id.pick_image: // 选择系统相册
				if(isGranted && !isRefused){
					openAlbum(false);
				}else if(!isGranted && !isRefused){
					checkPermission();
				}else if(!isGranted && isRefused){
					showSettingDialog();
				}
				break;
			case R.id.pick_image_cut: // 选择相册裁剪
				if(isGranted && !isRefused){
					openAlbum(true);
				}else if(!isGranted && !isRefused){
					checkPermission();
				}else if(!isGranted && isRefused){
					showSettingDialog();
				}
				break;
			case R.id.upload_image_btn:
				if(imgList.size() == 0){
					Toast.makeText(CameraActivity.this,"请添加图片",Toast.LENGTH_SHORT).show();
					return;
				}
				//图片转化成String
				List<String> temp = new ArrayList<>();
				for(Bitmap bit : imgList){
					temp.add(Utils.bitmapToString(bit));
				}
				//上传

				break;
		}
	}

	/**
	 * 拍照
	 * @param isCut 是否进行裁剪
	 */
	private void takePicture(boolean isCut) {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			File outDir = Environment.getExternalStoragePublicDirectory("takePic");
			if (!outDir.exists()) {
				outDir.mkdirs();
			}
			File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
			picFileFullName = outFile.getAbsolutePath();

			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			if(Build.VERSION.SDK_INT < 24){
				Log.e("XXX","android版本小于24");
				takeImageUri = Uri.fromFile(outFile);
			}else{
				Log.e("XXX","android版本大于等于24");
				//Android 7.0系统开始 使用本地真实的Uri路径不安全,使用FileProvider封装共享Uri
				//参数二:fileprovider绝对路径 com.dyb.testcamerademo：项目包名
				intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
				takeImageUri = FileProvider.getUriForFile(CameraActivity.this,"liwei.com",outFile);
			}

			intent.putExtra(MediaStore.EXTRA_OUTPUT, takeImageUri);
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			if(isCut){ //进行裁剪
				startActivityForResult(intent, CAPTURE_IMAGE_CUT_ACTIVITY_REQUEST_CODE);
			}else{ //不进行裁剪
				startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
		} else {
			Log.e("XXX","无SD卡");
		}
	}

	/**
	 * 打开相册
	 * @param isCut 是否进行裁剪
	 */
	public void openAlbum(boolean isCut) {
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		if(isCut){
			startActivityForResult(intent, PICK_IMAGE_CUT_ACTIVITY_REQUEST_CODE);
		}else{
			startActivityForResult(intent, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE: // 打开相机
			if (resultCode == RESULT_OK) {
				setImageView(picFileFullName);
			} else if (resultCode == RESULT_CANCELED) { // 用户取消了图像捕获
				Log.e("XXX","取消拍照");
			} else { // 图像捕获失败
				Log.e("XXX","拍照失败");
			}
			break;
		case CAPTURE_IMAGE_CUT_ACTIVITY_REQUEST_CODE: // 打开相机并裁剪
			if (resultCode == RESULT_OK) {
				startCutPhoto(takeImageUri);
			} else if (resultCode == RESULT_CANCELED) { // 用户取消了图像捕获
				Log.e("XXX","取消拍照");
			} else { // 图像捕获失败
				Log.e("XXX","拍照失败");
			}
			break;
		case PICK_IMAGE_ACTIVITY_REQUEST_CODE: // 打开相册
			if (resultCode == RESULT_OK) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
//				imgList.add(BitmapFactory.decodeFile(picturePath));
				adapter.addImage(BitmapFactory.decodeFile(picturePath));
				//展示图片到ImageView上
				selectImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			} else if (resultCode == RESULT_CANCELED) { // 用户取消了图像捕获
				Log.e("XXX","取消选择相册");
			} else { // 图像捕获失败
				Log.e("XXX","取消选择失败");
			}
			break;
		case PICK_IMAGE_CUT_ACTIVITY_REQUEST_CODE: // 打开相册并裁剪
			if (resultCode == RESULT_OK) {
				if (data != null) {
					startCutPhoto(data.getData());
				} else {
					Log.e("XXX","data为null");
				}
			} else if (resultCode == RESULT_CANCELED) { // 用户取消了图像捕获
				Log.e("XXX","取消选择相册");
			} else { // 图像捕获失败
				Log.e("XXX","取消选择失败");
			}
			break;
		case CUT_PHOTO_RESOULT:
			if (resultCode == RESULT_OK) {
				if(data != null){
					Bundle extras = data.getExtras();
					if (extras != null) {
						Bitmap photo = extras.getParcelable("data");
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
//						imgList.add(photo);
						adapter.addImage(photo);
						// 把图片显示在ImageView控件上
						selectImg.setImageBitmap(photo);
					}
				}
			}else if (resultCode == RESULT_CANCELED) { // 用户取消了图像裁剪
				Log.e("XXX","取消裁剪图片");
			} else { // 图像裁剪失败
				Log.e("XXX","裁剪图片失败");
			}
			break;
		}
	}

	private void setImageView(String realPath) {
		Bitmap bmp = BitmapFactory.decodeFile(realPath);
//		imgList.add(bmp);
		adapter.addImage(bmp);
		// 把图片显示在ImageView控件上
		selectImg.setImageBitmap(bmp);
	}

	/**
	 * 裁剪图片
	 */
	public void startCutPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");// 调用Android系统自带的一个图片剪裁页面
		intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");// 可裁剪
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", 250);
		intent.putExtra("outputY", 250);
		intent.putExtra("scale", true);//支持缩放
		intent.putExtra("return-data", true);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//返回图片格式
		intent.putExtra("noFaceDetection", true);//取消人脸识别
		startActivityForResult(intent, CUT_PHOTO_RESOULT);
	}
}