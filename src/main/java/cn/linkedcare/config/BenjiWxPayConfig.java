package cn.linkedcare.config;

import com.github.wxpay.sdk.WXPayConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * Created by Benji on 2018/9/11.
 */
public class BenjiWxPayConfig implements WXPayConfig {

    private static final Log log = LogFactory.getLog(BenjiWxPayConfig.class);

    private static byte[] certData;

    static {
        String certPath = "证书地址";
        File file = new File(certPath);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            certData = new byte[(int) file.length()];
            inputStream.read(certData);
        } catch (Exception e) {
            log.error("BenjiWxPayConfig 加载时出现异常", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("BenjiWxPayConfig 释放资源时出错", e);
                }
            }
        }
    }

    @Override
    public String getAppID() {
        return null;
    }

    @Override
    public String getMchID() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public InputStream getCertStream() {
        return new ByteArrayInputStream(certData);
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
