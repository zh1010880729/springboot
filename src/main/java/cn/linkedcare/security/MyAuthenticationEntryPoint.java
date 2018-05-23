package cn.linkedcare.security;

import cn.linkedcare.enumeration.HttpCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Benji on 2018/5/23.
 */
public class MyAuthenticationEntryPoint extends AbstractHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        super.handle(httpServletResponse, HttpCode.AUTHENTICATE_FAILURED, e.getMessage());
    }
}
