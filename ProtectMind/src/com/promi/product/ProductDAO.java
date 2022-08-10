package com.promi.product;

import java.util.ArrayList;
import java.util.List;

import com.promi.common.DAO;

public class ProductDAO extends DAO {

	private static ProductDAO pd = null;

	private ProductDAO() {

	}

	public static ProductDAO getInstance() {
		return pd == null ? new ProductDAO() : pd;
	}

	public int insertProduct(Product pro) {
		int result = 0;
		try {
			conn();
			String sql = "insert into product(product_name,product_price,product_kind,product_explain) "
					+ "values (?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pro.getProductName());
			pstmt.setInt(2, pro.getProductPrice());
			pstmt.setString(3, pro.getProductKind());
			pstmt.setString(4, pro.getProductExplain());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	// 삭제//
	public int deleteProduck(String name) {
		int result = 0;
		try {
			conn();
			String sql = "delete product where product_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			disconnect();
		}

		return result;
	}

	// total 리스트 + 이름 : 가격 : 종류//
	public List<Product> listProduck() {
		List<Product> list = new ArrayList<>();
		Product pro = null;
		try {
			conn();
			String sql = "select product_name,product_price,product_kind from product";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				pro = new Product();
				pro.setProductName(rs.getString("product_name"));
				pro.setProductPrice(rs.getInt("product_price"));
				pro.setProductKind(rs.getNString("product_kind"));
				list.add(pro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;
	}

	// 리스트 검색 -이름//이름,설명,가격
	public List<Product> listProduckName(String name) {
		List<Product> list = new ArrayList<>();
		Product pro = null;
		try {
			conn();
			String sql = "select *from product where product_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pro = new Product();
				pro.setProductName(rs.getString("product_name"));
				pro.setProductPrice(rs.getInt("product_price"));
				pro.setProductKind(rs.getNString("product_kind"));
				pro.setProductGrade(rs.getInt("product_grade"));
				pro.setProductExplain(rs.getString("product_explain"));
				list.add(pro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;

	}
	
	public Product buyList(String name) {
		
		Product pro = null;
		try {
			conn();
			String sql = "select *from product where product_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pro = new Product();
				pro.setProductName(rs.getString("product_name"));
				pro.setProductPrice(rs.getInt("product_price"));
				pro.setProductKind(rs.getNString("product_kind"));
				pro.setProductGrade(rs.getInt("product_grade"));
				pro.setProductExplain(rs.getString("product_explain"));
				pro.setProductTotalM(rs.getInt("product_totalm"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return pro;

	}
	//구매기능//
	//구매 : product에 합산시키기//
	public int TotalProPrice(int total,String id) {
		int result =0;
		
		try {
			conn();
			String sql = "update  member set  total_spend = total_spend+? where consumer_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, total);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			disconnect();
		}

		
		
		return result;
		
		
	}
	
	
	
	
	
}
