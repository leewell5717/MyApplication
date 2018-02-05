package liwei.com.other.pay;

public class PayWayBean {

    private String payway;
    private String remark;
    private String paytype;
    private SignInfo ext;

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public SignInfo getExt() {
        return ext;
    }

    public void setExt(SignInfo ext) {
        this.ext = ext;
    }

    private class SignInfo{
        private String appid;
        private String mchid;
        private String key;
        private String appsecret;
        private String notifyUrl;

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

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getNotifyUrl() {
            return notifyUrl;
        }

        public void setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
        }
    }

}