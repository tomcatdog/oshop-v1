package me.xiaoy.mvc.manager.goods.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import me.xiaoy.core.constants.ResourceDefvalue;
import me.xiaoy.core.utils.UtilValidate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ResourceUploadManager {
	private Logger logger=LoggerFactory.getLogger(ResourceUploadManager.class);
	public  String upload(String filepath,MultipartFile file,String fileNameUUID,String fileExt)throws IOException{
		filepath=getFilePath(filepath);
		logger.info("==============上传文件目录："+filepath);
		SaveFileFromInputStream(file.getInputStream(),filepath,fileNameUUID+fileExt);
		return fileNameUUID+fileExt;
		
	}
	public String getFilePath(String filepath){
		String classpath=this.getClass().getClassLoader().getResource("/").getPath();
		String rootpath=classpath.substring(0, classpath.lastIndexOf("WEB-INF/classes/"))+ResourceDefvalue.filepath+"/";
		if(UtilValidate.isEmpty(filepath)){
			filepath=rootpath;
		}else{
			if(filepath.startsWith("/")){
			    filepath=rootpath+filepath.substring(1, filepath.length());
			}else{
				filepath=rootpath+filepath;
			}
		}
		return filepath;
	}
	/**保存文件
     * @param stream
     * @param path
     * @param filename
     * @throws IOException
     */
    private void SaveFileFromInputStream(InputStream stream,String path,String filename) throws IOException
    {     
    	File file=new File(path);
    	if(!file.exists()){
    		file.mkdirs();
    	}
        FileOutputStream fs=new FileOutputStream( path + "/"+ filename);
        byte[] buffer =new byte[1024*1024];
        int bytesum = 0;
        int byteread = 0; 
        while ((byteread=stream.read(buffer))!=-1)
        {
           bytesum+=byteread;
           fs.write(buffer,0,byteread);
           fs.flush();
        } 
        fs.close();
        stream.close();      
    }       
}
