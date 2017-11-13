package liwei.com.other.webview.bean;

import java.io.Serializable;

/**
 * 打开新窗口bean
 */
public class OpenViewHandle implements Serializable{

    /*"pageOpenType": 2,
            // "newViewType": 1,
            // "currentViewType": 2,
            "isNativeControl": false,
            "pagePath": "http://test.17byh.com/html/alertPage.html",
            "pageClass": "alert",
            "pageTitle": "新开窗口",
            "message": ""
            */

    /*
     * "pageOpenType":2,
       "isNativeControl":false,
       "pagePath":"http://test.17byh.com/html/alertPage.html",
       "pageClass":"alert",
       "pageTitle":"�¿�����",
       "message":""
     */

    /**  页面打开类型：1-push（从右往左） 2-present（从下往上） 3-pop_up*/
    private int pageOpenType;
    /**是否有原生窗口*/
    private boolean isNativeControl;
    /**窗口路径*/
    private String pagePath;
    /**原生窗口类名(native需要判断本地是否存在这个类，不存在按通用窗体处理)*/
    private String pageClass;
    /**窗口名*/
    private String pageTitle;
    /**备注信息*/
    private String message;

    public int getPageOpenType() {
        return pageOpenType;
    }

    public void setPageOpenType(int pageOpenType) {
        this.pageOpenType = pageOpenType;
    }

    public boolean isNativeControl() {
        return isNativeControl;
    }

    public void setNativeControl(boolean nativeControl) {
        isNativeControl = nativeControl;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getPageClass() {
        return pageClass;
    }

    public void setPageClass(String pageClass) {
        this.pageClass = pageClass;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}