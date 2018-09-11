package cn.linkedcare.filter;

import cn.linkedcare.service.RedisService;
import cn.linkedcare.util.SpringContextUtil;
import cn.linkedcare.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by Benji on 2018/7/25.
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private static RedisService redisService;

    static {
        redisService = SpringContextUtil.getBean(RedisService.class);
    }

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
            /**没有token，继续之后的认证流程*/
            chain.doFilter(request, response);
            return;
        }
        String username = parseUsername(token);
        if (StringUtils.isNotBlank(username)) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()));
        }
        super.doFilterInternal(request, response, chain);
    }

    /**
     * 从Token中解析出用户名，并判断用户是否已登录
     *
     * @param token
     * @return
     */
    private String parseUsername(String token) {
        try {
            token = token.replace(TokenUtil.prefix, "");
            String username = TokenUtil.parseToken(token).getSubject();
            if (StringUtils.isNotBlank(username)) {
                String tokenInRedis = redisService.get(username);
                if (tokenInRedis != null && tokenInRedis.equals(token)) {
                    /**用户没有退出登录*/
                    return username;
                }
            }
        } catch (Exception e) {
            logger.error("parseUsername Exception:" + e.toString());
        }
        return null;
    }
}
