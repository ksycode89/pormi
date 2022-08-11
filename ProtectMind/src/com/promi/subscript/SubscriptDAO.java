package com.promi.subscript;

import java.util.ArrayList;
import java.util.List;

import com.promi.common.DAO;
import com.promi.member.Member;

public class SubscriptDAO extends DAO {
	private static SubscriptDAO sd = null;

	private SubscriptDAO() {

	}

	public static SubscriptDAO getInstance() {

		return sd == null ? new SubscriptDAO() : sd;
	}

//구매내역올리기 
	public int StackProduck() {
		int result = 0;

		try {
			conn();
			String sql = "update member set delivery = ? where consumer_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, "");
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;

	}

	// 생성//--필요없어짐
//	public int newSub(String id) {
//		int result = 0;
//		try {
//			conn();
//			String sql = "insert into Subscript(consumer_id) values (?) ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			result = pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			disconnect();
//		}
//
//		return result;
//	}

	// 항목늘리기(제품당 누적)
	public int updateInsence(String name, int num, int price, String id) {
		int result = 0;

		try {
			conn();
			String sql = "insert  into subscript (products_name,product_num,product_spend, consumer_id,order_number)  "
					+ "values(?,?,?,?,(select max(order_number) from subscript)+1)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, num);
			pstmt.setInt(3, price);
			pstmt.setString(4, id);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;

	}

//	public int updateInsence(String id) {
//		int result = 0;
//		
//		try {
//			conn();
//			String sql = "update subscript set incense_n =incense_n+1  where consumer_id =?";
//			pstmt=conn.prepareStatement(sql);
////			pstmt.setInt(1, incense);
//			pstmt.setString(1, id);
//			result =pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			disconnect();
//		}
//		
//		
//		return result;
//	}
///해당 회원의 정보를 가져오기///

	public List<Subscript> getBuyList(String id){
		List<Subscript>list = new ArrayList<>();
		Subscript sub = null;
		try {
			conn();
			String sql = "select products_name,product_num,product_spend,buy_day,delivery from Subscript where consumer_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				sub=new Subscript();
				sub.setProductsName(rs.getString("products_name"));
				sub.setProductNum(rs.getInt("product_num"));
				sub.setProductSpend(rs.getInt("product_spend"));
				sub.setBuyDate(rs.getDate("buy_day"));
				sub.setDelivery(rs.getInt("delivery"));
				list.add(sub);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
		
		
		return list;
		
		
		
	}
//배송목차만 가져오기
	
	public List<Subscript> getDeliverList(String id){
		List<Subscript>list = new ArrayList<>();
		Subscript sub = null;
		try {
			conn();
			String sql = "select products_name,product_num,product_spend,buy_day,delivery ,order_number"
					+ " from Subscript where consumer_id=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				sub=new Subscript();
				sub.setProductsName(rs.getString("products_name"));
				sub.setProductNum(rs.getInt("product_num"));
				sub.setProductSpend(rs.getInt("product_spend"));
				sub.setBuyDate(rs.getDate("buy_day"));
				sub.setDelivery(rs.getInt("delivery"));
				sub.setOrderNumber(rs.getInt("order_number"));
				list.add(sub);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
		
		
		return list;
		
		
		
	}
	
//배송 1을 2로 바꾸기
	public int setDeliverComple(int id){
		int result = 0;
		
		try {
			conn();
			String sql = "update Subscript set delivery=2 where order_number=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, result);
			result = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
		
		
		return result;
		
		
		
	}
	
	
}
