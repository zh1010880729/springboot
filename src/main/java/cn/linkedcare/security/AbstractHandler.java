package cn.linkedcare.security;

import cn.linkedcare.entity.CommonResultMap;
import cn.linkedcare.enumeration.HttpCode;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Benji on 2018/5/23.
 */
public abstract class AbstractHandler {
    protected void handle(HttpServletResponse httpServletResponse, HttpCode code, String message) throws IOException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        CommonResultMap resultMap = CommonResultMap.builder(code).msg(message).build();
        String result = JSON.toJSONString(resultMap);
        httpServletResponse.getWriter().write(result);
        httpServletResponse.getWriter().flush();
    }
}
