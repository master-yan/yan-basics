package com.yan.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yan.entity.BasicListResponseGenericityVO;
import com.yan.entity.BasicResponseGenericityVO;

/**
 * 工具
 * @author fenrir
 *
 */
public class Utils <T> {
	
	/**
	 * 基于org.springframework.beans.BeanUtils封装的list复制对象属性
	 * @param c 目标list泛型
	 * @param sourceList 源数据list
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T,B> List<B> copyListProperties(Class<B> c, List<T> sourceList) {
		// 转换模型
		List<B> targetList = new ArrayList<>();
		
		int size = sourceList.size();
		
		for (int i = 0; i < size; i++) {
			// 用反射创建对象
			B target = BeanUtils.instantiateClass(c);
			
			// 复制名字相同的成员
			BeanUtils.copyProperties(sourceList.get(i), target);
			targetList.add(target);
		}
		
		return targetList;
	}

	/**
	 * 获取分页响应对象
	 * @param <T>
	 * @param pageNumber
	 * @param pageSize
	 * @param total
	 * @param list
	 * @return
	 */
	public static <T> BasicResponseGenericityVO<BasicListResponseGenericityVO<T>> getBasicResponseGenericityVO(
			Integer pageNumber, Integer pageSize, Long total) {
		BasicResponseGenericityVO<BasicListResponseGenericityVO<T>> response = 
				new BasicResponseGenericityVO<BasicListResponseGenericityVO<T>>(
					200, "OK", 
					getBasicListResponseGenericityVO(pageNumber, pageSize, total)
				);
		
		return response;
	}
	
	/**
	 * 获取分页对象
	 * @param <T>
	 * @param pageNumber
	 * @param pageSize
	 * @param total
	 * @param list
	 * @return
	 */
	public static <T> BasicListResponseGenericityVO<T> getBasicListResponseGenericityVO(
			Integer pageNumber, Integer pageSize, Long total) {
		BasicListResponseGenericityVO<T> listResponse = new BasicListResponseGenericityVO<T>();
		listResponse.setPageNumber(pageNumber);
		listResponse.setPageSize(pageSize);
		listResponse.setTotal(total);
		
		// 计算当前页下标
		if (pageNumber <= 1) {
			listResponse.setPageIndex(0);
			listResponse.setPageNumber(1);
		} else {			
			listResponse.setPageIndex((pageNumber - 1) * pageSize);
		}
		
		// 计算总页数
		if (total <= pageSize) {
			listResponse.setTotalPage(1);
		} else {
			listResponse.setTotalPage(Integer.valueOf("" + ((total + pageSize - 1) / pageSize)));
		}
		
		return listResponse;
	}

}