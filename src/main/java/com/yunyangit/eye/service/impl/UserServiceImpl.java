package com.yunyangit.eye.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yunyangit.eye.dao.MailDao;
import com.yunyangit.eye.dao.UserDao;
import com.yunyangit.eye.dto.UserDto;
import com.yunyangit.eye.model.Mail;
import com.yunyangit.eye.model.SysUser;
import com.yunyangit.eye.service.SendMailSevice;
import com.yunyangit.eye.service.UserService;
import com.yunyangit.eye.utils.AesEncryptUtil;


@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	
	@Value("${spring.mail.subject}")
	private String subject;
	
	@Value("${spring.mail.pass}")
	private String pass;
	
	@Value("${spring.mail.no-pass}")
	private String no_pass;
	

	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private MailDao mailDao;
	
	@Autowired
	private SendMailSevice sendMailSevice;

	@Override
	@Transactional
	public SysUser saveUser(UserDto userDto) { 
		List<Long> roleIds = userDto.getRoleIds();
		roleIds.remove(0L);
		SysUser user = userDto;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//user.setStatus(Status.VALID);
		userDao.save(user);
		saveUserRoles(user.getId(), roleIds);

		log.debug("新增用户{}", user.getUsername());
		return user;
	}

	private void saveUserRoles(Long userId, List<Long> roleIds) {
		if (roleIds != null) {
			userDao.deleteUserRole(userId);
			if (!CollectionUtils.isEmpty(roleIds)) {
				userDao.saveUserRoles(userId, roleIds);
			}
		}
	}

	@Override
	public SysUser getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public void changePassword(String username, String oldPassword, String newPassword) {
		SysUser u = userDao.getUser(username);
		if (u == null) {
			throw new IllegalArgumentException("用户不存在");
		}
		try {
			oldPassword = AesEncryptUtil.desEncrypt(oldPassword).trim();
			newPassword = AesEncryptUtil.desEncrypt(newPassword).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!passwordEncoder.matches(oldPassword, u.getPassword())) {
			throw new IllegalArgumentException("旧密码错误");
		}

		userDao.changePassword(u.getId(), passwordEncoder.encode(newPassword));
		
		userDao.changeEnable(u.getId());
		
		log.debug("修改{}的密码", username);
	}

	@Override
	@Transactional
	public SysUser updateUser(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userDao.update(userDto);
		saveUserRoles(userDto.getId(), userDto.getRoleIds());
		return userDto;
	}
	
	@Override
	@Transactional
	public void changeUserstatus(Long id, Long status, String comment){
		if(subject == null || subject.equals("")){
			subject = "审核结果通知";
		}
		if(pass == null || pass.equals("")){
			pass = "审核通过！";
		}
		if(no_pass == null || no_pass.equals("")){
			no_pass = "审核不通过！";
		}
		// 获取用户状态
		SysUser sysUser = userDao.getById(id);
		if (sysUser != null){
			int i = userDao.changeUserstatus(status,id);
			if (i > 0){
				if (status == 1){ // 审核通过
					Mail mail = mailDao.getById("pass");
					StringBuilder str = new StringBuilder();
					str.append(mail.getBeforeRealname()+sysUser.getRealName() +mail.getAfterRealname());
					str.append(mail.getBeforeUsername()+sysUser.getUsername() +mail.getAfterUsername());
					str.append(mail.getBeforeOrganization()+sysUser.getOrganization()+ mail.getAfterOrganization());
					str.append(mail.getBeforeJob()+sysUser.getJob()+mail.getAfterJob());
					
//					StringBuilder str = new StringBuilder();
//					str.append("尊敬的 "+sysUser.getRealName() +":<br />");
//					str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;汕头国际眼科中心“全生命周期眼健康智能化眼病诊疗预警平台”欢迎您的到来！恭喜您在该平台（ai.jsiec.org）注册的账号已经审批通过！您可以进一步登录平台测试和使用，也欢迎提出宝贵意见。<br />");
//					str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢您对平台的支持！<br /><br /><br />");
//					str.append("您注册的登录账号是："+sysUser.getUsername()+"<br />");
//					str.append("您注册的单位组织是："+ sysUser.getOrganization()+"<br />");
//					str.append("您注册的职业是："+sysUser.getJob()+"<br /><br /><br /><br />");
//					str.append("汕头国际眼科中心人工智能团队<br />");
//					str.append("咨询电话：0754-88393587<br />");
//					str.append("联系人：林老师<br />");
//					str.append("地址： 广东省汕头市金平区东厦北路汕头国际眼科中心大数据实验室<br />");
					try {
						sendMailSevice.sendMail(sysUser.getUsername(), subject, str.toString());
						log.debug("用户Id:{}已通过", id);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (status == 2){ // 审核不通过
					Mail mail = mailDao.getById("nopass");
					StringBuilder str = new StringBuilder();
					str.append(mail.getBeforeRealname()+sysUser.getRealName() +mail.getAfterRealname());
					str.append("审批意见："+ comment +"<br /><br />");
					str.append(mail.getBeforeUsername()+sysUser.getUsername() +mail.getAfterUsername());
					str.append(mail.getBeforeOrganization()+sysUser.getOrganization()+ mail.getAfterOrganization());
					str.append(mail.getBeforeJob()+sysUser.getJob()+mail.getAfterJob());
					
					
//					StringBuilder str = new StringBuilder();
//					str.append("尊敬的"+sysUser.getRealName() +":<br />");
//					str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;汕头国际眼科中心“全生命周期眼健康智能化眼病诊疗预警平台”欢迎您的到来！您注册时提供的信息（如下所示）未能在该平台（ai.jsiec.org）通过审批，请根据审批意见做适当的修改。如果疑问，请进一步咨询工作人员。<br />");
//					str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢您对平台的支持！<br /><br />");
//					str.append("审批意见："+ comment +"<br /><br />");
//					str.append("您注册的登录账号是："+sysUser.getUsername()+"<br />");
//					str.append("您注册的单位组织是："+ sysUser.getOrganization()+"<br />");
//					str.append("您注册的职业是："+sysUser.getJob()+"<br /><br /><br /><br />");
//					str.append("汕头国际眼科中心人工智能团队<br />");
//					str.append("咨询电话：0754-88393587<br />");
//					str.append("联系人：林老师<br />");
//					str.append("地址： 广东省汕头市金平区东厦北路汕头国际眼科中心大数据实验室<br />");
					try {
						sendMailSevice.sendMail(sysUser.getUsername(), subject, str.toString());
						log.debug("用户Id:{}不通过", id);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	@Transactional
	public void deleteUser(Long id) {
		userDao.deleteRoleUser(id);
		userDao.delete(id);
		log.debug("删除角色id:{}", id);
	}

	@Override
	@Transactional
	public void saveUserPermission(Long userId, List<Long> permissionIds) {
		if (permissionIds != null) {
			userDao.deleteUserPermission(userId);
			if (!CollectionUtils.isEmpty(permissionIds)) {
				userDao.saveUserPermission(userId, permissionIds);
			}
		}
		
	}

	
}
