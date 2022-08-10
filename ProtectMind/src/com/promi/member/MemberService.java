package com.promi.member;

import java.util.List;
import java.util.Scanner;

import com.promi.subscript.Subscript;
import com.promi.subscript.SubscriptDAO;

public class MemberService {
	Scanner s = new Scanner(System.in);
	//로그인 정보 저장
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
		SubscriptDAO.getInstance().newSub(id);

	}

	// 로그인용
	public void doLogin() {
	
		try {
			Member mem =null;
			System.out.println("로그인을 시도합니다.");
			System.out.println("아이디");
			String id = s.nextLine();
			System.out.println("패스워드");
			String pw = s.nextLine();

			mem=MemberDAO.getInstance().login(id);

			if (MemberDAO.getInstance().login(id).getConsumerPw().equals(pw)) {
				if (MemberDAO.getInstance().login(id).getRoles() == 1) {
					System.out.println("관리자로 로그인 합니다.");
				}
				System.out.println("로그인 성공");
				memberInfo = mem;//로그인 정보 넣기
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

	// 배달체크 관리자기능 // 0배달전 1배달중 2배송완료(관리자)
	public void delivery() {
		System.out.println("1.배달 체크 2.배달완료(관리자)");
		String num = s.nextLine();
		try {

			if (num.equals("1")) {
				System.out.println("검색할 회원의 id를 입력하세요");
				String id = s.nextLine();
				int check = MemberDAO.getInstance().login(id).getDelivery();
				if (check == 0) {
					System.out.println("배달없음");
				} else if (check == 1) {
					System.out.println("배달중");
				} else if (check == 2) {
					System.out.println("배달완료");
				} else {
					System.out.println("배달 오류 #확인요망#");
				}
			} // 배달완료시 배달끝내기 (0으로만들기)-나중에 돈받아오기
			else if (num.equals("2")) {
				System.out.println("배송완료할 회원아이디 입력");
				String id = s.nextLine();
				int check = MemberDAO.getInstance().login(id).getDelivery();
				if (check == 2) {
					int resust = MemberDAO.getInstance().updateDelivery(id);
					if (resust == 1) {
						System.out.println("변경완료");
					} else {
						System.out.println("변경실패");
					}
				} else {
					System.out.println("구매확정 전입니다.");
				}

			}
		} catch (Exception e) {
			System.out.println("회원 아이디가 잘못되었습니다.");
		}
	}
	// 배송 구매자//

	public void deliveryMem() {
		System.out.println("1.배달 조회 2.구매 확정");
		String num = s.nextLine();
		try {

			if (num.equals("1")) {
			                            	/// 로그인 정보 가져오기↓//
				int check = MemberDAO.getInstance().login("blue").getDelivery();
				if (check == 0) {
					System.out.println("배달없음");
				} else if (check == 1) {
					System.out.println("배달중");
				} else if (check == 2) {
					System.out.println("배달완료");
				} else {
					System.out.println("배달 오류 #확인요망#");
				}
			} // 배달완료시 배달끝내기 (0으로만들기)-나중에 돈받아오기
			else if (num.equals("2")) {
			                        	/// 로그인 정보 가져오기↓//
				int check = MemberDAO.getInstance().login("blue").getDelivery();
				if (check == 1) {
					int resust = MemberDAO.getInstance().updateDeliveryConsumer("blue");
					if (resust == 1) {
						System.out.println("구매확정 완료");
					} else {
						System.out.println("구매확정 실패");
					}

				}else {System.out.println("도착한 상품이 없습니다.");}
			}
		} catch (Exception e) {
			System.out.println("회원 아이디가 잘못되었습니다.");
		}
	}

}
