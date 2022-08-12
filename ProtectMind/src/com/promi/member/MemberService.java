package com.promi.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.promi.event.Event;
import com.promi.event.EventDAO;
import com.promi.subscript.Subscript;
import com.promi.subscript.SubscriptDAO;

public class MemberService {
	Scanner s = new Scanner(System.in);

	// 로그인 정보 저장
	public static Member memberInfo = null;

//가입
	public void insertShip() {

		Member member = new Member();
		System.out.println("가입 시작");
		System.out.println("아이디");
		String id = s.nextLine();
		System.out.println("비번");
		String pw = s.nextLine();
		System.out.println("이름");
		String name = s.nextLine();
		member.setConsumerId(id);
		member.setConsumerPw(pw);
		member.setConsumerName(name);
		MemberDAO.getInstance().insertShip(member);
//		SubscriptDAO.getInstance().newSub(id);

	}

	// 로그인용
	public void doLogin() {

		try {
			Member mem = null;
			System.out.println("로그인을 시도합니다.");
			System.out.println("아이디");
			String id = s.nextLine();
			System.out.println("패스워드");
			String pw = s.nextLine();

			mem = MemberDAO.getInstance().login(id);

			if (MemberDAO.getInstance().login(id).getConsumerPw().equals(pw)) {
				if (MemberDAO.getInstance().login(id).getRoles() == 1) {
					System.out.println("관리자로 로그인 합니다.");
				}
				System.out.println("로그인 성공");
				memberInfo = mem;// 로그인 정보 넣기
			} else {
				System.out.println("로그인 실패");
			}
		} catch (Exception e) {
			System.out.println("입력값이 잘못 되었습니다.");
		}

	}
	// 회원조회 login 사용

	public void memberInfo() {
		System.out.println("검색할 회원의 id를 입력하세요");
		String id = s.nextLine();

		System.out.println(MemberDAO.getInstance().login(id).toString());

	}
//계정 삭제(관리자) --체크

	public void deletememberM() {
		System.out.println("삭제할 계정명");
		String id = s.nextLine();
		// 구독남은 일 수 확인
		if (MemberDAO.getInstance().login(id).getDelivery() != 0) {
			System.out.println(MemberDAO.getInstance().login(id).getSubDay() + "까지 남아있습니다. 정말로 삭제하시겠습니까?");
		}
		// 회원 확인 부분 //
		System.out.println("==========삭제 회원의 정보 ===========");
		System.out.print("이름 : ");
		MemberDAO.getInstance().login(id).getConsumerName();
		System.out.println("아이디 : ");
		MemberDAO.getInstance().login(id).getConsumerId();
		System.out.println("삭제하시려면 '삭제'를 입력해주세요.");
		String del = s.nextLine();
		if (del.equals("삭제")) {
			int result = MemberDAO.getInstance().deletemember(id);

			if (result == 1) {
				System.out.println("삭제완료");
			} else {
				System.out.println("삭제실패");
			}
		}
	}

	// 정보조회//
	public void detailInfo() {
		boolean a= true;
		while(true) {
		List<Subscript> list = new ArrayList<>();
		int num = 0;
		System.out.println(MemberService.memberInfo.getConsumerName() + "님의 정보를 조회합니다.");
		System.out.println("1.상세조회 2.구매목록 3.계정삭제 4.종료");
		String select = s.nextLine();
		if (select.equals("1")) {
			Member mem = MemberDAO.getInstance().login(MemberService.memberInfo.getConsumerId());
			System.out.print(mem.toString());
			if(mem.getSubDay()==null) {
				System.out.println(" | 구독내역이 없습니다.");
			}else {System.out.println(" | 구독만료일 : "+mem.getSubDay());}

		} else if (select.equals("2")) {
			list = SubscriptDAO.getInstance().getBuyList(MemberService.memberInfo.getConsumerId());
			System.out.println("=== 구매목록을 출력합니다 ===");
			System.out.println("구매번호     상품명      상품개수    구매액      구매일");
			for (Subscript sub : list) {
				System.out.println("===================================================");
				System.out.println(sub);
				System.out.println("===================================================");
				num++;
			}

		} else if (select.equals("3")) {
			deletememberC(); // 아래 삭제 메서드
		} else  if(select.equals("4")){
			System.out.println("메인메뉴로 나갑니다.");
			break;
		}else {System.out.println("입력값 오류");}
	}
	}

	// 맴버 회원 탈퇴 구문//
	public void deletememberC() {
		while (true) {
			System.out.println("계정삭제를 진행합니다.");
			System.out.println("확인을 위해 아이디와 비밀번호를 입력해주세요.");
			System.out.print("아이디 : ");
			String id = s.nextLine();

			System.out.print("비밀번호 : ");
			String pw = s.nextLine();
			if (MemberService.memberInfo.getConsumerId().equals(id)
					&& MemberService.memberInfo.getConsumerPw().equals(pw)) {
				System.out.println("확인되었습니다.");
			} else {
				System.out.println("입력값이 잘못되었습니다.");
				break;
			}
			// 구독남은 일 수 확인
			if (MemberDAO.getInstance().login(id).getDelivery() != 0) {
				System.out.println(MemberDAO.getInstance().login(id).getDelivery() + "주 남았습니다. 정말로 삭제하시겠습니까?");
			}
			// 회원 확인 부분 //
			System.out.println("==========삭제 회원의 정보 ===========");
			System.out.println("이름 : " + MemberDAO.getInstance().login(id).getConsumerName());

			System.out.println("아이디 : " + MemberDAO.getInstance().login(id).getConsumerId());

			System.out.println("삭제하시려면 '삭제'를 입력해주세요.");
			String del = s.nextLine();
			if (del.equals("삭제")) {
				int result = MemberDAO.getInstance().deletemember(id);

				if (result == 1) {
					System.out.println("삭제완료");
				} else {
					System.out.println("삭제실패");
				}
			} else {
				System.out.println("구문을 잘못 입력하였습니다.");
				break;
			}
		}

	}

	// 배달체크 관리자기능 // 0배달전 1배달중 2배송완료(관리자)
	public void delivery() {
		System.out.println("1.배달 체크 2.배달완료(관리자)");
		String num = s.nextLine();
		try {
			

			if (num.equals("1")) {
				System.out.println("검색할 회원의 id를 입력하세요");
				String id = s.nextLine();
				List<Subscript> list = SubscriptDAO.getInstance().getDeliverList(id);
				Subscript sub = null;
				// 로그인에 해당하는 총 구매 목록 불러오기
			
				
				System.out.println("배달 중인 상품");
				System.out.println("================================================================");
				
					for (Subscript s : list) {
					if (s.getDelivery() == 1) {
						System.out.println(s);
						System.out.println("================================================================");
					}
				}
					System.out.println("배달완료된 상품");
					System.out.println("================================================================");
				for (Subscript s : list) {
					if (s.getDelivery() == 2) {
						System.out.println(s);
						System.out.println("================================================================");
					}
				}
				System.out.println("구매완료된 상품");
				System.out.println("================================================================");
				for (Subscript s : list) {
					if (s.getDelivery() == 0) {
						System.out.println(s);
						System.out.println("================================================================");
					}
				}
				

//					if (s.getDelivery() == 0) {
//						System.out.println("배달없음");						
//					} else if (s.getDelivery() == 1) {
//						System.out.println("배달 중인 상품");
//						System.out.println(s);
//					} else if (s.getDelivery() == 2) {
//						System.out.println("배달완료");
//						System.out.println(s);
//					} else {
//						System.out.println("배달 오류 #확인요망#");
//					}
//				}
				// 배달완료 눌러주기 (1을 2로 만들기)
			} else if (num.equals("2")) {
				System.out.println("배송완료 할 고객의 id를 입력하세요");
				String id = s.nextLine();
				List<Subscript> list = SubscriptDAO.getInstance().getDeliverList(id);
				Subscript sub = null;
				for (Subscript s : list) {
					if (s.getDelivery() == 1) {
						System.out.println(s);
					}

				}
				System.out.println("배송완료 된 상품의 배송번호를 입력해주세요.");
				int delNUM = Integer.parseInt(s.nextLine());
				int result = SubscriptDAO.getInstance().setDeliverComple(delNUM);
				if (result ==1) {
					System.out.println("배송완료 확인");
				}else {System.out.println("실패");}
			}

		} catch (Exception e) {
			System.out.println("회원 아이디가 잘못되었습니다.");
		}

	}

	// 배송 구매자//
	public void deliveryMem() {
		System.out.println("1.배달 조회 2.구매 확정 3.상품평가하기 ");
		String num = s.nextLine();
		String id = MemberService.memberInfo.getConsumerId();
		// 로그인에 해당하는 총 구매 목록 불러오기
		List<Subscript> list = SubscriptDAO.getInstance().getDeliverList(id);
		Subscript sub = null;

		try {
//			배송내용 출력

			if (num.equals("1")) {

				System.out.println("구매번호     상품명      상품개수    구매액      구매일");
				for (Subscript s : list) {
					if (s.getDelivery() > 0) {
						if (s.getDelivery() == 1) {
							System.out.println("[배송중인 상품]");
							System.out.println(s);
						}
						if (s.getDelivery() == 2) {
							System.out.println("[배송완료된 상품]");
							System.out.println(s);

						}
					}
				}

				System.out.println();
				// 배달완료시 배달끝내기 (0으로만들기)-나중에 돈받아오기
			} else if (num.equals("2")) {
				System.out.println("[구매확정 목록]");
				System.out.println("구매번호     상품명      상품개수    구매액      구매일");
				for (Subscript s : list) {
					
						if (s.getDelivery() == 2) {
							System.out.println(s);
						}
				}
				System.out.println("구매확정할 상품번호를 입력해주세요.");
				int pnum=Integer.parseInt(s.nextLine());
				int result = SubscriptDAO.getInstance().setDeliverMem(pnum);
				if(result==1) {
				System.out.println("구매확정이 되었습니다.");
				System.out.println("리뷰 또는 평점을 남기시려면 아무키나 입력해주십시오 (남기지 않겠다 sksmsajdcjddl를 입력해주세요.)");
				String a = s.nextLine();
				if("sksmsajdcjddl".equals(a)) {
					System.out.println("리뷰를 취소합니다.");
				}else {
					review();
				}
				
				}else {System.out.println("구매확정에 실패하였습니다.");}
			}else if (num.equals("3")){
				review();
				;
			}

	
		} catch (Exception e) {
			System.out.println("회원 아이디가 잘못되었습니다.");
		}
	}
	
	public void review() {
		System.out.println("리뷰와 평점");
//		int num =Integer.parseInt(s.nextLine());
		
			
	
			String id = MemberService.memberInfo.getConsumerId();
			// 로그인에 해당하는 총 구매 목록 불러오기
			List<Subscript> list = SubscriptDAO.getInstance().getDeliverList(id);
			Subscript sub = null;
			//목차뽑기
			for (Subscript s : list) {
				if(s.getDelivery()==0 ||s.getDelivery()==2) {
			if (s.getReview()==0) {
				System.out.println("[리뷰 가능 상품]");
				System.out.println("================================================================");
				System.out.println("구매번호     상품명      상품개수    구매액      구매일");
				if(s.getReview()==0) {
				System.out.println(s);
				}
			}
				}	
		}
		System.out.println("평점과 리뷰를 남기실 구매번호를 입력해주세요.");
		int num2 =Integer.parseInt(s.nextLine());
		System.out.println("평점을 입력해주세요 (1~5)");
		int socre =Integer.parseInt(s.nextLine());
		int result =SubscriptDAO.getInstance().review(socre, num2);
		Subscript sub2 = SubscriptDAO.getInstance().getDeliver(num2)	;
			//리뷰작성
		writeBoardEvent(sub2.getProductsName());	
		
	}
	//리뷰작성 메서드
	public void writeBoardEvent(String name) {
		Event event = new Event();

		System.out.println("리뷰를 작성을 시작합니다.");
		System.out.println("==============================");
		System.out.print("글의 제목을 입력하세요 >");
		String title = s.nextLine();
		System.out.println("==============================");
		System.out.println("글의 내용을 입력하세요.");
		String contents = s.nextLine();
		System.out.println("==============================");

		event.setEventTitle("["+name+"]"+title);
		event.setEventContents(contents);
//		event.setEventNum(Event.lsatNum);
		// 아이디
		String id = MemberService.memberInfo.getConsumerId();

		int result = EventDAO.getInstance().writeBoardEvnet(event, id);
		if (result == 1) {
			System.out.println("작성완료");

		} else {
			System.out.println("작성 실패");
		}

	}
	


}
