package cn.linkedcare.exception.advice;

import cn.linkedcare.entity.CommonResultMap;
import cn.linkedcare.enumeration.HttpCode;
import cn.linkedcare.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * Created by Benji on 2018/5/9.
 */

@ResponseBody
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public CommonResultMap handleException(RuntimeException e) {
        return CommonResultMap.builder(HttpCode.ERROR).msg(e.getMessage()).build();
    }

    @ExceptionHandler(BusinessException.class)
    public CommonResultMap handleBusinessException(BusinessException e) {
        return CommonResultMap.builder(HttpCode.BUSINESS_EXCEPTION).msg(e.getMessage()).build();
    }
}
