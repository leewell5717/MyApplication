package liwei.com.other.webview.bean;

/**
 * 网络请求Js返回实体Bean
 */
public class RequestThoughNative {

    /**请求接口名*/
    private String requestUrlStr;
    /**请求参数*/
    private String paraDic;
//    private Map<String,String> paraDic;

    /**网络请求名*/
    private String requestTitle;
    /**请求的时候是否禁用UI交互(默认不用为false)*/
    private boolean isUiEnable;
    /**请求是否显示HUD(默认不使用为false)*/
    private boolean isUseHUD;

    public String getRequestUrlStr() {
        return requestUrlStr;
    }

    public void setRequestUrlStr(String requestUrlStr) {
        this.requestUrlStr = requestUrlStr;
    }

    public String getParaDic() {
        return paraDic;
    }

    public void setParaDic(String paraDic) {
        this.paraDic = paraDic;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public boolean isUiEnable() {
        return isUiEnable;
    }

    public void setUiEnable(boolean uiEnable) {
        isUiEnable = uiEnable;
    }

    public boolean isUseHUD() {
        return isUseHUD;
    }

    public void setUseHUD(boolean useHUD) {
        isUseHUD = useHUD;
    }
}