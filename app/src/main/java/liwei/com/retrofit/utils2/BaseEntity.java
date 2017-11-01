package liwei.com.retrofit.utils2;

import java.io.Serializable;

public class BaseEntity<E> implements Serializable {
    private int code;
    private String message;
    private E data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}