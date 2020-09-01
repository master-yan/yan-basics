package com.yan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yan.entity.BookPO;

/**
 * dao - 书本管理
 * @author fenrir
 *
 */
public interface BookDao {

	String COLUMNS = "id,book_name,author,total,price,introduction,creation_time,updated_time";
	
	/**
	 * 查询书本
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@Select(
		"SELECT " + COLUMNS + " FROM book ORDER BY added_time DESC LIMIT #{pageNumber},#{pageSize}"
	)
	List<BookPO> queryAll(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);
	
	/**
	 * 查询总条数
	 * @return
	 */
	@Select("SELECT count(*) FROM book")
	long queryAllTotal();
	
	/**
	 * 新增
	 * @param po
	 * @return
	 */
	@Insert(
		"INSERT INTO book (" + COLUMNS + 
		") VALUES (#{id},#{bookName},#{author},#{total},#{price},#{introduction},#{creationTime},#{updatedTime})"
	)
	int add(BookPO po);
	
	/**
	 * 批量新增
	 * @param pos
	 * @return
	 */
	@Insert(
		"<script>" + 
			"INSERT INTO book (" + COLUMNS + ") VALUES " + 
			"<foreach collection = \"pos\" item = \"item\" index = \"index\" separator = \",\">" + 
				"(#{item.id},#{item.bookName},#{item.author},#{item.total},#{item.price},#{item.introduction},#{item.creationTime})" + 
			"</foreach>" + 
		"</script>"
	)
	int batchAdd(@Param("pos") List<BookPO> pos);
	
	/**
	 * 修改
	 * @param po
	 * @return
	 */
	@Update(
		"UPDATE book SET book_name = #{bookName}, author = #{author}, total = #{total}, " + 
		"price = #{price}, introduction = #{introduction} WHERE id = #{id}"
	)
	int update(BookPO po);
	
	/**
	 * 批量修改
	 * @param pos
	 * @return
	 */
	@Update(
		"<script>" + 
			"UPDATE book SET " + 
			"<trim prefix = \"book_name = CASE id\" suffix = \"END,\">" + 
				"<foreach collection = \"pos\" item = \"item\" index = \"index\">" + 
					"<if test = \"item.bookName != null\"> WHEN #{item.id} THEN #{item.bookName}</if>" + 
				"</foreach>" + 
			"</trim>" + 
			"<trim prefix = \"author = CASE id\" suffix = \"END,\">" + 
				"<foreach collection = \"pos\" item = \"item\" index = \"index\">" + 
					"<if test = \"item.author != null\"> WHEN #{item.id} THEN #{item.author}</if>" + 
				"</foreach>" + 
			"</trim>" + 
			"<trim prefix = \"total = CASE id\" suffix = \"END,\">" + 
				"<foreach collection = \"pos\" item = \"item\" index = \"index\">" + 
					"<if test = \"item.total != null\"> WHEN #{item.id} THEN #{item.total}</if>" + 
				"</foreach>" + 
			"</trim>" + 
			"<trim prefix = \"price = CASE id\" suffix = \"END,\">" + 
				"<foreach collection = \"pos\" item = \"item\" index = \"index\">" + 
					"<if test = \"item.price != null\"> WHEN #{item.id} THEN #{item.price}</if>" + 
				"</foreach>" + 
			"</trim>" + 
			"<trim prefix = \"introduction = CASE id\" suffix = \"END,\">" + 
				"<foreach collection = \"pos\" item = \"item\" index = \"index\">" + 
					"<if test = \"item.introduction != null\"> WHEN #{item.id} THEN #{item.introduction}</if>" + 
				"</foreach>" + 
			"</trim>" + 
			"WHERE id IN" + 
			"<foreach collection = \"pos\" item = \"item\" index = \"no\" open = \"(\" separator = \",\" close = \")\">" + 
				"#{item.id}" + 
			"</foreach>" + 
		"</script>"
	)
	int batchUpdate(@Param("pos") List<BookPO> pos);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM book WHERE id = #{id}")
	int deleteById(@Param("id") Integer id);
	
}