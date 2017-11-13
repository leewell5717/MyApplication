package liwei.com.other.webview.bean;

/**
 * 关闭窗口bean
 */
public class PageCloseType {

    /**
     * "message":"关闭窗口","pageCloseType":2,"popPath":""
     */

    /**关闭类型：1.pop、2.dismiss 3.close*/
    private int pageCloseType;
    /**退出路径 (只能在pop页面调用,不填写则返回至上一页，rootPage返回根界面)*/
    private String popPath;
    /**备注信息*/
    private String message;

    public int getPageCloseType() {
        return pageCloseType;
    }

    public void setPageCloseType(int pageCloseType) {
        this.pageCloseType = pageCloseType;
    }

    public String getPopPath() {
        return popPath;
    }

    public void setPopPath(String popPath) {
        this.popPath = popPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}