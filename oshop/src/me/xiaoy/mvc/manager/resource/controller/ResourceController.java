package me.xiaoy.mvc.manager.resource.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import me.xiaoy.core.base.BaseController;
import me.xiaoy.core.constants.ResourceDefvalue;
@Controller
@RequestMapping(value = "/resource")
public class ResourceController extends BaseController {
	private Logger logger=LoggerFactory.getLogger(ResourceController.class);
	@Resource
	private ResourceManager resourceManager;
	/**
	 * 上传文件 返回json对象
	 * @return
	 */
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public ModelAndView upload(){
		logger.debug("==========开始上传文件");
//		   // String filepath="d:/"+ResourceDefvalue.filepath;
//	        System.out.println(filepath);//输出存放上传文件所到的路径  
//	        File uploadPath = new File(filepath);  
//	        // 检查文件夹是否存在 不存在 创建一个  
//	        if (!uploadPath.exists()) {
//	        	//TODO 建议修改为 mkdirs
//	            uploadPath.mkdirs();  
//	        }  
	        String filename ="";// 文件名
	        String localfilename="";//本地存放文件名
	        String fileExt = "";
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			List<MultipartFile> files=multipartRequest.getFiles("file");
			int success=0;
			int fail=0;
				for(MultipartFile file:files){
					if(file.getSize()>0){      
						filename=file.getOriginalFilename();
						if (filename.lastIndexOf(".") != -1) {
							fileExt = filename.substring(filename.lastIndexOf("."), filename.length());
						}
						localfilename=UUID.randomUUID().toString();
						try {
							resourceManager.upload("/abc", file, localfilename, fileExt);
							success+=1;
						} catch (IOException e) {
						logger.error("==============上传文件出错 文件名：{}{}",filename,e);
						fail+=1;
						}
					}
				}
				logger.debug("上传文件成功{}失败{}",success,fail);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("success",success);
			map.put("fail",fail);
			super.MakeJson(true, map, false);//false 对应response.setContentType("text/html; charset=UTF-8");
		return null;
	}
	@RequestMapping(value="testjtemplates",method=RequestMethod.POST)
	public ModelAndView testjtemplates(){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("value","1");
		map.put("name","选项1");
		list.add(map);
		Map<String,Object> map2=new HashMap<String, Object>();
		map2.put("value","2");
		map2.put("name","选项2");
		list.add(map2);
		super.outJson(true, list);
		return null;
	}

	
}
