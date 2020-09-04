package com.yan.sql;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import com.yan.entity.CupPO;

/**
 * sql - 杯子管理
 * @author master-yan
 *
 */
public class CupSql {
	
	public static final String COLUMNS = "id,cup_name,total,price,introduction,creation_time,updated_time";
	
	/**
	 * 查询书本
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public String queryAll(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize) {
		String sql = new StringBuffer("SELECT ")
			.append(COLUMNS)
			.append(" FROM test_cup LIMIT ")
            .append(pageNumber)
            .append(",")
            .append(pageSize)
            .toString();

        return sql;
	}
	
	/**
	 * 查询总条数
	 * @return
	 */
	public String queryAllTotal() {
		return "SELECT count(*) FROM test_cup";
	}
	
	/**
	 * 新增
	 * @param po
	 * @return
	 */
	public String add(@Param("po") CupPO po) {
		String sql = new StringBuffer("INSERT INTO test_cup (").append(COLUMNS).append(") values (")
			.append("'").append(po.getId()).append("',")
	        .append("'").append(po.getCupName()).append("',")
	        .append("'").append(po.getTotal()).append("',")
	        .append("'").append(po.getPrice()).append("',")
	        .append("'").append(po.getIntroduction()).append("',")
	        .append("'").append(po.getCreationTime()).append("',")
	        .append("'").append(po.getUpdatedTime()).append("')")
	        .toString();
		
		return sql;
	}

	/**
	 * 批量新增
	 * @param pos
	 * @return
	 */
	public String batchAdd(@Param("pos") List<CupPO> pos) {
		StringBuffer sql = new StringBuffer("INSERT INTO test_cup (").append(COLUMNS).append(") values ");
		
		int size = pos.size();
		
		for (int i = 0; i < size; i++) {
			CupPO po = pos.get(i);
			sql.append("(")
				.append("'").append(po.getId()).append("',")
		        .append("'").append(po.getCupName()).append("',")
		        .append("'").append(po.getTotal()).append("',")
		        .append("'").append(po.getPrice()).append("',")
		        .append("'").append(po.getIntroduction()).append("',")
		        .append("'").append(po.getCreationTime()).append("',")
		        .append("'").append(po.getUpdatedTime()).append("')");
		}
		
		sql.deleteCharAt(sql.length() - 1);
		
		return sql.toString();
	}
	
	/**
	 * 修改
	 * @param po
	 * @return
	 */
	public String update(@Param("po") CupPO po) {
		String sql = new StringBuffer("UPDATE test_cup SET")
	        .append(" cup_name = '").append(po.getCupName()).append("',")
	        .append(" total = ").append(po.getTotal()).append(",")
	        .append(" price = ").append(po.getPrice()).append(",")
	        .append(" introduction = '").append(po.getIntroduction()).append("',")
	        .append(" WHERE id = ").append(po.getId())
	        .toString();
		
		return sql;
	}
	
	/**
	 * 批量修改
	 * @param pos
	 * @return
	 */
	public String batchUpdate(@Param("pos") List<CupPO> pos) {
		StringBuffer sql = new StringBuffer("UPDATE test_cup SET");
		
		int size = pos.size();
		
		sql.append(" cup_name = CASE ");
		for (int i = 0; i < size; i++) {
			if (!StringUtils.isEmpty(pos.get(i).getCupName())) {
				sql.append("WHEN id = ").append(pos.get(i).getId()).append(" THEN '").append(pos.get(i).getCupName()).append("' ");
			}
		}
		sql.append("END,");
		
		sql.append(" total = CASE ");
		for (int i = 0; i < size; i++) {
			if (null != pos.get(i).getTotal()) {
				sql.append("WHEN id = ").append(pos.get(i).getId()).append(" THEN ").append(pos.get(i).getTotal()).append(" ");
			}
		}
		sql.append("END,");
		
		sql.append(" price = CASE ");
		for (int i = 0; i < size; i++) {
			if (null != pos.get(i).getPrice()) {
				sql.append("WHEN id = ").append(pos.get(i).getId()).append(" THEN ").append(pos.get(i).getPrice()).append(" ");
			}
		}
		sql.append("END,");
		
		sql.append(" introduction = CASE ");
		for (int i = 0; i < size; i++) {
			if (!StringUtils.isEmpty(pos.get(i).getIntroduction())) {
				sql.append("WHEN id = ").append(pos.get(i).getId()).append(" THEN '").append(pos.get(i).getIntroduction()).append("' ");
			}
		}
		sql.append("END,");
		
		sql.append("WHERE id IN (");
		for (int i = 0; i < size; i++) {
			sql.append(pos.get(i).getId()).append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
			
		return sql.toString();
	}
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public String deleteById(@Param("id") Integer id) {
		return new StringBuffer("DELETE FROM test_cup WHERE id = ").append(id).toString();
	}
	
}