package liwei.com.other.webview.bean;

import java.util.List;

/**
 * Js返回弹出提示框bean
 */
public class OpenActivityViewHandle {
    /**dialog显示时间（如果输入了这个参数，则提示框风格变为短暂显示的提示视图，下面的参数只传入title）*/
    private float displayTime;
    /**提示框标题*/
    private String title;
    /**提示框内容*/
    private String message;
    /**是否需要输入框（如果需要，值显示一个）*/
    private boolean isTextFiled;
    /**提示框按钮数组*/
    private List<String> actionArr;


    public float getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(float displayTime) {
        this.displayTime = displayTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isTextFiled() {
        return isTextFiled;
    }

    public void setTextFiled(boolean textFiled) {
        isTextFiled = textFiled;
    }

    public List<String> getActionArr() {
        return actionArr;
    }

    public void setActionArr(List<String> actionArr) {
        this.actionArr = actionArr;
    }
}