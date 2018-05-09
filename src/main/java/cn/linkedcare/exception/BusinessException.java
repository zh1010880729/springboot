package cn.linkedcare.exception;

/**
 * 自定义异常
 * Created by Benji on 2018/5/9.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }
}
