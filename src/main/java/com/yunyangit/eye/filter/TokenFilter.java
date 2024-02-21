package com.yunyangit.eye.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yunyangit.eye.dao.RoleDao;
import com.yunyangit.eye.dto.LoginUser;
import com.yunyangit.eye.dto.RequestWrapper;
import com.yunyangit.eye.service.TokenService;
import com.yunyangit.eye.utils.AesEncryptUtil;



/**
 * Token过滤器
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

	public static final String TOKEN_KEY = "token";

	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserDetailsService userDetailsService;
	private static final Long MINUTES_10 = 10 * 60 * 1000L;
	@Autowired
	private RoleDao roleDao;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("request："+request.getRequestURI());
		// 如果请求路径是为login,进行过滤对参数parameter内容解密，放入request.parameter中会定期坻崿
        if (request.getRequestURI().indexOf("/login") != -1 || request.getRequestURI().indexOf("/api/login") != -1) {
        	if (!request.getMethod().equals("OPTIONS")){
        		// 1.获取加密串,进行解密
            	String password = request.getParameter("password");
            	// 对登录密码进行解密
            	try {
    				password = AesEncryptUtil.desEncrypt(password).trim();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
                //2.解密出加密串，我和前台约定的是JSON,获取到JSON我将其转换为map，这里我直接用手动封装map代替
                Map<String, Object> paramter = new HashMap<String, Object>(16);
                paramter.put("password", password);
                RequestWrapper wrapper = new RequestWrapper(request, paramter);
                filterChain.doFilter(wrapper, response);
        	}else{
        		filterChain.doFilter(request, response);
        	}
            return;
        }
        
        
		String token = getToken(request);
		if (StringUtils.isNotBlank(token)) {
			LoginUser loginUser = tokenService.getLoginUser(token);
			if (loginUser != null) {
				loginUser = checkLoginTime(loginUser);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser,
						null, loginUser.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);	
			}
		}
		
		
		filterChain.doFilter(request, response);
	}

	/**
	 * 校验时间<br>
	 * 过期时间与当前时间对比，临近过期10分钟内的话，自动刷新缓存
	 * 
	 * @param loginUser
	 * @return
	 */
	private LoginUser checkLoginTime(LoginUser loginUser) {
		long expireTime = loginUser.getExpireTime();
		long currentTime = System.currentTimeMillis();
		if (expireTime - currentTime <= MINUTES_10) {
			String token = loginUser.getToken();
			loginUser = (LoginUser) userDetailsService.loadUserByUsername(loginUser.getUsername());
			loginUser.setToken(token);
			tokenService.refresh(loginUser);
		}
		
		return loginUser;
	}

	/**
	 * 根据参数或者header获取token
	 * 
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		String token = request.getParameter(TOKEN_KEY);
		if (StringUtils.isBlank(token)) {
			token = request.getHeader(TOKEN_KEY);
		}

		return token;
	}

}
