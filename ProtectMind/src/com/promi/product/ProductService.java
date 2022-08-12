package com.promi.product;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.promi.member.MemberDAO;
import com.promi.member.MemberService;
import com.promi.subscript.SubscriptDAO;

public class ProductService {
	Scanner s = new Scanner(System.in);
	LocalTime now = LocalTime.now();

//	제품 등록

	public void insertProduct() {

		Product product = new Product();

		System.out.println("제품명");
		String name = s.nextLine();
		System.out.println("제품 가격");
		int price = Integer.parseInt(s.nextLine());
		System.out.println("제품 종류");
		String kind = s.nextLine();
//		System.out.println("평점");
//		int garde= Integer.parseInt(s.nextLine());
		System.out.println("제품 설명");
		String explain = s.nextLine();

		product.setProductName(name);
		product.setProductPrice(price);
		product.setProductKind(kind);
		product.setProductExplain(explain);

		int result = ProductDAO.getInstance().insertProduct(product);
		if (result == 1) {
			System.out.println("O");
		} else {
			System.out.println("X");
		}
	}

//제품삭제
	public void deleteProduck() {
		System.out.println("삭제할 제품명");
		String name = s.nextLine();

		int result = ProductDAO.getInstance().deleteProduck(name);

		if (result == 1) {
			System.out.println("O");
		} else {
			System.out.println("X");
		}

	}

//상품목록
	public void listProduck() {
		int num = 0;

		List<Product> list = ProductDAO.getInstance().listProduck();
		System.out.println("==========================");
		System.out.println("상품목록을 출력합니다.");
		System.out.println("==========================");
		for (Product pro : list) {
			System.out.println(num + ". 제품명 : " + pro.getProductName() + " | 제품 가격 : " + pro.getProductPrice()
					+ "| 제품 종류 : " + pro.getProductKind() + " |");
			num++;
		}
		System.out.println("==========================");

	}

	
	
	
	
	// 상품이름검색//
	public void listProduckName() {
		int num = 0;
		System.out.println("검색할 상품");
		String name = s.nextLine();
		List<Product> list = ProductDAO.getInstance().listProduckName(name);
		System.out.println("================================================================================");
		System.out.println("[검색 결과]");
		System.out.println("================================================================================");

		for (Product pro : list) {
//			System.out.println("["+num+"]");
//			System.out.println("제품명 : "+pro.getProductName()+" | 제품 가격 : "+pro.getProductPrice()+"| 제품 종류 : "+pro.getProductKind()+ " |");		
			System.out.println(num + "." +"제품명   :  "+ pro.getProductName()+"\n  제품 가격 : "+pro.getProductPrice()+"\n  제품 종류 : "+pro.getProductKind()+
					"\n  제품설명  : "+pro.getProductExplain());
			switch (ProductDAO.getInstance().avgGrade(pro.getProductName())) {
			case 0: System.out.println("죄송합니다 곧 삭제될 메뉴입니다."); break;
			case 1: System.out.println("  평점     : ☆☆☆☆★"); break;
			case 2: System.out.println("  평점     : ☆☆☆★★"); break;
			case 3: System.out.println("  평점     : ☆☆★★★"); break;
			case 4: System.out.println("  평점     : ☆★★★★"); break;
			case 5: System.out.println("  평점     : ★★★★★"); break;
				
			}
			num++;
			System.out.println("==============================================================================");
		}

	}

//	총 판매목록(누적),일반구매
	public void buyProduct() {
		try {
			
		
		System.out.println("구입할 상품의 명과 수량을 입력해주세요.");
		System.out.print("상품명 :");
		String name = s.nextLine();
		System.out.print("갯수 :");
		int num = Integer.parseInt(s.nextLine());
		int sum = 0;
		
		Product pro = ProductDAO.getInstance().buyList(name);
		if (pro.getProductName().equals(name)) {
		
			System.out.println("구매하는 물품은 " + pro.getProductName() + "이며" + " 수량은 " + num + "입니다.");
			sum = pro.getProductPrice() * num;
			System.out.println("총 결제 금액은 " + sum + "원 입니다.");
			System.out.println("결제를 시작합니다.");
			System.out.println("비밀번호를 입력해주세요");
			String pw = s.nextLine();
			if (MemberService.memberInfo.getConsumerPw().equals(pw)) {

				int result = MemberDAO.getInstance().updateDeliveryOrder(MemberService.memberInfo.getConsumerId());
				if (result == 1) {
					System.out.println("주문완료");
					// product에 총액 누적합산
					ProductDAO.getInstance().TotalMemPrice(sum, MemberService.memberInfo.getConsumerId());
					// 상품 제품 누적 부분
					SubscriptDAO.getInstance().updateInsence(pro.getProductName(), num, sum,
							MemberService.memberInfo.getConsumerId());
					// 배달 0-1로 번경 구문
					// product 에 판매수 누적
					ProductDAO.getInstance().TotalProNum(num, name);
					// product에 판매 액누적
					ProductDAO.getInstance().TotalProMoney(sum, name);
				} else {
					System.out.println("주문 실패");
				}

//			if("인센스스틱".equals(pro.getProductKind())) {
//				SubscriptDAO.getInstance().updateInsence(MemberService.memberInfo.getConsumerId());
//			}else if ("차".equals(pro.getProductKind())) {
//				SubscriptDAO.getInstance().updateInsence(MemberService.memberInfo.getConsumerId());
//			}else if ("필로우미스트".equals(pro.getProductKind())) {
//				SubscriptDAO.getInstance().updateInsence(MemberService.memberInfo.getConsumerId());
//			}

			} else {
				System.out.println("잘못된 비밀 번호입니다.");
			}

		} else {
			System.out.println("올바른 상품명을 입력해 주세요.");
		}
	} catch (Exception e) {
		System.out.println("상품명을 바르게 넣어주세요.");
	}
	}

	// r구독하기
	public void sub(int num) {
//		String memsub = MemberService.memberInfo.getSubDay().substring(0,10);
		LocalDate date = LocalDate.now();
		int min = now.getMinute();
		LocalDate date2 = date.plusWeeks(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String a = date2.format(formatter);

		// 일주일 구독

		if (num == 1) {
			// 확인용
			System.out.println(1);
			int resultR = ProductDAO.getInstance().subDayReal(a, MemberService.memberInfo);
			if (resultR == 1) {
			
			}

			int result = ProductDAO.getInstance().subDay(min + 3, MemberService.memberInfo);
			if (result == 1) {
				System.out.println("구독완료");
			}
			// 30일구독
		} else if (num == 2) {
			date2 = date.plusWeeks(4);
			 formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			 a = date2.format(formatter);
			
			int resultR = ProductDAO.getInstance().subDayReal(a, MemberService.memberInfo);
			if (resultR == 1) {
			
			}

			int result2 = ProductDAO.getInstance().subDay(min + 5, MemberService.memberInfo);
			if (result2 == 1) {
				System.out.println("구독완료");
			}
		} 

	}
//null아닐때
	public void subCheck(int num) {
		LocalDate date = LocalDate.now();
		int min = now.getMinute();
		LocalDate date2 = date.plusWeeks(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String a = date2.format(formatter);
		if (num == 1) {
			// 확인용

			int resultR = ProductDAO.getInstance().subDayRealWeek(MemberService.memberInfo);
			if (resultR == 1) {
			
			}

			int result = ProductDAO.getInstance().subDay(min + 3, MemberService.memberInfo);
			if (result == 1) {
				System.out.println("구독완료");
			}
			// 30일구독
		} else if (num == 2) {
			subMonth();
//			int resultR = ProductDAO.getInstance().subDayRealMonth(MemberService.memberInfo);
//			if (resultR == 1) {
//				System.out.println("구독완료");
//			}
//
//			int result2 = ProductDAO.getInstance().subDay(min + 5, MemberService.memberInfo);
//			if (result2 == 1) {
//				System.out.println("구독완료");
//			}
		}
	}
	
	public void subMonth() {
		LocalDate date = LocalDate.now();
		int min = now.getMinute();
		LocalDate date2 = date.plusWeeks(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String a = date2.format(formatter);
		int resultR = ProductDAO.getInstance().subDayRealMonth(MemberService.memberInfo);
		if (resultR == 1) {
		
		}

		int result2 = ProductDAO.getInstance().subDay(min + 5, MemberService.memberInfo);
		if (result2 == 1) {
			System.out.println("구독완료");
		}
		
	}
	
	
}

//	(재고체크 ),
//	회원조회(아이디, 구독정보,구매정보)
//	리뷰(확인,삭제) 	
