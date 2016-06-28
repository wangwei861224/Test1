package com.hx.api.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class UploadHandle {

	/**
	 * 上传文件/支持多文件上传
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String uploadFile(HttpServletRequest request) throws IllegalStateException, IOException {
		//工程目录地址
		String contextPath = request.getContextPath() +"/upload/";
		
		StringBuffer strPath = new StringBuffer("");
		//创建一个通用的多部分解析器  
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//文件存放路径
		String filePath = request.getSession().getServletContext().getRealPath("upload")+File.separator;
		File tmpFile = new File(filePath);
		if (!tmpFile.exists()) {
			// 创建目录
			tmpFile.mkdir();
		}
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)) {
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			List<MultipartFile> files = multiRequest.getFiles("file");
			if(files != null && files.size() > 0) {
				for(int i = 0; i < files.size(); i++) {
					MultipartFile file = files.get(i);
					//取得上传文件  
	                if(!file.isEmpty()){
	                	
	                    //取得当前上传文件的文件名称  
	                    String fileName = file.getOriginalFilename();
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(!StringUtils.isEmpty(fileName)){
	                        //重命名上传后的文件名  
	                        String newFileName = CommonUtil.getUUIDName(fileName);
	                        //定义上传路径  
	                        String path = filePath + newFileName;
	                        File localFile = new File(path);
	                        file.transferTo(localFile);
	                        
	                        strPath.append(contextPath+newFileName);
	                        if(i != files.size()-1) {
	                        	strPath.append(",");
	                        }
	                    }
	                }
				}
                
			}
		}
		return strPath.toString();
	}
	
	/**
	 * 上传图片，对图片进行压缩处理
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadFileForImg(HttpServletRequest request) throws IllegalStateException, IOException {
		//工程目录地址
		String contextPath = request.getContextPath() +"/upload/";
		
		StringBuffer strPath = new StringBuffer("");
		//创建一个通用的多部分解析器  
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//文件存放路径
		String filePath = request.getSession().getServletContext().getRealPath("upload")+File.separator;
		File tmpFile = new File(filePath);
		if (!tmpFile.exists()) {
			// 创建目录
			tmpFile.mkdir();
		}
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)) {
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			List<MultipartFile> files = multiRequest.getFiles("file");
			if(files != null && files.size() > 0) {
				for(int i = 0; i < files.size(); i++) {
					MultipartFile file = files.get(i);
					//取得上传文件  
	                if(!file.isEmpty()){
	                	
	                    //取得当前上传文件的文件名称  
	                    String fileName = file.getOriginalFilename();
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(!StringUtils.isEmpty(fileName)){
	                        //重命名上传后的文件名  
	                        String newFileName = CommonUtil.getUUIDName(fileName);
	                        //定义上传路径  
	                        String path = filePath + newFileName;
	                        File localFile = new File(path);
	                        file.transferTo(localFile);
	                        
	                        strPath.append(contextPath+newFileName);
	                        if(i != files.size()-1) {
	                        	strPath.append(",");
	                        }
	                        
	                        //对图片进行压缩处理
	                        String newImage =null;
	                        /**对服务器上的临时文件进行处理*/
	            		    Image srcFile = ImageIO.read(new File(filePath + newFileName));
	            		    
	            		    int old_hei = srcFile.getHeight(null);
	            		    int old_wid = srcFile.getWidth(null);
	            		    int size = Math.min(old_hei, old_wid);
            			    size = size > 350 ? 350 : size;
            			    old_hei = old_wid = size;
            			    
            			    /**宽,高设定*/
            			    BufferedImage tag = new BufferedImage(old_wid,old_hei, BufferedImage.TYPE_INT_RGB);
            			    tag.getGraphics().drawImage(srcFile, 0, 0, old_wid,old_hei, null);
            			    /**压缩后的文件名*/
            			    newImage = filePath + newFileName;

            			    /**压缩之后临时存放位置*/
            			    FileOutputStream out = new FileOutputStream(newImage);

            			    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            			    JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            			    /**压缩质量*/
            			    jep.setQuality(100, false);
            			    encoder.encode(tag, jep);
            			    out.close();
	                    }
	                }
				}
                
			}
		}
		return strPath.toString();
	}
}
