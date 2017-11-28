package liwei.com.other.webview.bean;

import java.io.Serializable;

/**
 * js返回设置窗体在移动端展示位置bean
 */
public class ViewLayoutHandle implements Serializable{

    /*
     * "layoutType": 2,
     "centerX":x,
     "centerY":y ,
     "cornerRadius": 23,
     "pageOpenType":1,
     "widthScale	":"0.5",
     "heightScale":"0.88",
     "borderWidth":2,
     "borderColor": "#000",
     "message":"页面初始化"
     */

    /**布局方式，1为坐标布局，2为比例布局*/
    private int layoutType;
    /**x坐标（视图左上角为坐标原点）*/
    private float x;
    /**y坐标*/
    private float y;
    /**圆角*/
    private float cornerRadius;
    /**中心点X坐标 (不使用设为0或不传)*/
    private float centerX;
    /**中心点Y坐标 (不使用设为0或不传)*/
    private float centerY;
    /**视图宽度 （type为1时传入）*/
    private float width;
    /**视图高度 （type为1时传入）*/
    private float height;
    /**视图宽度在屏幕占比 （type为2时传入）*/
    private float widthScale;
    /**视图高度在屏幕占比 （type为2时传入）*/
    private float heightScale;
    /**备注信息*/
    private String message;
    /**页面打开类型*/
    private int pageOpenType;
    /**描边宽度*/
    private float borderWidth;
    /**描边颜色*/
    private String borderColor;

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidthScale() {
        return widthScale;
    }

    public void setWidthScale(float widthScale) {
        this.widthScale = widthScale;
    }

    public float getHeightScale() {
        return heightScale;
    }

    public void setHeightScale(float heightScale) {
        this.heightScale = heightScale;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPageOpenType() {
        return pageOpenType;
    }

    public void setPageOpenType(int pageOpenType) {
        this.pageOpenType = pageOpenType;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }
}