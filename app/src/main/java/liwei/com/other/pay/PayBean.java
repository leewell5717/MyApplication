package liwei.com.other.pay;

public class PayBean {

    //支付宝使用
    private String payParams;

    //微信使用
    private String appid;
    private String mchid;
    private String key;
    private String prepayid;
    private String sign;
    private String noncestr;
    private String packageVal;
    private String timestamp;

    //QQ钱包使用
    private String bargainorId;
    private String sigType;
    private String tokenId;

    public String getPayParams() {
        return payParams;
    }

    public void setPayParams(String payParams) {
        this.payParams = payParams;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageVal() {
        return packageVal;
    }

    public void setPackageVal(String packageVal) {
        this.packageVal = packageVal;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBargainorId() {
        return bargainorId;
    }

    public void setBargainorId(String bargainorId) {
        this.bargainorId = bargainorId;
    }

    public String getSigType() {
        return sigType;
    }

    public void setSigType(String sigType) {
        this.sigType = sigType;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}