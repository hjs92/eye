package com.yunyangit.eye.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.yunyangit.eye.annotation.LogAnnotation;
import com.yunyangit.eye.dao.HistoricalResultsDao;
import com.yunyangit.eye.model.HistoricalResults;
import com.yunyangit.eye.page.table.PageTableHandler;
import com.yunyangit.eye.page.table.PageTableRequest;
import com.yunyangit.eye.page.table.PageTableResponse;
import com.yunyangit.eye.page.table.PageTableHandler.CountHandler;
import com.yunyangit.eye.page.table.PageTableHandler.ListHandler;
import com.yunyangit.eye.utils.FileUtil;
import com.yunyangit.eye.utils.UserUtil;

/**
 * 智能分析相关接口
 *
 */
@Api(tags = "智能分析")
@RestController
@RequestMapping("/api/AI")
public class AIController {
	
	@Autowired
	private HistoricalResultsDao historicalResultsDao;
	
	
	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Value("${files.path}")
	private String filesPath;

	@LogAnnotation
	@PostMapping("/uploadfile_service")
	@ApiOperation(value = "上传多病种图片")
	public void uploadfile_service(MultipartFile[] file) throws IOException{
		// 文件保存
		String[] fullPath = new String[file.length];
		for(int i = 0;i < file.length;i++){
	        String fileOrigName = file[i].getOriginalFilename();
			if (!fileOrigName.contains(".")) {
				throw new IllegalArgumentException("缺少后缀名");
			}
			String md5 = FileUtil.fileMd5(file[i].getInputStream());
			fileOrigName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
			String pathname = FileUtil.getPath() + md5 + fileOrigName;
			fullPath[i] = filesPath + pathname;
			FileUtil.saveFile(file[i], fullPath[i]);
			log.debug("上传多病种图片{}", fullPath[i]);
		}
		// 请求第三方接口发送多张图片
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
		builder.addFormDataPart("username","test");
		builder.addFormDataPart("password","jsiec");
		builder.addFormDataPart("multi_images","1");
		for(int i = 0;i < file.length;i++){
			builder.addFormDataPart("input_image_file",fullPath[i],
				    RequestBody.create(MediaType.parse("application/octet-stream"),
				    new File(fullPath[i])));
		}
		RequestBody body = builder.build();
		Request request = new Request.Builder()
		  .url("http://10.12.249.23:8786/uploadfile_service")
		  .method("POST", body)
		  .addHeader("Cookie", "sessionid=lfzzq8ioi0241aei0inc7ewvayfa3mkf")
		  .build();
		Response response = client.newCall(request).execute();
		// 解析JSON字符串
		JSONObject jsonObject = JSONObject.parseObject(response.body().string());
	    String pid = jsonObject.getString("pid");
	    String files = jsonObject.getString("files");
	    // 封装数据
	    HistoricalResults historicalResults = new HistoricalResults();
	    historicalResults.setPid(pid);
	    historicalResults.setFiles(files);
	    historicalResults.setUserId(UserUtil.getLoginUser().getId());
	    historicalResults.setType(0L);
	    // 入库
	    historicalResultsDao.save(historicalResults);
	}

	@PostMapping("/diagnose_service")
	@ApiOperation(value = "获取诊断结果")
	public String diagnose_service(String str_uuid, String lang) throws IOException{
		
		String content = historicalResultsDao.getDiagnoseResult(str_uuid, lang);
		if (!(content == null || content.equals(""))){
			return content;
		}
		
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
				  .addFormDataPart("username","test")
				  .addFormDataPart("password","jsiec")
				  .addFormDataPart("showcam","1")
				  .addFormDataPart("str_uuid",str_uuid)
				  .addFormDataPart("lang",lang)
				  .build();
				Request request = new Request.Builder()
				  .url("http://10.12.249.23:8786/diagnose_service")
				  .method("POST", body)
				  .addHeader("Cookie", "sessionid=lfzzq8ioi0241aei0inc7ewvayfa3mkf")
				  .build();
				Response response = client.newCall(request).execute();
				content = response.body().string();
				historicalResultsDao.addDiagnoseResult(str_uuid, lang, content);
		return content;
	}
	
	@LogAnnotation
	@PostMapping("/preprocess")
	@ApiOperation(value = "上传多张图片")
	public void preprocess(MultipartFile[] file) throws IOException{
		String[] fullPath = new String[file.length];
		for(int i = 0;i < file.length;i++){
	        String fileOrigName = file[i].getOriginalFilename();
			if (!fileOrigName.contains(".")) {
				throw new IllegalArgumentException("缺少后缀名");
			}
			String md5 = FileUtil.fileMd5(file[i].getInputStream());
			fileOrigName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
			String pathname = FileUtil.getPath() + md5 + fileOrigName;
			fullPath[i] = filesPath + pathname;
			FileUtil.saveFile(file[i], fullPath[i]);
			log.debug("上传多张图片{}", fullPath[i]);
		}
		
	    OkHttpClient client = new OkHttpClient().newBuilder()
  			  .build();
  			MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
  			builder.addFormDataPart("username","test");
  			builder.addFormDataPart("password","jsiec");
  			for(int i = 0;i < file.length;i++){
	  			builder .addFormDataPart("image_file_list",fullPath[i],
	  			    RequestBody.create(MediaType.parse("application/octet-stream"),
	  			    new File(fullPath[i])));
  			}
  			RequestBody body = builder.build();
  			Request request = new Request.Builder()
  			  .url("http://10.12.249.23:5890/preprocess")
  			  .method("POST", body)
  			  .addHeader("Cookie", "sessionid=qevuqv5kp2l1ija49kf737mgvjeah3ds")
  			  .build();
  			Response response = client.newCall(request).execute();
  			
  			// 解析JSON字符串
  			JSONObject jsonObject = JSONObject.parseObject(response.body().string());
  		    String pid = jsonObject.getString("pid");
  		    String files = jsonObject.getString("files");
  		    // 封装数据
  		    HistoricalResults historicalResults = new HistoricalResults();
  		    historicalResults.setPid(pid);
  		    historicalResults.setFiles(files);
  		    historicalResults.setUserId(UserUtil.getLoginUser().getId());
  		    historicalResults.setType(1L);
  		    // 入库
  		    historicalResultsDao.save(historicalResults);
	}

	
	@PostMapping("/predict")
	@ApiOperation(value = "获取ROP详细信息")
	public String predict(String str_uuid,String lang) throws IOException{
		
		String content = historicalResultsDao.getDiagnoseResult(str_uuid, lang);
		if (!(content == null || content.equals(""))){
			return content;
		}
		
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
				  .addFormDataPart("str_uuid",str_uuid)
				  .addFormDataPart("lang",lang)
				  .build();
				Request request = new Request.Builder()
				  .url("http://10.12.249.23:5890/predict")
				  .method("POST", body)
				  .addHeader("Cookie", "sessionid=lfzzq8ioi0241aei0inc7ewvayfa3mkf")
				  .build();
				Response response = client.newCall(request).execute();
				
				content = response.body().string();
				historicalResultsDao.addDiagnoseResult(str_uuid, lang, content);
		return content;
	}
	
	@GetMapping("/getHistoricalResults")
	@ApiOperation(value = "获取当前用户历史分析报告")
	public PageTableResponse getHistoricalResults(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				Map<String, Object> map = request.getParams();
				map.put("userId", UserUtil.getLoginUser().getId());
				return historicalResultsDao.count(map);
			}
		}, new ListHandler() {

			@Override
			public List<HistoricalResults> list(PageTableRequest request) {
				Map<String, Object> map = request.getParams();
				map.put("userId", UserUtil.getLoginUser().getId());
				List<HistoricalResults> list = historicalResultsDao.list(map, request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}
	
}
