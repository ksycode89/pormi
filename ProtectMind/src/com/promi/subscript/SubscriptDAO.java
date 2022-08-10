package com.promi.subscript;

import com.promi.common.DAO;

public class SubscriptDAO extends DAO {
	private static  SubscriptDAO sd = null;
	
	private SubscriptDAO() {
		
	}
	public static SubscriptDAO getInstance () {
		
		return sd == null ? new SubscriptDAO() : sd;
	}
	
//구매내역올리기 
	public int StackProduck() {
		int result = 0;
	
		try {
			conn();
			String sql = "update member set delivery = ? where consumer_id =?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, "");
			result =pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
		
		
		
		return result ;
		
		
	}
	
	//생성//
	public int newSub(String id) {
	int result = 0;
	try {
		conn();
		String sql = "insert into Subscript(consumer_id) values (?) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		result = pstmt.executeUpdate();

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		disconnect();
	}

		return result;
	}
	
}
