package com.swjt.cattle.exceptions;


import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class SysValidateMessageConfig {

	private static Logger LOGGER = LoggerFactory.getLogger(SysValidateMessageConfig.class);

	private static Properties props;

	private static final String defaultSysValidateMessage = "SysValidateMessage.properties";
	
	public static void init(String sysValidateMessage) {
		try {
			Resource resource = new ClassPathResource(sysValidateMessage);
			if (resource != null) {
				// 资源文件夹中配置了
				EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
				props = PropertiesLoaderUtils.loadProperties(encodedResource);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			;
		}
	}

	public static void init() {

		init(defaultSysValidateMessage);
	}

	public static String getProperty(String key) {
		return props == null ? "" : props.getProperty(key);
	}
}
