package cn.linkedcare.security;

import cn.linkedcare.enumeration.HttpCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Benji on 2018/5/23.
 */
public class MyLogoutSuccessHandler extends AbstractHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        super.handle(httpServletResponse, HttpCode.OK, "logout success");
    }
}
