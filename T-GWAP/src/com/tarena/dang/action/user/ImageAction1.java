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
		//��ȡkey,ͼƬ�ϵ��ַ�
		String code = 
			map.keySet().iterator().next();
		//��key���뵽session,�����Ժ����û�������ȷ��
		session.put("code", code);
		//��ȡ��֤��ͼƬ
		BufferedImage image = map.get(code);
		//2.��ͼƬ����ֵ��imageStream����
		ByteArrayOutputStream bos = 
				new ByteArrayOutputStream();
		JPEGImageEncoder encoder = 
				JPEGCodec.createJPEGEncoder(bos);
		try {
			encoder.encode(image);//��imageѹ����jpeg��ʽ
			//��ͼƬѹ�������bos��ȡ��,��imageStream
			byte[] bytes = bos.toByteArray();
			imageStream = new ByteArrayInputStream(bytes);
			return "success";//result(stream)
		} catch (Exception e) {
			e.printStackTrace();
			return "error";//������ת��error.jsp
		}
	}

	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	
	
}
