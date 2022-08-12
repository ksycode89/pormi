package com.promi.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.promi.member.MemberDAO;
import com.promi.member.MemberService;

public class EvenService {

	Scanner s = new Scanner(System.in);

//	==============================
	public void writeBoard() {
		Event event = new Event();

		System.out.println("글을 작성합니다.");
		System.out.println("==============================");
		System.out.print("글이 제목을 입력하세요 >");
		String title = s.nextLine();
		System.out.println("==============================");
		System.out.println("글의 내용을 입력하세요.");
		String contents = s.nextLine();
		System.out.println("==============================");

		event.setEventTitle(title);
		event.setEventContents(contents);
//		event.setEventNum(Event.lsatNum);
		// 아이디
		String id = MemberService.memberInfo.getConsumerId();

		int result = EventDAO.getInstance().writeBoard(event, id);
		if (result == 1) {
			System.out.println("작성완료");

		} else {
			System.out.println("작성 실패");
		}

	}

	// 글목록//
	public void listEvent() {
		List<Event> list = EventDAO.getInstance().listEvent();

		System.out.println("===================목록===============================");
		System.out.println("          제목             작성자         날짜      조회    ");
		for (Event e : list) {
			System.out.print(String.format("%-4d", e.getEventNum()));
			System.out.print(String.format("%-20s", e.getEventTitle()));
			System.out.print(String.format("%-10s", e.getConsumerId()));
			System.out.print(String.format("%-13s", e.getEventDay()));
			System.out.println(String.format("%-4d", e.getCount()) + "|");
			System.out.println("-----------------------------------------------------");

		}

	}

	// 리뷰글목록
	public void listEventR() {
		List<Event> list = EventDAO.getInstance().listEventR();

		System.out.println("===================목록===============================");
		System.out.println("          제목             작성자         날짜      조회    ");
		for (Event e : list) {
			System.out.print(String.format("%-4d", e.getEventNum()));
			System.out.print(String.format("%-20s", e.getEventTitle()));
			System.out.print(String.format("%-10s", e.getConsumerId()));
			System.out.print(String.format("%-13s", e.getEventDay()));
			System.out.println(String.format("%-4d", e.getCount()) + "|");
			System.out.println("-----------------------------------------------------");

		}

	}

	// 읽기//
	public void readEvent() {
		System.out.println("해당글의 번호를 입력해주세요.");
		int select = Integer.parseInt(s.nextLine());

		Event e = EventDAO.getInstance().readEvent(select);
		System.out.println("================================");

		System.out.println("제목  : " + e.getEventTitle());
		System.out.println("글쓴이 : " + e.getConsumerId());
		System.out.print("              " + e.getEventDay());
		System.out.println("   조회 " + e.getCount());
		System.out.println("--------------------------------");
		System.out.println(String.format("%-25s", e.getEventContents()));

		System.out.println();

	}

	// 리뷰읽기
	public void readEventR() {
		System.out.println("해당글의 번호를 입력해주세요.");
		int select = Integer.parseInt(s.nextLine());

		Event e = EventDAO.getInstance().readEventR(select);
		System.out.println("================================");

		System.out.println("제목  : " + e.getEventTitle());
		System.out.println("글쓴이 : " + e.getConsumerId());
		System.out.print("              " + e.getEventDay());
		System.out.println("   조회 " + e.getCount());
		System.out.println("--------------------------------");
		System.out.println(String.format("%-25s", e.getEventContents()));

		System.out.println();

	}

	// 검색//
	public void searchEvent() {
		List<Event> list = new ArrayList<Event>();
		System.out.println("검색을 시작합니다.");
		System.out.println("1.이름검색 2.제목검색");
		int num = Integer.parseInt(s.nextLine());
		if (num == 1) {
			System.out.println("찾으실 글의 작성자를 입력해주세요.");
			String id = s.nextLine();
			list = EventDAO.getInstance().readEventName2(id);
			System.out.println("          제목             작성자         날짜      조회    ");
			System.out.println("===================목록===============================");
			for (Event e : list) {
				System.out.print(String.format("%-4d", e.getEventNum()));
				System.out.print(String.format("%-20s", e.getEventTitle()));
				System.out.print(String.format("%-10s", e.getConsumerId()));
				System.out.print(String.format("%-13s", e.getEventDay()));
				System.out.println(String.format("%-4d", e.getCount()) + "|");
				System.out.println("-----------------------------------------------------");
			}
		} else if (num == 2) {
			System.out.println("찾으실 글의 제목을 입력해주세요.");
			String title = s.nextLine();
			list = EventDAO.getInstance().readEventcontent(title);
			System.out.println("          제목             작성자         날짜      조회    ");
			System.out.println("===================목록===============================");
			for (Event e : list) {
				System.out.print(String.format("%-4d", e.getEventNum()));
				System.out.print(String.format("%-20s", e.getEventTitle()));
				System.out.print(String.format("%-10s", e.getConsumerId()));
				System.out.print(String.format("%-13s", e.getEventDay()));
				System.out.println(String.format("%-4d", e.getCount()) + "|");
				System.out.println("-----------------------------------------------------");
			}

		}

	}
//리뷰검색
	public void searchEventR() {
		List<Event> list = new ArrayList<Event>();
		System.out.println("검색을 시작합니다.");
		System.out.println("1.이름검색 2.제목검색");
		int num = Integer.parseInt(s.nextLine());
		if (num == 1) {
			System.out.println("찾으실 글의 작성자를 입력해주세요.");
			String id = s.nextLine();
			list = EventDAO.getInstance().readEventName2R(id);
			System.out.println("          제목             작성자         날짜      조회    ");
			System.out.println("===================목록===============================");
			for (Event e : list) {
				System.out.print(String.format("%-4d", e.getEventNum()));
				System.out.print(String.format("%-20s", e.getEventTitle()));
				System.out.print(String.format("%-10s", e.getConsumerId()));
				System.out.print(String.format("%-13s", e.getEventDay()));
				System.out.println(String.format("%-4d", e.getCount()) + "|");
				System.out.println("-----------------------------------------------------");
			}
		} else if (num == 2) {
			System.out.println("찾으실 글의 제목을 입력해주세요.");
			String title = s.nextLine();
			list = EventDAO.getInstance().readEventcontentR(title);
			System.out.println("          제목             작성자         날짜      조회    ");
			System.out.println("===================목록===============================");
			for (Event e : list) {
				System.out.print(String.format("%-4d", e.getEventNum()));
				System.out.print(String.format("%-20s", e.getEventTitle()));
				System.out.print(String.format("%-10s", e.getConsumerId()));
				System.out.print(String.format("%-13s", e.getEventDay()));
				System.out.println(String.format("%-4d", e.getCount()) + "|");
				System.out.println("-----------------------------------------------------");
			}

		}

	}

	// 삭제//1.일반삭제 2. 내가쓴글 다삭제
	public void delete() {
		System.out.println("1.일반삭제 2.내글 모두 삭제");
		String num = s.nextLine();
		String consumerid = MemberService.memberInfo.getConsumerId();
		if (num.equals("1")) {

			List<Event> list = EventDAO.getInstance().readEventName(consumerid);
			System.out.println(consumerid + "님의 글은");
			for (Event e : list) {
				System.out.print(+e.getEventNum() + "  제목 : " + e.getEventTitle());
				System.out.println("   날짜 : " + e.getEventDay());
			}
			System.out.println("삭제할 글 번호를 입력하세요.");
			int num2 = Integer.parseInt(s.nextLine());
			String id = EventDAO.getInstance().readEvent(num2).getConsumerId();
			if (consumerid.equals(id)) {
				int result = EventDAO.getInstance().deleteBoard(num2);
				if (result == 1) {
					System.out.println("삭제완료");
				} else {
					System.out.println("삭제실패");
				}
			} else {
				System.out.println("해당글은 삭제할수 없습니다.");
			}

//			EventDAO.getInstance().deleteBoard();
		} else if (num.equals("2")) {

			System.out.println("모든 글이 삭제됩니다. 삭제를 원하시면 '전부삭제' 를 입력하세요. ");
			String id = s.nextLine();
			if (id.equals("전부삭제")) {
				System.out.println(consumerid + "의 모든 글을 삭제합니다.");
				EventDAO.getInstance().deleteAllBoard(MemberService.memberInfo.getConsumerId());

			} else {
				System.out.println("글이 없거나 입력값을 잘못 입력하였습니다.");
			}
		}

	}
	//리뷰삭제하기
	public void deleteR() {
		System.out.println("1.일반삭제 2.내글 모두 삭제 3.종료");
		String num = s.nextLine();
		String consumerid = MemberService.memberInfo.getConsumerId();
		if (num.equals("1")) {
			
			List<Event> list = EventDAO.getInstance().readEventNameR(consumerid);
			System.out.println(consumerid + "님의 글은");
			for (Event e : list) {
				System.out.print(+e.getEventNum() + "  제목 : " + e.getEventTitle());
				System.out.println("   날짜 : " + e.getEventDay());
			}
			System.out.println("삭제할 글 번호를 입력하세요.");
			int num2 = Integer.parseInt(s.nextLine());
		System.out.println(EventDAO.getInstance().readEventR(num2)); 	
		
	
		String id = EventDAO.getInstance().readEventR(num2).getConsumerId();
		
			if (consumerid.equals(id)) {
				//내글인지 체크 구문 but 목록에 안뜸
				int result = EventDAO.getInstance().deleteBoardR(num2);
				if (result == 1) {
					System.out.println("삭제완료");
				} else {
					System.out.println("삭제실패");
				}
			} else if("main".equals(id)) {
				System.out.println("해당글은 삭제할수 없습니다.");
			}

//			EventDAO.getInstance().deleteBoard();
		} else if (num.equals("2")) {

			System.out.println("모든 글이 삭제됩니다. 삭제를 원하시면 '전부삭제' 를 입력하세요. ");
			String id = s.nextLine();
			if (id.equals("전부삭제")) {
				System.out.println(consumerid + "의 모든 글을 삭제합니다.");
				EventDAO.getInstance().deleteAllBoardR(MemberService.memberInfo.getConsumerId());

			} else {
				System.out.println("글이 없거나 입력값을 잘못 입력하였습니다.");
			}
		}

	}

	
	
}
