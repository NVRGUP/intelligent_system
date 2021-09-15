package com.swjt.cattle.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BeanUtils 继承org.springframework.beans.BeanUtils
 * 
 * @author Dai
 *
 */
public final class BeanUtils extends org.springframework.beans.BeanUtils {

	public static void copyPropertiesIgnoreNullValue(Object source, Object target, String... ignoreProperties)
			throws BeansException {
		
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
		for (PropertyDescriptor targetPd : targetPds) {
			if (null != targetPd.getWriteMethod() && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (null != sourcePd && null != sourcePd.getReadMethod()
						&& !StringUtils.isEmpty(sourcePd.getPropertyType())
						&& sourcePd.getPropertyType().isAssignableFrom(targetPd.getPropertyType())) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);

						// 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
						if (null != value) {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}

		}
	}

	public static void copyPropertiesIgnoreNullValue(Object source, Object target) throws BeansException {
		copyPropertiesIgnoreNullValue(source, target, (String[]) null);
	}

	public static void copyPropertiesSpecialMapAndIgnoreNull(Object source, Object target, String... mapProperties)
			throws BeansException {
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		Map<String, String> specialMap = new HashMap<String, String>();
		for (int i = 0; mapProperties != null && i < mapProperties.length; i++) {
			String[] item = mapProperties[i].split("[|]");
			if (item.length == 2) {
				specialMap.put(item[1], item[0]);
			}
		}
		for (PropertyDescriptor targetPd : targetPds) {
			if (null != targetPd.getWriteMethod()) {
				PropertyDescriptor sourcePd = null;
				String sourceMap = specialMap.get(targetPd.getName());
				if (specialMap.keySet().size() == 0 || StringUtils.isEmpty(sourceMap)) {
					sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				} else {
					sourcePd = getPropertyDescriptor(source.getClass(), sourceMap);
				}

				if (null != sourcePd && null != sourcePd.getReadMethod()
						&& !StringUtils.isEmpty(sourcePd.getPropertyType())
						&& sourcePd.getPropertyType().isAssignableFrom(targetPd.getPropertyType())) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);

						// 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
						if (null != value) {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}

		}
	}

	public static <T> T copyPropertiesBean(Object source, T target) {
		copyPropertiesIgnoreNullValue(source, target);
		return target;
	}
}
