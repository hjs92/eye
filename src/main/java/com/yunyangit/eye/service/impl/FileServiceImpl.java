package com.yunyangit.eye.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yunyangit.eye.dao.FileInfoDao;
import com.yunyangit.eye.model.FileInfo;
import com.yunyangit.eye.service.FileService;
import com.yunyangit.eye.utils.FileUtil;
import com.yunyangit.eye.utils.UserUtil;
@Service
public class FileServiceImpl implements FileService{

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Value("${files.path}")
	private String filesPath;
	@Autowired
	private FileInfoDao fileInfoDao;

	@Override
	public FileInfo save(MultipartFile file, Long type, Long fileId, Long type1) throws IOException {
		String fileOrigName = file.getOriginalFilename();
		if (!fileOrigName.contains(".")) {
			throw new IllegalArgumentException("缺少后缀名");
		}
		// 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
		fileOrigName = FileUtil.makeFileName(fileOrigName);
		
		//得到文件名的hashCode的值，得到的就是fileOrigName这个字符串对象在内存中的地址
		int hashcode = fileOrigName.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		//构造新的保存目录
		String dir = FileUtil.getPath() + dir1 + "/" + dir2 + "/";
		
		String pathname = dir + fileOrigName;
		String fullPath = filesPath + pathname;
		FileUtil.saveFile(file, fullPath);
		FileInfo fileInfo = new FileInfo();
		if (type == 1)
		{
			fileInfo.setState(0L);
			fileInfo.setDownloadLink1(pathname);
			fileInfo.setUser(UserUtil.getLoginUser());
			fileInfo.setType(type1);
			fileInfoDao.save(fileInfo);
		}else{
			fileInfo.setId(fileId);
			fileInfo.setState(1L);
			fileInfo.setDownloadLink2(pathname);
			fileInfoDao.update(fileInfo);
		}
		log.debug("上传文件{}", fullPath);

		return fileInfo;

	}
	
	@Override
	public void get(String fileName, final HttpServletResponse response) throws Exception{
		String fullPath = filesPath + fileName;
        File file = new File(fullPath);
        fileName = file.getName();
        //如果文件不存在
        if(!file.exists()){
        	throw new IllegalArgumentException("文件已不存在！");
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_")+1);
        // 清空缓冲区，状态码和响应头(headers)
        response.reset();
        // 设置ContentType，响应内容为二进制数据流，编码为utf-8，此处设定的编码是文件内容的编码
        response.setContentType("application/octet-stream;charset=utf-8");
        // 以（Content-Disposition: attachment; filename="filename.jpg"）格式设定默认文件名，设定utf编码，此处的编码是文件名的编码，使能正确显示中文文件名
        response.setHeader("Content-Disposition", "attachment;fileName="+ realname +";filename*=utf-8''"+URLEncoder.encode(realname,"utf-8").replaceAll("\\+", "%20"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(file);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
        log.debug("下载文件{}", fullPath);
	}
}
