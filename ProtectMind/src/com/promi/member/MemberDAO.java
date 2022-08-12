package com.promi.member;

import java.util.ArrayList;
import java.util.List;

import com.promi.common.DAO;
import com.promi.product.ProductDAO;

public class MemberDAO extends DAO {

	private static MemberDAO md = null;

	private MemberDAO() {

	}

	public static MemberDAO getInstance() {
		return md == null ? new MemberDAO() : md;
	}

	// 로그인용 계정 조회 풀스텍//
	public Member login(String id){
		Member mem = null;
		try {
			conn();
			String sql = "select * from member where consumer_id=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mem = new Member();
				mem.setConsumerId(rs.getNString("consumer_id"));
				mem.setConsumerPw(rs.getNString("consumer_pw"));
				mem.setConsumerName(rs.getNString("consumer_name"));
				mem.setRoles(rs.getInt("roles"));
				mem.setSubDay(rs.getString("sub_day"));
				mem.setDelivery(rs.getInt("delivery"));
				mem.settest_sub(rs.getInt("test_sub"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
		
		
		return mem;
	}
	// 계정등록//
	public int insertShip(Member mem) {
		int result = 0;
		try {
			conn();
			String sql = "insert into member(consumer_id,consumer_pw,consumer_name) values (?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getConsumerId());
			pstmt.setString(2, mem.getConsumerPw());
			pstmt.setString(3, mem.getConsumerName());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}
	//계정삭제
	public int deletemember(String id) {
		int result = 0;
		try {
			conn();
			String sql = "delete member where consumer_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			disconnect();
		}

		return result;
	}
	
	
	
	//배달업데이트(완료)//
	public int updateDelivery(String id) {
		int result =0;
		try {
			conn();
			String sql = "update member set delivery = ? where consumer_id =?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, id);
			result =pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
		
		return result;
	}
	//배달관리 [고객]//
	public int updateDeliveryConsumer(String id) {
		int result =0;
		try {
			conn();
			String sql = "update member set delivery = ? where consumer_id =?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 2);
			pstmt.setString(2, id);
			result =pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
		
		return result;
	}
	//상품구매시 1->2
	public int updateDeliveryOrder(String id) {
		int result =0;
		try {
			conn();
			String sql = "update member set delivery = ? where consumer_id =?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, id);
			result =pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
		
		return result;
	}
	
	//구독종료//
	public int subEnd(Member mem) {
		int result =0;
		try {
			conn();
			String sql = "update member set sub_day = null,test_sub=0 where consumer_id =?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getConsumerId());
			result =pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		
		
		return result;
		
	}
	
	
///회원조회 ///
	
	
	
	
	
	
	
}
