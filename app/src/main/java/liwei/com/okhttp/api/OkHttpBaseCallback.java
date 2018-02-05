package liwei.com.okhttp.api;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/**
 * @author liwei
 * @desc okHttp回调基类
 */
public abstract class OkHttpBaseCallback<T> {
	/**
	 * type用于方便JSON的解析
	 */
	public Type mType;

	/**
	 * 把type转换成对应的类，这里不用看明白也行。
	 */
	static Type getSuperclassTypeParameter(Class<?> subclass) {
		Type superclass = subclass.getGenericSuperclass();
		if (superclass instanceof Class) {
			throw new RuntimeException("Missingtype parameter.");
		}
		ParameterizedType parameterized = (ParameterizedType) superclass;
		return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
	}

	/**
	 * 构造的时候获得type的class
	 */
	public OkHttpBaseCallback() {
		mType = getSuperclassTypeParameter(getClass());
	}

	/**
	 * 请求之前调用
	 */
	public abstract void onRequestBefore();

	/**
	 * 请求失败调用（网络问题）
	 */
	public abstract void onFailure(Request request, Exception e);

	/**
	 * 请求成功而且没有错误的时候调用
	 */
	public abstract void onSuccess(Response response, T t);

	/**
	 * 请求成功但是有错误的时候调用，例如Gson解析错误等
	 */
	public abstract void onError(Response response, int errorCode, Exception e);
}