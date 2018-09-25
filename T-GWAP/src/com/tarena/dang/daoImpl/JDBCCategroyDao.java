package com.tarena.dang.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.dao.CategoryDao;
import com.tarena.dang.entity.Category;
import com.tarena.dang.util.DButil;

public class JDBCCategroyDao implements CategoryDao {

	public List<Category> findAll() throws SQLException {
		String sql="select * from d_category order by turn";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		List<Category> list=new ArrayList<Category>();
		while(rs.next()){
			Category cat=new Category();
			cat.setId(rs.getInt("id"));
			cat.setName(rs.getString("name"));
			cat.setDescription(rs.getString("description"));
			cat.setEn_name(rs.getString("en_name"));
			cat.setParent_id(rs.getInt("parent_id"));
			cat.setTurn(rs.getInt("turn"));
			list.add(cat);
		}
		return list;
	}

	public List<Category> findByParentId(int pid) throws SQLException {
		String sql="select dc.*,count(dcp.product_id) " +
				"from d_category dc " +
				"left join d_category_product dcp " +
				"on(dc.id=dcp.cat_id) " +
				"where dc.parent_id=? " +
				"group by dc.id order by dc.turn";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, pid);
		ResultSet rs=pst.executeQuery();
		List<Category> list=new ArrayList<Category>();
		while(rs.next()){
			Category cat=new Category();
			cat.setId(rs.getInt("id"));
			cat.setName(rs.getString("name"));
			cat.setDescription(rs.getString("description"));
			cat.setEn_name(rs.getString("en_name"));
			cat.setParent_id(rs.getInt("parent_id"));
			cat.setTurn(rs.getInt("turn"));
			cat.setPnum(rs.getInt("count(dcp.product_id)"));
			list.add(cat);
		}
		return list;
	}

}
