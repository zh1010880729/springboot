package cn.linkedcare.security;

import cn.linkedcare.enumeration.HttpCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Benji on 2018/5/23.
 */
public class MyAccessDeniedHandler extends AbstractHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        super.handle(httpServletResponse, HttpCode.AUTHENTICATE_FAILURED, e.getMessage());
    }
}
