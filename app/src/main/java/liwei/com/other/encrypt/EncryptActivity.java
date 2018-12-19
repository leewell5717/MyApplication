package liwei.com.other.encrypt;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PrivateKey;
import java.security.PublicKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.utils.Utils;
import liwei.com.other.encrypt.utils.Base64Utils;
import liwei.com.other.encrypt.utils.RSAUtils;

public class EncryptActivity extends Activity {

    @BindView(R.id.encrypt_plaintext)
    public EditText encryptPlaintext;
    @BindView(R.id.encrypt_btn)
    public Button encryptBtn;
    @BindView(R.id.ciphertext)
    public TextView ciphertext;
    @BindView(R.id.decrypt_btn)
    public Button decryptBtn;
    @BindView(R.id.decrypt_plaintext)
    public TextView decryptPlaintext;


    /* 密钥内容 base64 code */
    private static final String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfRTdcPIH10gT9f31rQuIInLwe"
            + "\r" + "7fl2dtEJ93gTmjE9c2H+kLVENWgECiJVQ5sonQNfwToMKdO0b3Olf4pgBKeLThra" + "\r"
            + "z/L3nYJYlbqjHC3jTjUnZc0luumpXGsox62+PuSGBlfb8zJO6hix4GV/vhyQVCpG" + "\r"
            + "9aYqgE7zyTRZYX9byQIDAQAB" + "\r";
    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ9FN1w8gfXSBP1/"
            + "\r" + "fWtC4gicvB7t+XZ20Qn3eBOaMT1zYf6QtUQ1aAQKIlVDmyidA1/BOgwp07Rvc6V/" + "\r"
            + "imAEp4tOGtrP8vedgliVuqMcLeNONSdlzSW66alcayjHrb4+5IYGV9vzMk7qGLHg" + "\r"
            + "ZX++HJBUKkb1piqATvPJNFlhf1vJAgMBAAECgYA736xhG0oL3EkN9yhx8zG/5RP/" + "\r"
            + "WJzoQOByq7pTPCr4m/Ch30qVerJAmoKvpPumN+h1zdEBk5PHiAJkm96sG/PTndEf" + "\r"
            + "kZrAJ2hwSBqptcABYk6ED70gRTQ1S53tyQXIOSjRBcugY/21qeswS3nMyq3xDEPK" + "\r"
            + "XpdyKPeaTyuK86AEkQJBAM1M7p1lfzEKjNw17SDMLnca/8pBcA0EEcyvtaQpRvaL" + "\r"
            + "n61eQQnnPdpvHamkRBcOvgCAkfwa1uboru0QdXii/gUCQQDGmkP+KJPX9JVCrbRt" + "\r"
            + "7wKyIemyNM+J6y1ZBZ2bVCf9jacCQaSkIWnIR1S9UM+1CFE30So2CA0CfCDmQy+y" + "\r"
            + "7A31AkB8cGFB7j+GTkrLP7SX6KtRboAU7E0q1oijdO24r3xf/Imw4Cy0AAIx4KAu" + "\r"
            + "L29GOp1YWJYkJXCVTfyZnRxXHxSxAkEAvO0zkSv4uI8rDmtAIPQllF8+eRBT/deD" + "\r"
            + "JBR7ga/k+wctwK/Bd4Fxp9xzeETP0l8/I+IOTagK+Dos8d8oGQUFoQJBAI4Nwpfo" + "\r"
            + "MFaLJXGY9ok45wXrcqkJgM+SN6i8hQeujXESVHYatAIL/1DgLi+u46EFD69fw0w+" + "\r" + "c7o0HLlMsYPAzJw="
            + "\r";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.encrypt_btn, R.id.decrypt_btn})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.encrypt_btn: //加密
                String source = encryptPlaintext.getText().toString().trim();
                if(TextUtils.isEmpty(source)){
                    Utils.showToastCenter("请输入明文");
                    return;
                }
                try {
                    // 从字符串中得到公钥
                     PublicKey publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
                    // 从文件中得到公钥
//                    InputStream inPublic = getResources().getAssets().open("rsa_public_key.pem");
//                    PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
                    // 加密
                    byte[] encryptByte = RSAUtils.encryptData(source.getBytes(), publicKey);
                    // 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
                    String afterencrypt = Base64Utils.encode(encryptByte);
                    ciphertext.setText(afterencrypt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.decrypt_btn: //解密
                String encryptContent = ciphertext.getText().toString().trim();
                try {
                    // 从字符串中得到私钥
                     PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
                    // 从文件中得到私钥
//                    InputStream inPrivate = getResources().getAssets().open("pkcs8_rsa_private_key.pem");
//                    PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
                    // 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
                    byte[] decryptByte = RSAUtils.decryptData(Base64Utils.decode(encryptContent), privateKey);
                    String decryptStr = new String(decryptByte);
                    decryptPlaintext.setText(decryptStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}