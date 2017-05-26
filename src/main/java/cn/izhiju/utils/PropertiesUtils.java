package cn.izhiju.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PropertiesUtils {

	/**
	 * 获取properties文件
	 * @param path
	 * @return
	 */
	public static Properties getProperties(String path){
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			properties.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static List<String> getValues(String path){
		Properties properties = getProperties(path);
		
		List<String> list = new ArrayList<String>();
		
		Collection<Object> collection =properties.values();
		
		for (Object object : collection) {
			String value = (String) object;
			list.add(value);
		}
		return list;
	}
	
	public static List<String> getValues(String filePath, String prefix){
		Properties properties = PropertiesUtils.getProperties(filePath);
		List<String> anonUrlsList = new ArrayList<String>();
		Collection<Object> keys = properties.keySet();
		for (Object object : keys) {
			String key = (String) object;
			if(key.startsWith(prefix)){
				String value = properties.getProperty(key);
				anonUrlsList.add(value);
			}
		}
		return anonUrlsList;
	}
	
	public static void WriteProperties(String filePath, String Key, String Value) throws Exception {
		Properties properties = getProperties(filePath);
		OutputStream out = new FileOutputStream(filePath);
		properties.setProperty(Key, Value);
		properties.store(out, "设置属性值。");
	}
}
