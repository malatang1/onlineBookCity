package com.tarena.dang.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tarena.dang.dao.ProductDao;
import com.tarena.dang.entity.Book;
import com.tarena.dang.entity.Product;
import com.tarena.dang.util.DButil;

public class JDBCProductDao implements ProductDao {

	public List<Product> findNew() throws SQLException {
		String sql="select * from d_product where has_deleted=0 order by add_time desc limit 0,8";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		List<Product> list=new ArrayList<Product>();
		while(rs.next()){
			Product pro=new Product();
			pro.setId(rs.getInt("id"));
			pro.setProduct_name(rs.getString("product_name"));
			pro.setFixed_price(rs.getDouble("fixed_price"));
			pro.setDang_price(rs.getDouble("dang_price"));
			pro.setAdd_time(rs.getLong("add_time"));
			pro.setDescription(rs.getString("description"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHas_deleted(rs.getInt("has_deleted"));
			pro.setProduct_pic(rs.getString("product_pic"));
			list.add(pro);
		}
		return list;
	}

	public List<Product> findByCatId(int cid,int page,int size) throws SQLException {
		String sql="select * from d_category_product dcp " +
				"join d_product dp on(dcp.product_id=dp.id) " +
				"join d_book db on(db.id=dp.id) " +
				"where cat_id=? limit ?,?";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, cid);
		pst.setInt(2, (page-1)*size);
		pst.setInt(3, size);
		ResultSet rs=pst.executeQuery();
		List<Product> list=new ArrayList<Product>();
		while(rs.next()){
			Book pro=new Book();
			pro.setId(rs.getInt("id"));
			pro.setProduct_name(rs.getString("product_name"));
			pro.setFixed_price(rs.getDouble("fixed_price"));
			pro.setDang_price(rs.getDouble("dang_price"));
			pro.setAdd_time(rs.getLong("add_time"));
			pro.setDescription(rs.getString("description"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHas_deleted(rs.getInt("has_deleted"));
			pro.setProduct_pic(rs.getString("product_pic"));
			pro.setAuthor(rs.getString("author"));
			pro.setPublishing(rs.getString("publishing"));
			pro.setPrint_time(rs.getLong("print_time"));
			pro.setPublish_time(rs.getLong("publish_time"));
			pro.setWord_number(rs.getString("word_number"));
			pro.setWhich_edtion(rs.getString("which_edtion"));
			pro.setTotal_page(rs.getString("total_page"));
			pro.setPrint_number(rs.getString("print_number"));
			pro.setIsbn(rs.getString("isbn"));
			pro.setAuthor_summary(rs.getString("author_summary"));	
			pro.setCatalogue(rs.getString("catalogue"));
			list.add(pro);
		}
		return list;
	}
	public int getMaxpage(int cid,int size) throws SQLException{
		String sql="select count(*) from d_category_product dcp " +
		"join d_product dp on(dcp.product_id=dp.id) " +
		"join d_book db on(db.id=dp.id) " +
		"where cat_id=?";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, cid);
		ResultSet rs = pst.executeQuery();
		rs.next();
		int total = rs.getInt(1);
		int maxPage = 1;
		if(total % size == 0){
			maxPage = total/size;
		}else{
			maxPage = total/size + 1;
		}
		return maxPage;
	}

	public List<Product> findRecommend() throws SQLException {
		String sql="select * from d_product dp join d_book db on(dp.id=db.id) where has_deleted=0 and dp.id in(?,?)";
		Random r=new Random();
		int r1=r.nextInt(24)+1,r2=r.nextInt(24)+1;
		while(r1==r2){
			r2=r.nextInt(24)+1;
		}
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, r1);
		pst.setInt(2, r2);
		ResultSet rs=pst.executeQuery();
		List<Product> list=new ArrayList<Product>();
		while(rs.next()){
			Book pro=new Book();
			pro.setId(rs.getInt("id"));
			pro.setProduct_name(rs.getString("product_name"));
			pro.setFixed_price(rs.getDouble("fixed_price"));
			pro.setDang_price(rs.getDouble("dang_price"));
			pro.setAdd_time(rs.getLong("add_time"));
			pro.setDescription(rs.getString("description"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHas_deleted(rs.getInt("has_deleted"));
			pro.setProduct_pic(rs.getString("product_pic"));
			pro.setAuthor(rs.getString("author"));
			pro.setPublishing(rs.getString("publishing"));
			pro.setPrint_time(rs.getLong("print_time"));
			pro.setPublish_time(rs.getLong("publish_time"));
			pro.setWord_number(rs.getString("word_number"));
			pro.setWhich_edtion(rs.getString("which_edtion"));
			pro.setTotal_page(rs.getString("total_page"));
			pro.setPrint_number(rs.getString("print_number"));
			pro.setIsbn(rs.getString("isbn"));
			pro.setAuthor_summary(rs.getString("author_summary"));	
			pro.setCatalogue(rs.getString("catalogue"));
			list.add(pro);
		}
		return list;
	}

	public List<Product> findHot() throws SQLException {
		String sql="select d_product.* from d_item join d_product on(d_item.product_id=d_product.id) group by product_id order by sum(product_num) desc limit 0,8";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		List<Product> list=new ArrayList<Product>();
		while(rs.next()){
			Product pro=new Product();
			pro.setId(rs.getInt("id"));
			pro.setProduct_name(rs.getString("product_name"));
			pro.setFixed_price(rs.getDouble("fixed_price"));
			pro.setDang_price(rs.getDouble("dang_price"));
			pro.setAdd_time(rs.getLong("add_time"));
			pro.setDescription(rs.getString("description"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHas_deleted(rs.getInt("has_deleted"));
			pro.setProduct_pic(rs.getString("product_pic"));
			list.add(pro);
		}
		return list;
	}

	public Product findById(int id) throws SQLException {
		String sql="select * from d_product where id=?";
		PreparedStatement pst=DButil.getConnection().prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs=pst.executeQuery();
		Product pro=null;
		while(rs.next()){
			pro=new Product();
			pro.setId(rs.getInt("id"));
			pro.setProduct_name(rs.getString("product_name"));
			pro.setFixed_price(rs.getDouble("fixed_price"));
			pro.setDang_price(rs.getDouble("dang_price"));
			pro.setAdd_time(rs.getLong("add_time"));
			pro.setDescription(rs.getString("description"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHas_deleted(rs.getInt("has_deleted"));
			pro.setProduct_pic(rs.getString("product_pic"));
			
		}
		return pro;
	}

}
