package liwei.com.other.webview.bean;

/**
 * native传给js实体Bean
 */
public class ResponseJs {

    private int statu;
    private String message;

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
