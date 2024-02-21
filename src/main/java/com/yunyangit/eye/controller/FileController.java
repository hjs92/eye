package com.yunyangit.eye.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yunyangit.eye.annotation.LogAnnotation;
import com.yunyangit.eye.dao.FileInfoDao;
import com.yunyangit.eye.dao.RoleDao;
import com.yunyangit.eye.model.FileInfo;
import com.yunyangit.eye.model.Role;
import com.yunyangit.eye.page.table.PageTableHandler;
import com.yunyangit.eye.page.table.PageTableHandler.CountHandler;
import com.yunyangit.eye.page.table.PageTableHandler.ListHandler;
import com.yunyangit.eye.page.table.PageTableRequest;
import com.yunyangit.eye.page.table.PageTableResponse;
import com.yunyangit.eye.service.FileService;
import com.yunyangit.eye.utils.UserUtil;

/**
 * 文件上传下载相关接口
 *
 */
@Api(tags = "文件上传下载")
@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired
	private FileService fileService;
	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Autowired
	private RoleDao roleDao;

	@LogAnnotation
	@PostMapping("/uploadFile")
	@ApiOperation(value = "文件上传")
	public FileInfo uploadFile(MultipartFile file,Long type, Long fileId, Long type1) throws IOException {
		return fileService.save(file, type, fileId, type1);
	}
	
	@GetMapping
	@ApiOperation(value = "查询上传记录")
	public PageTableResponse listFiles(PageTableRequest request) {
		Map<String, Object> map = request.getParams();
		if (map.get("type").equals("1")){// 1：医联体 2：后台用户0：普通用户
			map.put("username", UserUtil.getLoginUser().getUsername());
		}else if (map.get("type").equals("2") || map.get("type").equals("0")){ // 后台用户
			// 获取当前登录用户ID
			Long uId = UserUtil.getLoginUser().getId();
			// 获取
			List<Role> rolelist = roleDao.listByUserId(uId);
			if (rolelist == null){
				throw new IllegalArgumentException("不可操作");
			}else{
				List<Long> roleIds = rolelist.stream().map(Role::getId).collect(Collectors.toList());
				
				boolean result = roleIds.contains(2L);
				if (!result){
					throw new IllegalArgumentException("不可操作");
				}
			}
			
		}
		
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				
				return fileInfoDao.count(map);
			}
		}, new ListHandler() {

			@Override
			public List<FileInfo> list(PageTableRequest request) {
				List<FileInfo> list = fileInfoDao.list(map, request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}
	
	@LogAnnotation
	@GetMapping(value = "/downloadFile")
	@ApiOperation(value = "文件下载")
    public void downloadFile(String fileName, final HttpServletResponse response)
            throws Exception {
 
		fileService.get(fileName, response);
    }


}









