package com.promi.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			System.out.println(MemberDAO.getInstance().login(id).getDelivery() + "주 남았습니다. 정말로 삭제하시겠습니까?");
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
		List<Subscript> list = new ArrayList<>();
		int num = 0;
		System.out.println(MemberService.memberInfo.getConsumerName() + "님의 정보를 조회합니다.");
		System.out.println("1.상세조회 2.구매목록 3.계정삭제");
		String select = s.nextLine();
		if (select.equals("1")) {
			Member mem = MemberDAO.getInstance().login(MemberService.memberInfo.getConsumerId());
			System.out.println(mem.toString());

		} else if (select.equals("2")) {
			list = SubscriptDAO.getInstance().getBuyList(MemberService.memberInfo.getConsumerId());
			System.out.println("=== 구매목록을 출력합니다 ===");
			for (Subscript sub : list) {
				System.out.println(num + " : " + sub);
				num++;
			}

		} else if (select.equals("3")) {
			deletememberC(); // 아래 삭제 메서드
		} else {
			System.out.println("입력값이 잘못 되었습니다.");
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
			for(Subscript s : list) {
				System.out.println(s.getConsumerId());
				System.out.println(s.getDelivery());
				
			}
				
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
		System.out.println("1.배달 조회 2.구매 확정 3.배송완료상품");
		String num = s.nextLine();
		String id = MemberService.memberInfo.getConsumerId();
		// 로그인에 해당하는 총 구매 목록 불러오기
		List<Subscript> list = SubscriptDAO.getInstance().getDeliverList(id);
		Subscript sub = null;

		try {
//			배송내용 출력

			if (num.equals("1")) {

				for (Subscript s : list) {
					if (s.getDelivery() > 0) {
						if (s.getDelivery() == 1) {
							System.out.println("배송중인 상품");
							System.out.println(s);
						}
						if (s.getDelivery() == 2) {
							System.out.println("배송완료된 상품");
							System.out.println(s);

						}
					}
				}

				System.out.println();
				// 배달완료시 배달끝내기 (0으로만들기)-나중에 돈받아오기
			} else if (num.equals("2")) {

				for (Subscript s : list) {
					if (s.getDelivery() > 0) {
						if (s.getDelivery() == 2) {
							System.out.println("배송중인 상품");
							System.out.println(s);
						}

					}
				}

			} else if (num.equals("3")) {

				for (Subscript a : list) {

					System.out.println("====배송완료 상품====");
					System.out.println(a.getDelivery());

				}

			}
		} catch (Exception e) {
			System.out.println("회원 아이디가 잘못되었습니다.");
		}
	}

}
