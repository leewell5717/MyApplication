package liwei.com.other.pay;

import com.google.gson.InstanceCreator;

import java.io.Serializable;
import java.lang.reflect.Type;

public class BaseBean<T> implements Serializable,InstanceCreator<BaseBean> {

    private static final long serialVersionUID = 5511912617499518401L;

    private int error;
    private String message;
    private String msg;
    private T content;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public BaseBean createInstance(Type type) {
        return new BaseBean();
    }
}
