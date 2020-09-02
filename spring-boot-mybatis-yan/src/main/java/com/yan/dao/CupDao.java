package com.yan.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.yan.entity.CupPO;
import com.yan.sql.CupSql;

/**
 * dao - 杯子管理
 * @author master-yan
 *
 */
public interface CupDao {
	
	/**
	 * 查询杯子
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = CupSql.class, method = "queryAll")
	List<CupPO> queryAll(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);
	
	/**
	 * 查询总条数
	 * @return
	 */
	@SelectProvider(type = CupSql.class, method = "queryAllTotal")
	long queryAllTotal();
	
	/**
	 * 新增
	 * @param po
	 * @return
	 */
	@InsertProvider(type = CupSql.class, method = "add")
	int add(@Param("po") CupPO po);
	
	/**
	 * 批量新增
	 * @param pos
	 * @return
	 */
	@InsertProvider(type = CupSql.class, method = "batchAdd")
	int batchAdd(@Param("pos") List<CupPO> pos);
	
	/**
	 * 修改
	 * @param po
	 * @return
	 */
	@UpdateProvider(type = CupSql.class, method = "update")
	int update(@Param("po") CupPO po);
	
	/**
	 * 批量修改
	 * @param pos
	 * @return
	 */
	@UpdateProvider(type = CupSql.class, method = "batchUpdate")
	int batchUpdate(@Param("pos") List<CupPO> pos);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = CupSql.class, method = "deleteById")
	int deleteById(@Param("id") Integer id);
	
}