package com.promi.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.promi.common.DAO;

public class EventDAO extends DAO {

	private static EventDAO ed = null;

	private EventDAO() {

	}

	public static EventDAO getInstance() {

		return ed == null ? new EventDAO() : ed;
	}

//	(select max(event_num)+1 from event)) 
	// 글쓰기 //
	public int writeBoard(Event event, String id) {
		int result = 0;
		try {
			conn();
			String sql = "insert into event (event_contents,consumer_id,event_title,event_num) "
					+ " values(?,?,?,(select max(event_num) from event)+1 )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event.getEventContents());
			pstmt.setString(2, id);
			pstmt.setString(3, event.getEventTitle());
//			pstmt.setInt(4, event.getEventNum());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	// 게시판 목록보기//
	public List<Event> listEvent() {
		List<Event> list = new ArrayList<>();
		Event event = null;
		try {
			conn();
			String sql = "select* from event";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				event = new Event();
				event.setEventTitle(rs.getString("event_title"));
				event.setEventContents(rs.getString("event_contents"));
				event.setConsumerId(rs.getString("consumer_id"));
				event.setEventDay(rs.getDate("event_day"));
				event.setEventNum(rs.getInt("event_num"));
				list.add(event);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;

	}

	// 글읽기
	public Event readEvent(int num) {
		List<Event> list = new ArrayList<>();
		Event event = null;
		try {
			conn();
			String sql = "select* from event where event_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				event = new Event();
				event.setEventTitle(rs.getString("event_title"));
				event.setEventContents(rs.getString("event_contents"));
				event.setConsumerId(rs.getString("consumer_id"));
				event.setEventDay(rs.getDate("event_day"));
				event.setEventNum(rs.getInt("event_num"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return event;

	}

	// 내가 쓴글 한번에 삭제하기//
	public int deleteAllBoard(String id) {
		int result = 0;
		try {
			conn();
			String sql = "delete from event where consumer_id = ?";
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

	// 글삭제일반으로ㅜ바꿔야함
	public int deleteBoard(int num) {
		int result = 0;
		try {
			conn();
			String sql = "delete from event where event_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	// 아이디로 글찾아오기 삭제용//
	public List<Event> readEventName(String id) {
		List<Event> list = new ArrayList<Event>();
		Event event = null;
		try {
			conn();
			String sql = "select* from event where consumer_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				event = new Event();
				event.setEventTitle(rs.getString("event_title"));
				event.setEventContents(rs.getString("event_contents"));
				event.setConsumerId(rs.getString("consumer_id"));
				event.setEventDay(rs.getDate("event_day"));
				event.setEventNum(rs.getInt("event_num"));
				list.add(event);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;

	}
///검색용 아이디 로 찾아오기
	public List<Event> readEventName2(String id) {
		List<Event> list = new ArrayList<Event>();
		Event event = null;
		try {
			conn();
			String sql = "select* from event where consumer_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				event = new Event();
				event.setEventTitle(rs.getString("event_title"));
				event.setEventContents(rs.getString("event_contents"));
				event.setConsumerId(rs.getString("consumer_id"));
				event.setEventDay(rs.getDate("event_day"));
				event.setEventNum(rs.getInt("event_num"));
				list.add(event);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;

	}
	///내용으로 찾기
	public List<Event> readEventcontent(String title) {
		List<Event> list = new ArrayList<Event>();
		Event event = null;
		try {
			conn();
			String sql = "select* from event where event_title like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+title+"%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				event = new Event();
				event.setEventTitle(rs.getString("event_title"));
				event.setEventContents(rs.getString("event_contents"));
				event.setConsumerId(rs.getString("consumer_id"));
				event.setEventDay(rs.getDate("event_day"));
				event.setEventNum(rs.getInt("event_num"));
				list.add(event);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;

	}
	
	
	
	
}
