package com.yan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yan.entity.SweetsPO;

/**
 * dao - 糖果管理
 * @author master-yan
 *
 */
public interface RedisCacheDao {

	String COLUMNS = "id,sweet_name,brand,total,price,creation_time,updated_time";
	
	/**
	 * 查询书本
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@Select(
		"SELECT " + COLUMNS + " FROM test_sweet ORDER BY creation_time DESC LIMIT #{pageNumber},#{pageSize}"
	)
	List<SweetsPO> queryAll(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);
	
	/**
	 * 查询总条数
	 * @return
	 */
	@Select("SELECT count(*) FROM test_sweet")
	long queryAllTotal();
	
	/**
	 * 新增
	 * @param po
	 * @return
	 */
	@Insert(
		"INSERT INTO test_sweet (" + COLUMNS + 
		") VALUES (#{id},#{sweetName},#{brand},#{total},#{price},#{creationTime},#{updatedTime})"
	)
	int add(SweetsPO po);
	
	
	/**
	 * 修改
	 * @param po
	 * @return
	 */
	@Update(
		"UPDATE test_sweet SET book_name = #{sweetName}, brand = #{brand}, total = #{total}, " + 
		"price = #{price} WHERE id = #{id}"
	)
	int update(SweetsPO po);
	
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM test_sweet WHERE id = #{id}")
	int deleteById(@Param("id") Integer id);
	
}