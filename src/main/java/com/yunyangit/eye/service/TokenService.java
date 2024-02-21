package com.yunyangit.eye.service;

import com.yunyangit.eye.dto.LoginUser;
import com.yunyangit.eye.dto.Token;


/**
 * Token管理器<br>
 * 可存储到redis或者数据库<br>
 * 具体可看实现类<br>
 * 默认基于redis，实现类为 com.yunyangit.eye.service.impl.TokenServiceJWTImpl<br>
 * 如要换成数据库存储，将TokenServiceImpl类上的注解@Primary挪到com.yunyangit.eye.service.impl.TokenServiceDbImpl
 */
public interface TokenService {

	Token saveToken(LoginUser loginUser);

	void refresh(LoginUser loginUser);

	LoginUser getLoginUser(String token);

	boolean deleteToken(String token);

}
