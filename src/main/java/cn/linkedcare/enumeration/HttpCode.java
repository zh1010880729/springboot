package cn.linkedcare.enumeration;

/**
 * Created by Benji on 2018/5/8.
 */
public enum HttpCode {
    OK("A0000"),
    ERROR("A0001"),
    AUTHENTICATE_FAILURED("A0002");

    private String status;

    HttpCode(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
