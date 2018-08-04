package cn.linkedcare.filter;

import cn.linkedcare.entity.CommonResultMap;
import cn.linkedcare.enumeration.HttpCode;
import cn.linkedcare.service.RedisService;
import cn.linkedcare.util.SpringContextUtil;
import cn.linkedcare.util.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by Benji on 2018/5/9.
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private static RedisService redisService;

    static {
        redisService = SpringContextUtil.getBean(RedisService.class);
    }


    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 登录成功之后生成Token
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        JSONObject jsonObject = new JSONObject();
        String username = ((User) authResult.getPrincipal()).getUsername();
        String token = TokenUtil.generateToken(username);
        jsonObject.put("Authorization", "Bearer " + token);
        redisService.set(username, token, TokenUtil.EXPIRE_TIME);
        CommonResultMap resultMap = CommonResultMap.builder(HttpCode.OK).msg("login success").data(jsonObject.toJSONString()).build();
        response.getWriter().write(JSONObject.toJSONString(resultMap));
    }
}
