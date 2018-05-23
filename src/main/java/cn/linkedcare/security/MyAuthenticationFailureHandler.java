package cn.linkedcare.security;

import cn.linkedcare.enumeration.HttpCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Benji on 2018/5/23.
 */
public class MyAuthenticationFailureHandler extends AbstractHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        super.handle(httpServletResponse, HttpCode.AUTHENTICATE_FAILURED, e.getMessage());
    }
}
