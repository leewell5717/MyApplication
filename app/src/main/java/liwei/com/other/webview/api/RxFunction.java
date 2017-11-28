package liwei.com.other.webview.api;

import android.support.annotation.NonNull;

import io.reactivex.functions.Function;

/**
 * 错误信息统一处理,异常会在RxObserver的onError中catch
 */
public class RxFunction<T> implements Function<HttpResult<T>, T> {

    @Override
    public T apply(@NonNull HttpResult<T> httpResult) throws Exception {

        int retCode = httpResult.getRetCode();
        if (retCode != 200) {
            switch (retCode) {
                case 21001:
                    throw new ApiException("查询的日期格式错误,格式:yyyy-MM-dd");
              //  case 2111:
              //      throw ........
            }
        }
        return httpResult.getResult();
    }
}
