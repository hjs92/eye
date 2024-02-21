package com.yunyangit.eye.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.yunyangit.eye.dao.UserDao;
import com.yunyangit.eye.dto.LoginUser;
import com.yunyangit.eye.dto.ResponseInfo;
import com.yunyangit.eye.dto.Token;
import com.yunyangit.eye.filter.TokenFilter;
import com.yunyangit.eye.model.SysUser;
import com.yunyangit.eye.service.TokenService;
import com.yunyangit.eye.utils.ResponseUtil;


/**
 * spring security处理器
 */
@Configuration
public class SecurityHandlerConfig {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserDao userDao;
	
	private static final Long MINUTES_2 = 10 * 60 * 1000L;

	/**
	 * 登陆成功，返回Token
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				LoginUser loginUser = (LoginUser) authentication.getPrincipal();
				
				SysUser sysUser = new SysUser();
				sysUser.setId(loginUser.getId());
				sysUser.setAllowAt( new Date() );
				sysUser.setErrorNum(0);
				userDao.update(sysUser);
				
				Token token = tokenService.saveToken(loginUser);
				ResponseUtil.responseJson(response, HttpStatus.OK.value(), token);
			}
		};
	}

	/**
	 * 登陆失败
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				String msg = null;
				if (exception instanceof BadCredentialsException) {
					//msg = "密码错误";
					msg = "信息有误";
				} else {
					msg = exception.getMessage();
				}
				SysUser sysUser = userDao.getUser( request.getParameter("username") );
				Integer errorNum = sysUser.getErrorNum();
				
				Long currentTime = new Date().getTime(); //当前时间
				Long allowTime = sysUser.getAllowAt().getTime(); //允许登陆时间
				
				if (currentTime > allowTime){
					if(errorNum < 2){
						if( (currentTime - allowTime) <= MINUTES_2){
							sysUser.setErrorNum(errorNum + 1);
							sysUser.setAllowAt(new Date());
						}else {
							sysUser.setErrorNum(1);
							sysUser.setAllowAt(new Date());
	                    }
					}else{
						sysUser.setAllowAt( new Date(currentTime + MINUTES_2) );
						sysUser.setErrorNum(0);
					}
					userDao.update(sysUser);
				}
				
				
				ResponseInfo info = new ResponseInfo(HttpStatus.UNAUTHORIZED.value() + "", msg);
				
				ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
			}
		};

	}

	/**
	 * 未登录，返回401
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				ResponseInfo info = new ResponseInfo(HttpStatus.UNAUTHORIZED.value() + "", "请先登录");
				//ResponseInfo info = new ResponseInfo(HttpStatus.UNAUTHORIZED.value() + "", "用户名不存在");
				ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
			}
		};
	}

	/**
	 * 退出处理
	 * 
	 * @return
	 */
	@Bean
	public LogoutSuccessHandler logoutSussHandler() {
		return new LogoutSuccessHandler() {

			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				ResponseInfo info = new ResponseInfo(HttpStatus.OK.value() + "", "退出成功");

				String token = TokenFilter.getToken(request);
				tokenService.deleteToken(token);

				ResponseUtil.responseJson(response, HttpStatus.OK.value(), info);
			}
		};

	}

}
