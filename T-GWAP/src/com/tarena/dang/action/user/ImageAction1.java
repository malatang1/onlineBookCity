package com.tarena.dang.action.user;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.tarena.dang.action.BaseAction;
import com.tarena.dang.util.ImageUtil;

public class ImageAction1 extends BaseAction{
	private InputStream imageStream;
	
	public String execute(){
		Map<String,BufferedImage> map = 
						ImageUtil.getImage();
		//获取key,图片上的字符
		String code = 
			map.keySet().iterator().next();
		//将key存入到session,用于以后检查用户输入正确性
		session.put("code", code);
		//获取验证码图片
		BufferedImage image = map.get(code);
		//2.将图片对象赋值给imageStream属性
		ByteArrayOutputStream bos = 
				new ByteArrayOutputStream();
		JPEGImageEncoder encoder = 
				JPEGCodec.createJPEGEncoder(bos);
		try {
			encoder.encode(image);//将image压缩成jpeg格式
			//将图片压缩结果从bos中取出,给imageStream
			byte[] bytes = bos.toByteArray();
			imageStream = new ByteArrayInputStream(bytes);
			return "success";//result(stream)
		} catch (Exception e) {
			e.printStackTrace();
			return "error";//可以跳转到error.jsp
		}
	}

	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	
	
}
